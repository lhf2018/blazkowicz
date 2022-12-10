package com.bupt.running.domain.inf;

import java.util.List;

import com.bupt.domain.share.entity.Status;
import com.bupt.running.domain.entity.RunningStrategy;
import com.bupt.running.domain.support.rule.RuleReq;

/**
 * 运行态规则引擎
 * 
 * @author lhf2018
 * @date 2022/10/22 18:27
 */
public interface RuleEngineInfService {
    /**
     * 获取规则
     *
     * @param businessIdentity
     * @param preventionType
     * @return
     */
    List<RunningStrategy> getRunningStrategyList(String businessIdentity, String preventionType);

    /**
     * 运行规则
     * 
     * @param ruleReq
     * @return
     */
    Status runRule(RuleReq ruleReq);
}
