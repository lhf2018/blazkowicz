package com.bupt.blazkowicz.configuration.application.api;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.bupt.blazkowicz.client.share.dto.result.ResultDTO;
import com.bupt.blazkowicz.common.exception.BlazkowiczException;
import com.bupt.blazkowicz.common.exception.Code;
import com.bupt.blazkowicz.configuration.client.api.StrategyConfigurationService;
import com.bupt.blazkowicz.configuration.client.api.req.CreateStrategyReq;
import com.bupt.blazkowicz.configuration.client.api.req.UpdateStrategyReq;
import com.bupt.blazkowicz.configuration.client.api.resp.StrategyDetailResp;
import com.bupt.blazkowicz.domain.share.bridge.SequenceInfService;
import com.bupt.blazkowicz.domain.share.bridge.enums.SequenceType;
import com.bupt.blazkowicz.domain.share.dto.DisposalCustomDTO;
import com.bupt.blazkowicz.infrastructure.share.dal.dataobject.RuleDO;
import com.bupt.blazkowicz.infrastructure.share.dal.dataobject.StrategyDO;
import com.bupt.blazkowicz.infrastructure.share.dal.mapper.RuleMapper;
import com.bupt.blazkowicz.infrastructure.share.dal.mapper.StrategyMapper;
import com.bupt.blazkowicz.infrastructure.share.query.PreventionConfigInfService;
import com.bupt.blazkowicz.infrastructure.share.query.RuleQueryService;
import com.bupt.blazkowicz.infrastructure.share.translator.DisposalConfigTranslator;

/**
 * @author lhf2018
 */
@Component
public class StrategyConfigurationServiceImpl implements StrategyConfigurationService {
    @Autowired
    private StrategyMapper strategyMapper;
    @Autowired
    private RuleMapper ruleMapper;
    @Autowired
    private SequenceInfService sequenceInfService;
    @Autowired
    private PreventionConfigInfService preventionConfigInfService;
    @Autowired
    private RuleQueryService ruleQueryService;

    @Override
    public ResultDTO<Void> create(CreateStrategyReq createStrategyReq) {
        validateStrategyKey(createStrategyReq.getBusinessIdentity(), createStrategyReq.getPreventionType(),
            createStrategyReq.getName());
        StrategyDO existing = strategyMapper.get(createStrategyReq.getBusinessIdentity(),
            createStrategyReq.getPreventionType(), createStrategyReq.getName());
        if (existing != null) {
            throw new BlazkowiczException(Code.PARAMETER_INVALID, "策略已存在");
        }
        ensureRuleExists(createStrategyReq.getIntroducedRuleId());

        StrategyDO strategyDO = buildStrategyDO(createStrategyReq.getBusinessIdentity(),
            createStrategyReq.getPreventionType(), createStrategyReq.getName(), createStrategyReq.getDescription(),
            createStrategyReq.getIntroducedRuleId(), createStrategyReq.getDisposalType(),
            createStrategyReq.getDisposalAction(), createStrategyReq.getDisposalMessage(), null);
        strategyDO.setStrategyId(sequenceInfService.nextSequenceId(SequenceType.STRATEGY_ID));
        strategyMapper.insert(strategyDO);
        publishAndInvalidate(strategyDO);
        return ResultDTO.buildSuccessResult();
    }

    @Override
    public ResultDTO<Void> update(UpdateStrategyReq updateStrategyReq) {
        validateStrategyKey(updateStrategyReq.getBusinessIdentity(), updateStrategyReq.getPreventionType(),
            updateStrategyReq.getName());
        StrategyDO existing = strategyMapper.get(updateStrategyReq.getBusinessIdentity(),
            updateStrategyReq.getPreventionType(), updateStrategyReq.getName());
        if (existing == null) {
            throw new BlazkowiczException(Code.PARAMETER_INVALID, "策略不存在");
        }
        ensureRuleExists(updateStrategyReq.getIntroducedRuleId());

        StrategyDO strategyDO = buildStrategyDO(updateStrategyReq.getBusinessIdentity(),
            updateStrategyReq.getPreventionType(), updateStrategyReq.getName(), updateStrategyReq.getDescription(),
            updateStrategyReq.getIntroducedRuleId(), updateStrategyReq.getDisposalType(),
            updateStrategyReq.getDisposalAction(), updateStrategyReq.getDisposalMessage(), existing.getVersion());
        strategyDO.setStrategyId(existing.getStrategyId());
        strategyMapper.update(strategyDO);
        publishAndInvalidate(strategyDO);
        return ResultDTO.buildSuccessResult();
    }

