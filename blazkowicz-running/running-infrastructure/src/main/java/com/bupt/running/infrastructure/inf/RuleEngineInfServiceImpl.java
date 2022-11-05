package com.bupt.running.infrastructure.inf;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bupt.domain.share.entity.Status;
import com.bupt.infrastructure.share.query.RuleQueryService;
import com.bupt.infrastructure.share.resp.RuleParamResp;
import com.bupt.infrastructure.share.resp.RuleScriptResp;
import com.bupt.running.domain.inf.RuleEngineInfService;
import com.bupt.running.domain.support.rule.RuleReq;
import com.bupt.running.domain.support.rule.RuleResp;
import com.google.common.collect.Lists;

/**
 * @author lhf2018
 * @date 2022/10/22 18:28
 */
@Component
public class RuleEngineInfServiceImpl implements RuleEngineInfService {
    @Autowired
    private GroovyInfService groovyInfService;
    @Autowired
    private RuleQueryService ruleQueryService;

    private static final String DEFAULT_METHOD = "run";

    @Override
    public List<RuleResp> getRuleRespList(String businessIdentity, String preventionType) {
        List<String> ruleIds = ruleQueryService.getRuleList(businessIdentity, preventionType);
        List<RuleResp> ruleRespList = Lists.newArrayList();
        ruleIds.forEach(ruleId -> {
            RuleParamResp ruleParamResp = ruleQueryService.getRuleParamResp(businessIdentity, preventionType, ruleId);
            RuleScriptResp ruleScriptResp =
                ruleQueryService.getRuleScriptResp(businessIdentity, preventionType, ruleId);
            RuleResp ruleResp = RuleResp.builder().id(ruleId).params(ruleParamResp.getParams())
                .script(ruleScriptResp.getScript()).build();
            ruleRespList.add(ruleResp);
        });

        return ruleRespList;
    }

    @Override
    public Status runRule(RuleReq ruleReq) {
        try {
            Boolean result = (Boolean)groovyInfService.run(ruleReq.getScript(), DEFAULT_METHOD, ruleReq.getParams());
            return result.equals(Boolean.TRUE) ? Status.NOT_MEET : Status.MEET;
        } catch (Throwable e) {
            return Status.ERROR;
        }
    }
}
