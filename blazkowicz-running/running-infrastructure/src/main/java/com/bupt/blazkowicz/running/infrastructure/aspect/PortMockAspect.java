package com.bupt.blazkowicz.running.infrastructure.aspect;

import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.bupt.blazkowicz.common.utils.MockSwitch;
import com.bupt.blazkowicz.domain.share.entity.*;
import com.bupt.blazkowicz.domain.share.factory.RuleFactory;
import com.bupt.blazkowicz.running.domain.entity.RunningStrategy;
import com.google.common.collect.Lists;

/**
 * @author lhf2018
 * @date 2022/11/5 18:47
 */
@Component
@Aspect
public class PortMockAspect {
    @Pointcut("execution(public * com.bupt.blazkowicz.running.infrastructure.inf.RuleEngineInfServiceImpl.getRunningStrategyList(..))")
    public void getRuleList() {}

    @Around("getRuleList()")
    public Object mockGetRuleList(ProceedingJoinPoint pjp) throws Throwable {
        if (MockSwitch.MOCK) {
            RunningStrategy runningStrategy = new RunningStrategy(getRule());
            return Lists.newArrayList(runningStrategy);
        } else {
            return pjp.proceed();
        }
    }

    private static final String SCRIPT = "class Main {\n" + "    static boolean run(String userId, String param) {\n"
        + "        if (userId == \"114515\") {\n" + "            return param == \"test\"\n" + "        } else {\n"
        + "            return false;\n" + "        }\n" + "    }\n" + "}";

    private Rule getRule() {
        ConditionScript conditionScript = new ConditionScript(SCRIPT, ConditionScriptType.GROOVY);
        List<RequiredValue> requiredValueList = Lists.newArrayList();
        requiredValueList.add(new RequiredValue(RequiredValueType.CONSTANT, "param", "test"));
        Condition condition = new Condition(1, conditionScript, requiredValueList, LeftParamType.ACCOUNT);
        return RuleFactory.create("测试", Lists.newArrayList(condition), "1&2&3")
    }
}
