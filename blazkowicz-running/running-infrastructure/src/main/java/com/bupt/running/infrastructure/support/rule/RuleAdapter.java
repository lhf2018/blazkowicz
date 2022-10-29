package com.bupt.running.infrastructure.support.rule;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bupt.domain.share.entity.Rule;
import com.bupt.domain.share.entity.Status;
import com.bupt.running.domain.inf.RuleEngineInfService;
import com.bupt.running.domain.support.rule.IdentityResultResp;
import com.bupt.running.domain.support.rule.RulePort;
import com.bupt.running.domain.support.rule.RuleResp;
import com.bupt.running.infrastructure.query.RuleQueryService;
import com.bupt.running.infrastructure.resp.RuleParamResp;
import com.bupt.running.infrastructure.resp.RuleScriptResp;
import com.google.common.collect.Lists;

/**
 * @author lhf2018
 * @date 2022/10/29 16:45
 */
@Component
public class RuleAdapter implements RulePort {
    @Autowired
    private RuleQueryService ruleQueryService;
    @Autowired
    private RuleEngineInfService ruleEngineInfService;

    @Override
    public List<RuleResp> getRunningFullRuleRespList(String businessIdentity, String preventionType) {
        List<String> res = ruleQueryService.getRuleList(businessIdentity, preventionType);

        List<RuleResp> ruleRespList = Lists.newArrayList();
        res.forEach(ruleName -> {
            RuleResp ruleResp = new RuleResp();
            RuleParamResp ruleParamResp = ruleQueryService.getRuleParamResp(businessIdentity, preventionType, ruleName);
            RuleScriptResp ruleScriptResp = ruleQueryService.getRuleScriptResp(businessIdentity, preventionType, ruleName);
            ruleResp.setScript(ruleScriptResp.getScript());
            ruleResp.setParams(ruleParamResp.getParams());
            ruleResp.setName(ruleName);
            ruleRespList.add(ruleResp);
        });
        return ruleRespList;
    }

    @Override
    public IdentityResultResp runRule(Rule rule, Object[] params) {
        IdentityResultResp identityResultResp = new IdentityResultResp();
        identityResultResp.setRuleName(rule.getName());

        Status status = ruleEngineInfService.runRule(rule, params);
        identityResultResp.setStatus(status);
        return identityResultResp;
    }
}
