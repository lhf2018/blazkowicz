package com.bupt.blazkowicz.configuration.application.api;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bupt.blazkowicz.client.share.dto.result.ResultDTO;
import com.bupt.blazkowicz.common.exception.BlazkowiczException;
import com.bupt.blazkowicz.common.exception.Code;
import com.bupt.blazkowicz.configuration.client.api.RuleConfigurationService;
import com.bupt.blazkowicz.configuration.client.api.req.CreateRuleReq;
import com.bupt.blazkowicz.configuration.client.api.req.UpdateRuleReq;
import com.bupt.blazkowicz.configuration.client.api.resp.RuleDetailResp;
import com.bupt.blazkowicz.domain.share.entity.Condition;
import com.bupt.blazkowicz.domain.share.entity.Rule;
import com.bupt.blazkowicz.domain.share.factory.RuleFactory;
import com.bupt.blazkowicz.domain.share.repo.RuleRepo;
import com.bupt.blazkowicz.infrastructure.share.dal.dataobject.RuleDO;
import com.bupt.blazkowicz.infrastructure.share.dal.mapper.RuleMapper;
import com.bupt.blazkowicz.infrastructure.share.query.RuleQueryService;

/**
 * @author lhf2018
 */
@Component
public class RuleConfigurationServiceImpl implements RuleConfigurationService {
    @Autowired
    private RuleRepo ruleRepo;
    @Autowired
    private RuleMapper ruleMapper;
    @Autowired
    private RuleQueryService ruleQueryService;

    @Override
    public ResultDTO<Void> create(CreateRuleReq createRuleReq) {
        ruleRepo.save(RuleFactory.create(createRuleReq.getRuleName(),
            JSON.parseObject(createRuleReq.getRuleConditions(), new TypeReference<List<Condition>>() {}),
            createRuleReq.getRuleLogic()));
        invalidateAllRuleCaches();
        return ResultDTO.buildSuccessResult();
    }

    @Override
    public ResultDTO<Void> update(UpdateRuleReq updateRuleReq) {
        Rule rule = ruleRepo.get(updateRuleReq.getRuleId());
        if (rule == null) {
            throw new BlazkowiczException(Code.PARAMETER_INVALID, "规则不存在");
        }
        rule.update(updateRuleReq.getRuleLogic());
        rule.update(JSON.parseObject(updateRuleReq.getRuleConditions(), new TypeReference<List<Condition>>() {}));
        ruleRepo.save(rule);
        invalidateAllRuleCaches();
        return ResultDTO.buildSuccessResult();
    }

    @Override
    public ResultDTO<List<RuleDetailResp>> list() {
        List<RuleDO> ruleDOList = ruleMapper.listAll();
        if (ruleDOList == null || ruleDOList.isEmpty()) {
            return ResultDTO.buildSuccessResult(Collections.emptyList());
        }
        List<RuleDetailResp> respList =
            ruleDOList.stream().map(this::toDetailResp).collect(Collectors.toList());
        return ResultDTO.buildSuccessResult(respList);
    }

    @Override
    public ResultDTO<RuleDetailResp> get(Long ruleId) {
        Rule rule = ruleRepo.get(ruleId);
        if (rule == null) {
            throw new BlazkowiczException(Code.PARAMETER_INVALID, "规则不存在");
        }
        RuleDO ruleDO = ruleMapper.getByRuleId(ruleId);
        return ResultDTO.buildSuccessResult(toDetailResp(ruleDO));
    }

    private RuleDetailResp toDetailResp(RuleDO ruleDO) {
        RuleDetailResp resp = new RuleDetailResp();
        resp.setRuleId(Long.valueOf(ruleDO.getRuleId()));
        resp.setRuleName(ruleDO.getRuleName());
        resp.setRuleConditions(ruleDO.getConditions());
        resp.setRuleLogic(ruleDO.getLogic());
        resp.setLeftParamType(ruleDO.getLeftParamType());
        resp.setVersion(ruleDO.getVersion());
        return resp;
    }

    private void invalidateAllRuleCaches() {
        ruleQueryService.invalidateRulesCache("TEST", "TEST");
    }
}
