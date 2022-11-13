package com.bupt.configuration.domain.inf;

import com.bupt.domain.share.entity.ConfigurationRule;

/**
 * @author lhf2018
 * @date 2022/10/6 18:50
 */
public interface RuleManagerInfService {

    /**
     * 创建规则
     * 
     * @param configurationRule
     */
    void createRule(ConfigurationRule configurationRule);

}
