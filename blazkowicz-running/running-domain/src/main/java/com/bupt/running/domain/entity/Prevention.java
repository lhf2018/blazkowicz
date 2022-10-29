package com.bupt.running.domain.entity;

import com.bupt.domain.share.entity.Rule;
import com.bupt.running.domain.bridge.RunningDomainBridge;
import com.bupt.running.domain.resp.PreventionResultResp;
import com.bupt.running.domain.support.rule.IdentityResp;
import com.bupt.running.domain.support.rule.RulePort;
import com.bupt.running.domain.support.rule.RunningFullRuleResp;

import lombok.Getter;

/**
 * @author lhf2018
 * @date 2022/10/29 16:28
 */
@Getter
public class Prevention {
    public PreventionResultResp run(String businessIdentity, String preventionPointType, String ruleName) {
        RunningFullRuleResp resp = RunningDomainBridge.getAdapter(RulePort.class)
            .getRunningFullRuleResp(businessIdentity, preventionPointType, ruleName);

        IdentityResp identityResp = RunningDomainBridge.getAdapter(RulePort.class)
            .runRule(new Rule(resp.getName(), resp.getScript()), resp.getParams());
        return PreventionResultResp.builder().name(ruleName).status(identityResp.getStatus().name()).build();
    }
}
