package com.bupt.blazkowicz.configuration.infrastructure.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bupt.blazkowicz.configuration.domain.entity.Strategy;
import com.bupt.blazkowicz.configuration.domain.repo.StrategyRepo;
import com.bupt.blazkowicz.configuration.infrastructure.dal.StrategyMapper;
import com.bupt.blazkowicz.domain.share.entity.BusinessIdentity;
import com.bupt.blazkowicz.domain.share.entity.PreventionType;

/**
 * @author lhf2018
 * @date 2023/3/4 18:02
 */
@Component
public class StrategyRepoImpl implements StrategyRepo {
    @Autowired
    private StrategyMapper strategyMapper;

    @Override
    public void save(Strategy strategy) {
        // todo
    }

    @Override
    public Strategy get(BusinessIdentity businessIdentity, PreventionType preventionType, String name) {
        // todo
        return null;
    }
}
