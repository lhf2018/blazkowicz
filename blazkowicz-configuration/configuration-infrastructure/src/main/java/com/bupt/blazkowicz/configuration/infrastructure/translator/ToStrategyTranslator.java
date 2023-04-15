package com.bupt.blazkowicz.configuration.infrastructure.translator;

import com.alibaba.fastjson.JSON;
import com.bupt.blazkowicz.configuration.domain.entity.Disposal;
import com.bupt.blazkowicz.configuration.domain.entity.Strategy;
import com.bupt.blazkowicz.configuration.infrastructure.dal.dataobject.StrategyDO;
import com.bupt.blazkowicz.domain.share.entity.BusinessIdentity;
import com.bupt.blazkowicz.domain.share.entity.PreventionType;
import com.alibaba.fastjson.TypeReference;

import java.util.List;

/**
 * @author lhf2018
 * @date 2023/4/16 2:14
 */
public class ToStrategyTranslator {
    public static Strategy toStrategy(StrategyDO strategyDO) {
        return new Strategy(
                strategyDO.getStrategyId(),
                strategyDO.getGmtCreate(),
                strategyDO.getGmtModified(),
                strategyDO.getVersion(),
                BusinessIdentity.valueOf(strategyDO.getBusinessIdentity()),
                PreventionType.valueOf(strategyDO.getPreventionType()),
                strategyDO.getName(),
                strategyDO.getDescription(),
                strategyDO.getIntroducedRuleId(),
                JSON.parseObject(strategyDO.getDisposalList(),new TypeReference<List<Disposal>>(){}));
    }
}