    @Override
    public ResultDTO<List<StrategyDetailResp>> list(String businessIdentity, String preventionType) {
        List<StrategyDO> strategyDOList =
            strategyMapper.listByBusinessAndPrevention(businessIdentity, preventionType);
        if (strategyDOList == null || strategyDOList.isEmpty()) {
            return ResultDTO.buildSuccessResult(Collections.emptyList());
        }
        List<StrategyDetailResp> respList =
            strategyDOList.stream().map(this::toDetailResp).collect(Collectors.toList());
        return ResultDTO.buildSuccessResult(respList);
    }

    @Override
    public ResultDTO<StrategyDetailResp> get(String businessIdentity, String preventionType, String name) {
        StrategyDO strategyDO = strategyMapper.get(businessIdentity, preventionType, name);
        if (strategyDO == null) {
            throw new BlazkowiczException(Code.PARAMETER_INVALID, "策略不存在");
        }
        return ResultDTO.buildSuccessResult(toDetailResp(strategyDO));
    }

    private StrategyDO buildStrategyDO(String businessIdentity, String preventionType, String name,
        String description, Long introducedRuleId, String disposalType, String disposalAction, String disposalMessage,
        Integer version) {
        StrategyDO strategyDO = new StrategyDO();
        strategyDO.setBusinessIdentity(businessIdentity);
        strategyDO.setPreventionType(preventionType);
        strategyDO.setName(name);
        strategyDO.setDescription(description);
        strategyDO.setIntroducedRuleId(introducedRuleId);
        strategyDO.setVersion(version);

        DisposalCustomDTO disposalCustomDTO = new DisposalCustomDTO();
        disposalCustomDTO.setBusinessIdentity(businessIdentity);
        disposalCustomDTO.setPreventionType(preventionType);
        disposalCustomDTO.setRuleId(introducedRuleId);
        disposalCustomDTO.setDisposalType(StringUtils.defaultIfBlank(disposalType, "AUDIT"));
        disposalCustomDTO.setAction(StringUtils.defaultIfBlank(disposalAction, "MANUAL_REVIEW"));
        disposalCustomDTO
            .setMessage(StringUtils.defaultIfBlank(disposalMessage, "风险命中，需人工审核"));
        strategyDO.setDisposalConfig(DisposalConfigTranslator.toJson(disposalCustomDTO));
        return strategyDO;
    }

    private StrategyDetailResp toDetailResp(StrategyDO strategyDO) {
        StrategyDetailResp resp = new StrategyDetailResp();
        resp.setStrategyId(strategyDO.getStrategyId());
        resp.setBusinessIdentity(strategyDO.getBusinessIdentity());
        resp.setPreventionType(strategyDO.getPreventionType());
        resp.setName(strategyDO.getName());
        resp.setDescription(strategyDO.getDescription());
        resp.setIntroducedRuleId(strategyDO.getIntroducedRuleId());
        resp.setVersion(strategyDO.getVersion());

        RuleDO ruleDO = ruleMapper.getByRuleId(strategyDO.getIntroducedRuleId());
        if (ruleDO != null) {
            resp.setRuleName(ruleDO.getRuleName());
        }

        DisposalCustomDTO disposalCustomDTO =
            JSON.parseObject(strategyDO.getDisposalConfig(), DisposalCustomDTO.class);
        if (disposalCustomDTO != null) {
            resp.setDisposalType(disposalCustomDTO.getDisposalType());
            resp.setDisposalAction(disposalCustomDTO.getAction());
            resp.setDisposalMessage(disposalCustomDTO.getMessage());
        }
        return resp;
    }

    private void publishAndInvalidate(StrategyDO strategyDO) {
        DisposalCustomDTO disposalCustomDTO =
            JSON.parseObject(strategyDO.getDisposalConfig(), DisposalCustomDTO.class);
        preventionConfigInfService.publishDisposalResp(disposalCustomDTO);
        ruleQueryService.invalidateRulesCache(strategyDO.getBusinessIdentity(), strategyDO.getPreventionType());
    }

    private void ensureRuleExists(Long ruleId) {
        if (ruleId == null || ruleMapper.getByRuleId(ruleId) == null) {
            throw new BlazkowiczException(Code.PARAMETER_INVALID, "关联规则不存在");
        }
    }

    private void validateStrategyKey(String businessIdentity, String preventionType, String name) {
        if (StringUtils.isAnyBlank(businessIdentity, preventionType, name)) {
            throw new BlazkowiczException(Code.PARAMETER_INVALID, "策略标识不完整");
        }
    }
}
