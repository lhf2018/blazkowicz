package com.bupt.blazkowicz.configuration.infrastructure.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.bupt.blazkowicz.configuration.domain.entity.Strategy;
import com.bupt.blazkowicz.configuration.domain.repo.StrategyRepo;
import com.bupt.blazkowicz.configuration.infrastructure.translator.StrategyDOTranslator;
import com.bupt.blazkowicz.domain.share.dto.DisposalCustomDTO;
import com.bupt.blazkowicz.domain.share.entity.BusinessIdentity;
import com.bupt.blazkowicz.domain.share.entity.PreventionType;
import com.bupt.blazkowicz.infrastructure.share.dal.dataobject.StrategyDO;
import com.bupt.blazkowicz.infrastructure.share.dal.mapper.StrategyMapper;
import com.bupt.blazkowicz.infrastructure.share.query.PreventionConfigInfService;
import com.bupt.blazkowicz.infrastructure.share.query.RuleQueryService;

/**
 * @author lhf2018
 */
@Component
public class StrategyRepoImpl implements StrategyRepo {
    @Autowired
    private StrategyMapper strategyMapper;
    @Autowired
    private PreventionConfigInfService preventionConfigInfService;
    @Autowired
    private RuleQueryService ruleQueryService;

    @Override
    public void save(Strategy strategy) {
        StrategyDO strategyDO = StrategyDOTranslator.toDO(strategy);
        StrategyDO existing = strategyMapper.get(strategy.getBusinessIdentity().name(),
            strategy.getPreventionType().name(), strategy.getName());
        if (existing == null) {
            strategyMapper.insert(strategyDO);
        } else {
            strategyDO.setVersion(existing.getVersion());
            strategyMapper.update(strategyDO);
        }
        DisposalCustomDTO disposalCustomDTO = JSON.parseObject(strategyDO.getDisposalConfig(), DisposalCustomDTO.class);
        preventionConfigInfService.publishDisposalResp(disposalCustomDTO);
        ruleQueryService.invalidateRulesCache(strategy.getBusinessIdentity().name(),
            strategy.getPreventionType().name());
    }

    @Override
    public Strategy get(BusinessIdentity businessIdentity, PreventionType preventionType, String name) {
        StrategyDO strategyDO = strategyMapper.get(businessIdentity.name(), preventionType.name(), name);
        return StrategyDOTranslator.toDomain(strategyDO);
    }
}
