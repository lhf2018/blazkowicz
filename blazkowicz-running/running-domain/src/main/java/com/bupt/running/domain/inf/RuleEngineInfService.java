package com.bupt.running.domain.inf;

import java.util.List;

import com.bupt.domain.share.entity.Rule;
import com.bupt.domain.share.entity.Status;

/**
 * @author lhf2018
 * @date 2022/10/22 18:27
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
     * 运行规则
     * 
     * @param rule
     * @param args
     * @return
     */
    Status runRule(Rule rule, Object[] args);
}
