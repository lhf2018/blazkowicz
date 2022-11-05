package com.bupt.running.infrastructure.support.rule;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bupt.domain.share.entity.Status;
import com.bupt.running.domain.inf.RuleEngineInfService;
import com.bupt.running.domain.support.rule.IdentityResultResp;
import com.bupt.running.domain.support.rule.RulePort;
import com.bupt.running.domain.support.rule.RuleReq;
import com.bupt.running.domain.support.rule.RuleResp;
import com.google.common.collect.Lists;

/**
 * 规则管理
 * 
 * @author lhf2018
 * @date 2022/10/29 16:45
 */
@Component
public class RuleAdapter implements RulePort {

    @Autowired
    private RuleEngineInfService ruleEngineInfService;

    @Override
    public List<RuleResp> getRuleRespList(String businessIdentity, String preventionType) {
        return ruleEngineInfService.getRuleRespList(businessIdentity, preventionType);
    }

    @Override
    public List<IdentityResultResp> run(String businessIdentity, String preventionType) {
        List<RuleResp> ruleRespList = ruleEngineInfService.getRuleRespList(businessIdentity, preventionType);
        List<IdentityResultResp> identityResultRespList = Lists.newArrayList();
        ruleRespList.forEach(ruleResp -> {
            IdentityResultResp identityResultResp = new IdentityResultResp();
            RuleReq ruleReq = RuleReq.builder().id(ruleResp.getId()).params(ruleResp.getParams())
                .script(ruleResp.getScript()).build();
            Status status = ruleEngineInfService.runRule(ruleReq);
            identityResultResp.setStatus(status);
            identityResultResp.setRuleName(ruleResp.getId());
            identityResultRespList.add(identityResultResp);
        });

        return identityResultRespList;
    }
}
