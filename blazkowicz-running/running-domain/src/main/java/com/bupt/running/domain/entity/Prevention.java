package com.bupt.running.domain.entity;

import java.util.List;

import com.bupt.domain.share.anno.AggRoot;
import com.bupt.domain.share.entity.BusinessIdentity;
import com.bupt.domain.share.entity.Condition;
import com.bupt.domain.share.entity.PreventionType;
import com.bupt.domain.share.entity.Status;
import com.bupt.running.domain.bridge.RunningDomainBridge;
import com.bupt.running.domain.inf.RuleEngineInfService;
import com.bupt.running.domain.support.rule.IdentityResultResp;
import com.bupt.running.domain.support.rule.RuleReq;
import com.bupt.running.domain.translator.ToParamObjectTranslator;
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
    /** 规则列表 */
    private List<RunningStrategy> runningStrategyList;

    public Prevention(BusinessIdentity businessIdentity, PreventionType preventionType) {
        this.businessIdentity = businessIdentity;
        this.preventionType = preventionType;
        init();
    }

    private void init() {
        this.runningStrategyList = RunningDomainBridge.getAdapter(RuleEngineInfService.class)
            .getRunningStrategyList(businessIdentity.name(), preventionType.name());
    }

    public List<IdentityResultResp> run(Object leftParam) {
        List<IdentityResultResp> identityResultRespList = Lists.newArrayList();

        runningStrategyList.forEach(runningStrategy -> {
            IdentityResultResp identityResultResp = new IdentityResultResp();
            List<Condition> conditionList = runningStrategy.getRule().getConditions();
            Object[] params = conditionList.stream().map(ToParamObjectTranslator::toParamObject).toArray();

            RuleReq ruleReq = RuleReq.builder().rightParams(params).leftParam(leftParam)
                .script(runningStrategy.getRule().getRuleScript().getContent()).build();
            Status status = RunningDomainBridge.getAdapter(RuleEngineInfService.class).runRule(ruleReq);
            identityResultResp.setStatus(status);
            identityResultRespList.add(identityResultResp);
        });
        return identityResultRespList;
    }
}
