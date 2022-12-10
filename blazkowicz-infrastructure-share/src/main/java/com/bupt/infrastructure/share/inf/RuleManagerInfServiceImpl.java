package com.bupt.infrastructure.share.inf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bupt.domain.share.entity.Rule;
import com.bupt.domain.share.inf.RuleManagerInfService;

/**
 * @author lhf2018
 * @date 2022/10/6 18:50
 */
@Component
public class RuleManagerInfServiceImpl implements RuleManagerInfService {
    @Autowired
    private NosqlInfService nosqlInfService;

    @Override
    public void createRule(Rule rule) {
        // todo 写道一个公用的地方，比如nosql系统
        // todo 且只更新规则
    }

    @Override
    public void updateRuleCondition(Rule rule) {

    }
}
