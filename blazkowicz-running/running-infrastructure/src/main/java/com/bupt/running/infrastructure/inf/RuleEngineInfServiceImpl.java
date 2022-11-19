package com.bupt.running.infrastructure.inf;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bupt.domain.share.entity.Status;
import com.bupt.infrastructure.share.query.RuleQueryService;
import com.bupt.infrastructure.share.resp.RunningRuleResp;
import com.bupt.running.domain.inf.RuleEngineInfService;
import com.bupt.running.domain.support.rule.RuleReq;
import com.bupt.running.domain.support.rule.RuleResp;
import com.bupt.running.infrastructure.translator.ToRuleRespListTranslator;

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
    public Status runRule(RuleReq ruleReq) {
        Object[] params = new Object[ruleReq.getRightParams().length + 1];
        params[0] = ruleReq.getLeftParam();
        System.arraycopy(ruleReq.getRightParams(), 0, params, 1, ruleReq.getRightParams().length);
        try {
            Boolean result = (Boolean)groovyInfService.run(ruleReq.getScript(), DEFAULT_METHOD, params);
            return result.equals(Boolean.TRUE) ? Status.NOT_MEET : Status.MEET;
        } catch (Throwable e) {
            return Status.ERROR;
        }
    }

    @Override
    public List<RuleResp> getRuleRespList(String businessIdentity, String preventionType) {
        List<RunningRuleResp> runningRuleRespList =
            ruleQueryService.getRunningRuleRespList(businessIdentity, preventionType);
        return ToRuleRespListTranslator.toRuleRespList(runningRuleRespList);
    }
}
