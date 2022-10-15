package com.bupt.infrastructure.share.inf;

import com.bupt.domain.share.entity.Rule;
import com.bupt.domain.share.inf.RuleEngineInfService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author lhf2018
 * @date 2022/10/6 18:50
 */
@Component
public class RuleEngineInfServiceImpl implements RuleEngineInfService {

    @Override
    public List<Rule> getRuleList(String businessIdentity, String preventionType, String name) {
        // todo
        return null;
    }

    @Override
    public void createRule(Rule rule) {
        // todo
    }
}
