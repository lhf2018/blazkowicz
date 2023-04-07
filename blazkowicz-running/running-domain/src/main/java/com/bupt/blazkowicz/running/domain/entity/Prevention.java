package com.bupt.blazkowicz.running.domain.entity;

import java.util.List;

import com.bupt.blazkowicz.domain.share.anno.AggRoot;
import com.bupt.blazkowicz.domain.share.entity.BusinessIdentity;
import com.bupt.blazkowicz.domain.share.entity.PreventionType;
import com.bupt.blazkowicz.domain.share.entity.Rule;
import com.bupt.blazkowicz.domain.share.entity.Status;
import com.bupt.blazkowicz.running.domain.bridge.RunningDomainBridge;
import com.bupt.blazkowicz.running.domain.inf.RuleEngineInfService;
import com.bupt.blazkowicz.running.domain.support.rule.IdentityResultResp;
import com.bupt.blazkowicz.running.domain.support.rule.RuleReq;
import com.google.common.collect.Lists;

import lombok.Getter;

/**
 * @author lhf2018
 * @date 2022/11/13 23:03
 */
@Getter
@AggRoot
public class Prevention {
    /** 业务身份 */
    private BusinessIdentity businessIdentity;
    /** 防控场景 */
    private PreventionType preventionType;

    public Prevention(BusinessIdentity businessIdentity, PreventionType preventionType) {
        this.businessIdentity = businessIdentity;
        this.preventionType = preventionType;
    }

    public List<IdentityResultResp> run(Object leftParam) {
        List<RunningStrategy> runningStrategyList = RunningDomainBridge.getAdapter(RuleEngineInfService.class)
            .getRunningStrategyList(businessIdentity.name(), preventionType.name());

        List<IdentityResultResp> identityResultRespList = Lists.newArrayList();
        runningStrategyList.forEach(runningStrategy -> {
            Rule rule = runningStrategy.getRule();
            RuleReq ruleReq = RuleReq.builder().leftParam(leftParam).conditionList(rule.getConditionList())
                .logic(rule.getLogic()).build();
            Status status = RunningDomainBridge.getAdapter(RuleEngineInfService.class).run(ruleReq);

            IdentityResultResp identityResultResp = new IdentityResultResp();
            identityResultResp.setStatus(status);
            identityResultResp.setRuleName(rule.getRuleName());
            identityResultRespList.add(identityResultResp);
        });
        // todo 增加处置的处理
        return identityResultRespList;
    }
}
