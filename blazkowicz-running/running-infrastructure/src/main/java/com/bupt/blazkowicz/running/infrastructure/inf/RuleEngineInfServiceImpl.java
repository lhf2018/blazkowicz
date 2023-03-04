package com.bupt.blazkowicz.running.infrastructure.inf;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bupt.blazkowicz.common.utils.LogicUtil;
import com.bupt.blazkowicz.domain.share.entity.*;
import com.bupt.blazkowicz.infrastructure.share.inf.NosqlInfService;
import com.bupt.blazkowicz.infrastructure.share.query.RuleQueryService;
import com.bupt.blazkowicz.running.domain.entity.RunningStrategy;
import com.bupt.blazkowicz.running.domain.inf.RuleEngineInfService;
import com.bupt.blazkowicz.running.domain.support.rule.RuleReq;
import com.bupt.blazkowicz.running.domain.translator.ToParamObjectTranslator;
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
    public Status run(RuleReq ruleReq) {
        final String[] logic = {ruleReq.getLogic()};
        try {
            ruleReq.getConditionList().forEach(condition -> {
                List<RequiredValue> requiredValueList = condition.getRequiredValues();
                List<Object> paramList = Lists.newArrayList();
                paramList.add(ruleReq.getLeftParam());
                paramList.addAll(requiredValueList.stream().map(ToParamObjectTranslator::toParamObject)
                    .collect(Collectors.toList()));
                Object[] paramArray = paramList.toArray();
                Boolean result = (Boolean)groovyInfService.run(condition.getConditionScript().getContent(),
                    DEFAULT_METHOD, paramArray);
                logic[0] = logic[0].replace(condition.getConditionId().toString(), result.toString());
            });
        } catch (Throwable e) {
            return Status.ERROR;
        }
        return LogicUtil.compute(logic[0]) ? Status.NOT_MEET : Status.MEET;
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
