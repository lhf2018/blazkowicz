package com.bupt.blazkowicz.configuration.infrastructure.translator;

import com.alibaba.fastjson.JSON;
import com.bupt.blazkowicz.configuration.domain.entity.Strategy;
import com.bupt.blazkowicz.configuration.infrastructure.dal.dataobject.StrategyDO;

/**
 * @author lhf2018
 * @date 2023/4/16 2:14
 */
public class ToStrategyDOTranslator {
    public static StrategyDO toStrategyDO(Strategy strategy) {
        StrategyDO strategyDO = new StrategyDO();
        strategyDO.setStrategyId(strategy.getStrategyId());
        strategyDO.setDescription(strategy.getDescription());
        strategyDO.setBusinessIdentity(strategy.getBusinessIdentity().name());
        strategyDO.setDisposalList(JSON.toJSONString(strategy.getDisposalList()));
        strategyDO.setGmtCreate(strategy.getGmtCreate());
        strategyDO.setGmtModified(strategy.getGmtModified());
        strategyDO.setName(strategy.getName());
        strategyDO.setVersion(strategy.getVersion());
        strategyDO.setIntroducedRuleId(strategy.getIntroducedRuleId());
        strategyDO.setPreventionType(strategy.getPreventionType().name());
        return strategyDO;
    }
}
