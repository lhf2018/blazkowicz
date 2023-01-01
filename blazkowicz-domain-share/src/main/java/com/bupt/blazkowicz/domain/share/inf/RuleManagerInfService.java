package com.bupt.blazkowicz.domain.share.inf;

import com.bupt.blazkowicz.domain.share.entity.Rule;

/**
 * 配置态管理规则
 * 
 * @author lhf2018
 * @date 2022/10/6 18:50
 */
public interface RuleManagerInfService {

    /**
     * 创建规则
     * 
     * @param rule
     */
    void createRule(Rule rule);

    /**
     * 更新规则的条件
     * 
     * @param rule
     */
    void updateRuleCondition(Rule rule);

}
