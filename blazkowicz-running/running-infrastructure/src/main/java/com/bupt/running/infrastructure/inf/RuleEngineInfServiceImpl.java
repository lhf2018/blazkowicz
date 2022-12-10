package com.bupt.running.infrastructure.inf;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bupt.domain.share.entity.BusinessIdentity;
import com.bupt.domain.share.entity.PreventionType;
import com.bupt.domain.share.entity.Rule;
import com.bupt.domain.share.entity.Status;
import com.bupt.infrastructure.share.inf.NosqlInfService;
import com.bupt.infrastructure.share.query.RuleQueryService;
import com.bupt.running.domain.entity.RunningStrategy;
import com.bupt.running.domain.inf.RuleEngineInfService;
import com.bupt.running.domain.support.rule.RuleReq;
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
    @Autowired
    private NosqlInfService nosqlInfService;

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

    /**
     * 返回策略列表
     *
     * @param businessIdentity
     * @param preventionType
     * @return
     */
    @Override
    public List<RunningStrategy> getRunningStrategyList(String businessIdentity, String preventionType) {
        List<Rule> ruleList = ruleQueryService.getRuleRespList(BusinessIdentity.valueOf(businessIdentity),
            PreventionType.valueOf(preventionType));
        List<RunningStrategy> runningStrategyList = Lists.newArrayList();
        ruleList.forEach(rule -> {
            runningStrategyList.add(new RunningStrategy(rule));
        });
        return runningStrategyList;
    }
}
