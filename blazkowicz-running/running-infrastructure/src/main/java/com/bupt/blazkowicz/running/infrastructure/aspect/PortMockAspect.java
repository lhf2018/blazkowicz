package com.bupt.blazkowicz.running.infrastructure.aspect;

import com.bupt.blazkowicz.common.utils.MockSwitch;
import com.bupt.blazkowicz.domain.share.entity.*;
import com.bupt.blazkowicz.running.domain.entity.RunningStrategy;
import com.google.common.collect.Lists;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.List;

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
        RuleScript ruleScript = new RuleScript(SCRIPT, RuleScriptType.GROOVY);
        List<RequiredValue> requiredValueList = Lists.newArrayList();
        requiredValueList.add(new RequiredValue(RequiredValueType.CONSTANT, "param", "test"));
        Condition condition = new Condition(123, ruleScript, requiredValueList, LeftParamType.ACCOUNT);
        return new Rule("测试", Lists.newArrayList(condition), "123");
    }
}
