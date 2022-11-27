package com.bupt.running.infrastructure.translator;

import java.util.List;
import java.util.stream.Collectors;

import com.bupt.domain.share.entity.Rule;
import com.bupt.domain.share.resp.RuleResp;
import com.google.common.collect.Lists;

/**
 * @author lhf2018
 * @date 2022/11/19 22:07
 */
public class ToRuleRespListTranslator {
    public static List<RuleResp> toRuleRespList(List<Rule> ruleList) {
        return ruleList.stream().map(ToRuleRespListTranslator::toRuleResp).collect(Collectors.toList());
    }

    private static RuleResp toRuleResp(Rule rule) {
        RuleResp ruleResp = new RuleResp();
        ruleResp.setId(rule.getRuleId());
        ruleResp.setScript(rule.getRuleScript().getContent());
        ruleResp.setParams(Lists.newArrayList(null, rule.getRightParam().getValue()).toArray());
        return ruleResp;
    }
}
