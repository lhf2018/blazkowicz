package com.bupt.blazkowicz.configuration.infrastructure.translator;

import java.util.Collections;
import java.util.Date;

import com.bupt.blazkowicz.configuration.domain.entity.Disposal;
import com.bupt.blazkowicz.configuration.domain.entity.Strategy;
import com.bupt.blazkowicz.domain.share.dto.DisposalCustomDTO;
import com.bupt.blazkowicz.domain.share.entity.BusinessIdentity;
import com.bupt.blazkowicz.domain.share.entity.PreventionType;
import com.bupt.blazkowicz.infrastructure.share.dal.dataobject.StrategyDO;
import com.bupt.blazkowicz.infrastructure.share.translator.DisposalConfigTranslator;

/**
 * @author lhf2018
 */
public final class StrategyDOTranslator {

    private StrategyDOTranslator() {}

    public static StrategyDO toDO(Strategy strategy) {
        StrategyDO strategyDO = new StrategyDO();
        strategyDO.setStrategyId(strategy.getStrategyId());
        strategyDO.setVersion(strategy.getVersion());
        strategyDO.setBusinessIdentity(strategy.getBusinessIdentity().name());
        strategyDO.setPreventionType(strategy.getPreventionType().name());
        strategyDO.setName(strategy.getName());
        strategyDO.setDescription(strategy.getDescription());
        strategyDO.setIntroducedRuleId(strategy.getIntroducedRuleId());
        strategyDO.setDisposalConfig(DisposalConfigTranslator.toJson(toDisposalCustomDTO(strategy)));
        return strategyDO;
    }

    public static Strategy toDomain(StrategyDO strategyDO) {
        if (strategyDO == null) {
            return null;
        }
        Strategy strategy = new Strategy(strategyDO.getStrategyId(), new Date(), new Date(), strategyDO.getVersion(),
            BusinessIdentity.valueOf(strategyDO.getBusinessIdentity()),
            PreventionType.valueOf(strategyDO.getPreventionType()), strategyDO.getName(), strategyDO.getDescription(),
            strategyDO.getIntroducedRuleId(), Collections.<Disposal>emptyList());
        return strategy;
    }

    private static DisposalCustomDTO toDisposalCustomDTO(Strategy strategy) {
        DisposalCustomDTO dto = new DisposalCustomDTO();
        dto.setBusinessIdentity(strategy.getBusinessIdentity().name());
        dto.setPreventionType(strategy.getPreventionType().name());
        dto.setRuleId(strategy.getIntroducedRuleId());
        if (strategy.getDisposalList() != null && !strategy.getDisposalList().isEmpty()) {
            dto.setDisposalType(strategy.getDisposalList().get(0).getType().name());
            dto.setAction("MANUAL_REVIEW");
            dto.setMessage("风险命中，需人工审核");
        } else {
            dto.setDisposalType("AUDIT");
            dto.setAction("MANUAL_REVIEW");
            dto.setMessage("风险命中，需人工审核");
        }
        return dto;
    }
}
