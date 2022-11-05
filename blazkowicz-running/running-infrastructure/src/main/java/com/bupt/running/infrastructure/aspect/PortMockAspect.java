package com.bupt.running.infrastructure.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.bupt.common.utils.MockSwitch;
import com.bupt.running.domain.support.rule.RuleResp;
import com.google.common.collect.Lists;

/**
 * @author lhf2018
 * @date 2022/11/5 18:47
 */
@Component
@Aspect
public class PortMockAspect {
    @Pointcut("execution(public * com.bupt.running.infrastructure.inf.RuleEngineInfServiceImpl.getRuleRespList(..))")
    public void getRuleList() {}

    @Around("getRuleList()")
    public Object mockGetRuleList(ProceedingJoinPoint pjp) throws Throwable {
        if (MockSwitch.MOCK) {
            RuleResp resp = new RuleResp();
            resp.setId("12333");
            resp.setParams(new Object[] {"test"});
            resp.setScript("class Main {\n" + "    static boolean run(String param) {\n"
                + "        return param == \"test\"\n" + "    }\n" + "}");
            return Lists.newArrayList(resp);
        } else {
            return pjp.proceed();
        }
    }
}
