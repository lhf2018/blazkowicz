package com.bupt.infrastructure.share.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bupt.domain.share.entity.BusinessIdentity;
import com.bupt.domain.share.entity.PreventionType;
import com.bupt.domain.share.entity.Rule;
import com.bupt.domain.share.repo.RuleRepo;
import com.bupt.infrastructure.share.inf.NosqlInfService;

/**
 * @author lhf2018
 * @date 2022/11/5 15:18
 */
@Component
public class RuleRepoImpl implements RuleRepo {
    @Autowired
    private NosqlInfService nosqlInfService;

    @Override
    public List<Rule> get(BusinessIdentity businessIdentity, PreventionType preventionType) {
        // todo
        return null;
    }

    @Override
    public Rule get(String ruleId) {
        // todo
        return null;
    }

    @Override
    public void save(Rule rule) {

    }
}
