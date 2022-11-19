package com.bupt.running.infrastructure.translator;

import java.util.List;
import java.util.stream.Collectors;

import com.bupt.infrastructure.share.resp.RunningRuleResp;
import com.bupt.running.domain.support.rule.RuleResp;

/**
 * @author lhf2018
 * @date 2022/11/19 22:07
 */
public class ToRuleRespListTranslator {
    public static List<RuleResp> toRuleRespList(List<RunningRuleResp> runningRuleRespList) {
        return runningRuleRespList.stream().map(ToRuleRespListTranslator::toRuleResp).collect(Collectors.toList());
    }

    private static RuleResp toRuleResp(RunningRuleResp runningRuleResp) {
        RuleResp ruleResp = new RuleResp();
        ruleResp.setId(runningRuleResp.getRuleId());
        ruleResp.setScript(runningRuleResp.getScript());
        ruleResp.setParams(runningRuleResp.getParams());
        return ruleResp;
    }
}
