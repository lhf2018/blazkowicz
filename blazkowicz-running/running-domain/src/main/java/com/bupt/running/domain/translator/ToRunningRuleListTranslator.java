package com.bupt.running.domain.translator;

import java.util.List;
import java.util.stream.Collectors;

import com.bupt.running.domain.entity.RunningRule;
import com.bupt.running.domain.support.rule.RuleResp;

/**
 * @author lhf2018
 * @date 2022/11/19 22:17
 */
public class ToRunningRuleListTranslator {
    public static List<RunningRule> toRunningRuleList(List<RuleResp> ruleRespList) {
        return ruleRespList.stream().map(ToRunningRuleListTranslator::toRunningRule).collect(Collectors.toList());
    }

    private static RunningRule toRunningRule(RuleResp ruleResp) {
        return new RunningRule(ruleResp.getId(), ruleResp.getScript(), ruleResp.getParams());
    }
}
