package com.bupt.running.domain.entity;

import java.util.List;

import com.bupt.domain.share.entity.BusinessIdentity;
import com.bupt.domain.share.entity.PreventionType;
import com.bupt.domain.share.entity.Status;
import com.bupt.running.domain.bridge.RunningDomainBridge;
import com.bupt.running.domain.inf.RuleEngineInfService;
import com.bupt.running.domain.support.rule.IdentityResultResp;
import com.bupt.running.domain.support.rule.RuleReq;
import com.bupt.running.domain.support.rule.RuleResp;
import com.google.common.collect.Lists;

import lombok.Getter;

/**
 * @author lhf2018
 * @date 2022/11/13 23:03
 */
@Getter
public class Prevention {
    /** 业务身份 */
    private BusinessIdentity businessIdentity;
    /** 防控场景 */
    private PreventionType preventionType;
    /** 规则列表 */
    private List<RunningRule> runningRuleList;
    /** 最行执行结果 */
    private List<IdentityResultResp> latestResult;

    public Prevention(BusinessIdentity businessIdentity, PreventionType preventionType) {
        this.businessIdentity = businessIdentity;
        this.preventionType = preventionType;
    }

    public void run() {
        List<RuleResp> ruleRespList = RunningDomainBridge.getAdapter(RuleEngineInfService.class)
            .getRuleRespList(businessIdentity.name(), preventionType.name());
        // todo 更新，翻译runningRuleList
        List<IdentityResultResp> identityResultRespList = Lists.newArrayList();
        ruleRespList.forEach(ruleResp -> {
            IdentityResultResp identityResultResp = new IdentityResultResp();
            RuleReq ruleReq = RuleReq.builder().id(ruleResp.getId()).params(ruleResp.getParams())
                .script(ruleResp.getScript()).build();
            Status status = RunningDomainBridge.getAdapter(RuleEngineInfService.class).runRule(ruleReq);
            identityResultResp.setStatus(status);
            identityResultResp.setRuleName(ruleResp.getId());
            identityResultRespList.add(identityResultResp);
        });
        latestResult = identityResultRespList;
    }
}
