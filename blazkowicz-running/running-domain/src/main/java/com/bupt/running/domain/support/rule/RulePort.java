package com.bupt.running.domain.support.rule;

import com.bupt.domain.share.entity.Rule;

/**
 * @author lhf2018
 * @date 2022/10/29 16:35
 */
public interface RulePort {

    /**
     * 获取完整运行的规则，包括脚本和参数
     *
     * @param businessIdentity
     * @param preventionType
     * @param ruleName
     * @return
     */
    RunningFullRuleResp getRunningFullRuleResp(String businessIdentity, String preventionType, String ruleName);

    /**
     * 运行规则
     * 
     * @param rule
     * @param params
     * @return
     */
    IdentityResp runRule(Rule rule, Object[] params);
}
