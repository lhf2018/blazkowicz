package com.bupt.running.domain.entity;

import java.util.List;

import com.bupt.domain.share.entity.Rule;
import com.bupt.running.domain.bridge.RunningDomainBridge;
import com.bupt.running.domain.resp.PreventionResultResp;
import com.bupt.running.domain.support.rule.IdentityResultResp;
import com.bupt.running.domain.support.rule.RulePort;
import com.bupt.running.domain.support.rule.RuleResp;
import com.google.common.collect.Lists;

import lombok.Getter;

/**
 * @author lhf2018
 * @date 2022/10/29 16:28
 */
@Getter
public class Prevention {
    public List<PreventionResultResp> run(String businessIdentity, String preventionType) {
        List<RuleResp> ruleRespList =
            RunningDomainBridge.getAdapter(RulePort.class).getRunningFullRuleRespList(businessIdentity, preventionType);

        List<PreventionResultResp> result = Lists.newArrayList();
        ruleRespList.forEach(ruleResp -> {
            IdentityResultResp identityResultResp = RunningDomainBridge.getAdapter(RulePort.class)
                .runRule(new Rule(ruleResp.getName(), ruleResp.getScript()), ruleResp.getParams());
            result.add(PreventionResultResp.builder().name(ruleResp.getName())
                .status(identityResultResp.getStatus().name()).build());
        });
        return result;
    }
}
