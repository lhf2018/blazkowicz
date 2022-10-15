package com.bupt.domain.share.inf;

import com.bupt.domain.share.entity.Rule;

import java.util.List;

/**
 * @author lhf2018
 * @date 2022/10/6 18:50
 */
public interface RuleEngineInfService {
    /**
     * 获取规则
     * 
     * @param businessIdentity
     * @param preventionType
     * @param name
     * @return
     */
    List<Rule> getRuleList(String businessIdentity, String preventionType, String name);

    /**
     * 创建规则
     * 
     * @param rule
     */
    void createRule(Rule rule);
}
