package com.bupt.blazkowicz.configuration.infrastructure.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bupt.blazkowicz.configuration.domain.entity.Strategy;
import com.bupt.blazkowicz.configuration.domain.repo.StrategyRepo;
import com.bupt.blazkowicz.configuration.infrastructure.dal.StrategyMapper;
import com.bupt.blazkowicz.configuration.infrastructure.dal.dataobject.StrategyDO;
import com.bupt.blazkowicz.configuration.infrastructure.translator.ToStrategyDOTranslator;
import com.bupt.blazkowicz.configuration.infrastructure.translator.ToStrategyTranslator;
import com.bupt.blazkowicz.domain.share.dto.DisposalCustomDTO;
import com.bupt.blazkowicz.domain.share.entity.BusinessIdentity;
import com.bupt.blazkowicz.domain.share.entity.PreventionType;
import com.bupt.blazkowicz.infrastructure.share.query.PreventionConfigInfService;

/**
 * @author lhf2018
 * @date 2023/3/4 18:02
 */
@Component
public class StrategyRepoImpl implements StrategyRepo {
    @Autowired
    private StrategyMapper strategyMapper;
    @Autowired
    private PreventionConfigInfService preventionConfigInfService;

    @Override
    public void save(Strategy strategy) {
        StrategyDO oldStrategyDO = strategyMapper.get(strategy.getStrategyId());
        if (oldStrategyDO == null) {
            strategyMapper.insert(ToStrategyDOTranslator.toStrategyDO(strategy));
        } else {
            strategyMapper.update(ToStrategyDOTranslator.toStrategyDO(strategy));
        }
        // 插入或更新处置信息
        preventionConfigInfService.publishDisposalResp(strategy.getBusinessIdentity().name(),
            strategy.getPreventionType().name(), strategy.getIntroducedRuleId(), new DisposalCustomDTO());
    }

    @Override
    public Strategy get(BusinessIdentity businessIdentity, PreventionType preventionType, String name) {
        return ToStrategyTranslator
            .toStrategy(strategyMapper.get(businessIdentity.name(), preventionType.name(), name));
    }
}
