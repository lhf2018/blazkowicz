package com.bupt.running.domain.support.rule;

import java.util.List;

import com.bupt.domain.share.entity.Rule;

/**
 * @author lhf2018
 * @date 2022/10/29 16:35
 */
public interface RulePort {

    /**
     * 获取某防控类型完整风控规则
     * 
     * @param businessIdentity
     * @param preventionType
     * @return
     */
    List<RuleResp> getRunningFullRuleRespList(String businessIdentity, String preventionType);

    /**
     * 运行规则
     * 
     * @param rule
     * @param params
     * @return
     */
    IdentityResultResp runRule(Rule rule, Object[] params);
}
