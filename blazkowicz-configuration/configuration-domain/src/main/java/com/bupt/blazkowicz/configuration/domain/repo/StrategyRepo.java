package com.bupt.blazkowicz.configuration.domain.repo;

import com.bupt.blazkowicz.configuration.domain.entity.Strategy;
import com.bupt.blazkowicz.domain.share.entity.BusinessIdentity;
import com.bupt.blazkowicz.domain.share.entity.PreventionType;

/**
 * @author lhf2018
 */
public interface StrategyRepo {
    /**
     * 保存
     * 
     * @param strategy
     */
    void save(Strategy strategy);

    /**
     * 获取策略
     * 
     * @param businessIdentity
     * @param preventionType
     * @param name
     * @return
     */
    Strategy get(BusinessIdentity businessIdentity, PreventionType preventionType, String name);
}
