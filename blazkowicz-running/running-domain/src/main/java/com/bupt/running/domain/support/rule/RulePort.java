package com.bupt.running.domain.support.rule;

import java.util.List;

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
    List<RuleResp> getRuleRespList(String businessIdentity, String preventionType);

    /**
     * 运行某一业务身份对应的防控点的所有策略
     * 
     * @param businessIdentity
     * @param preventionType
     * @return
     */
    List<IdentityResultResp> run(String businessIdentity, String preventionType);
}
