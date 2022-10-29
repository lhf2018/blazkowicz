package com.bupt.running.infrastructure.support.rule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bupt.domain.share.entity.Rule;
import com.bupt.domain.share.entity.Status;
import com.bupt.running.domain.inf.RuleEngineInfService;
import com.bupt.running.domain.support.rule.IdentityResp;
import com.bupt.running.domain.support.rule.RulePort;
import com.bupt.running.domain.support.rule.RunningFullRuleResp;
import com.bupt.running.infrastructure.query.RuleQueryService;
import com.bupt.running.infrastructure.resp.RuleParamResp;
import com.bupt.running.infrastructure.resp.RuleScriptResp;

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
    public RunningFullRuleResp getRunningFullRuleResp(String businessIdentity, String preventionType, String ruleName) {
        RunningFullRuleResp runningFullRuleResp = new RunningFullRuleResp();
        runningFullRuleResp.setName(ruleName);

        RuleParamResp ruleParamResp = ruleQueryService.getRuleParamResp(businessIdentity, preventionType, ruleName);
        runningFullRuleResp.setParams(ruleParamResp.getParams());
        RuleScriptResp ruleScriptResp = ruleQueryService.getRuleScript(businessIdentity, preventionType, ruleName);
        runningFullRuleResp.setScript(ruleScriptResp.getScript());
        return runningFullRuleResp;
    }

    @Override
    public IdentityResp runRule(Rule rule, Object[] params) {
        IdentityResp identityResp = new IdentityResp();
        identityResp.setRuleName(rule.getName());

        Status status = ruleEngineInfService.runRule(rule, params);
        identityResp.setStatus(status);
        return identityResp;
    }
}
