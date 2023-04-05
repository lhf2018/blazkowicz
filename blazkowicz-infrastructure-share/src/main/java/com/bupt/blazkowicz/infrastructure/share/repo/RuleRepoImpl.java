package com.bupt.blazkowicz.infrastructure.share.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bupt.blazkowicz.domain.share.entity.BusinessIdentity;
import com.bupt.blazkowicz.domain.share.entity.PreventionType;
import com.bupt.blazkowicz.domain.share.entity.Rule;
import com.bupt.blazkowicz.domain.share.repo.RuleRepo;
import com.bupt.blazkowicz.infrastructure.share.dal.mapper.RuleMapper;
import com.bupt.blazkowicz.infrastructure.share.inf.CacheInfService;

/**
 * @author lhf2018
 * @date 2022/11/5 15:18
 */
@Component
public class RuleRepoImpl implements RuleRepo {
    @Autowired
    private CacheInfService cacheInfService;
    @Autowired
    private RuleMapper ruleMapper;

    @Override
    public List<Rule> get(BusinessIdentity businessIdentity, PreventionType preventionType) {
        // todo
        return null;
    }

    @Override
    public Rule get(Long ruleId) {
        // todo
        return null;
    }

    @Override
    public void save(Rule rule) {

    }
}
