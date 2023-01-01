package com.bupt.blazkowicz.controller.share.aspect;

import java.lang.reflect.Parameter;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.commons.collections.CollectionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.bupt.blazkowicz.client.share.dto.result.ResultDTO;
import com.bupt.blazkowicz.common.exception.BlazkowiczException;
import com.bupt.blazkowicz.common.exception.Code;
import com.bupt.blazkowicz.common.valid.ParamValidation;
import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;

/**
 * @author lhf2018
 * @date 2022/11/13 18:04
 */
@Slf4j
@Aspect
@Component
@Order(1)
public class ApiAspect {
    @Autowired
    private Validator validator;

    @Pointcut("@within(org.springframework.stereotype.Service)")
    public void rpc() {}

    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    public void http() {}

    @Around("rpc()")
    public Object catchRcpUnexpectedExp(ProceedingJoinPoint pjp) {
        return catchUnexpectedExp(pjp);
    }

    @Around("http()")
    public Object catchHttpUnexpectedExp(ProceedingJoinPoint pjp) {
        return catchUnexpectedExp(pjp);
    }

    private Object catchUnexpectedExp(ProceedingJoinPoint pjp) {
        MethodSignature methodSignature = (MethodSignature)pjp.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String method = methodSignature.getMethod().getName();

        Object[] param = filterParam(pjp.getArgs());
        log.info("api request, className={}, method={}, param={}", className, method,
            JSON.toJSONString(param, SerializerFeature.IgnoreNonFieldGetter));
        try {
            checkParam(pjp);
            Object proceed = pjp.proceed();
            log.info("api respond success, className={}, method={}, param={}", className, method,
                JSON.toJSONString(param, SerializerFeature.IgnoreNonFieldGetter));
            return proceed;
        } catch (Throwable throwable) {
            ResultDTO<Void> failResult = processException(throwable);
            log.error("api respond failed, className={}, method={}, param={}", className, method,
                JSON.toJSONString(param, SerializerFeature.IgnoreNonFieldGetter));
            return failResult;
        }
    }

    /**
     * 参数校验
     * 
     * @param pjp
     */
    private void checkParam(ProceedingJoinPoint pjp) {
        Parameter[] parameters = ((MethodSignature)pjp.getSignature()).getMethod().getParameters();
        Object[] args = pjp.getArgs();
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            Object arg = args[i];
            ParamValidation annotation = parameter.getAnnotation(ParamValidation.class);
            if (annotation == null) {
                continue;
            }
            Set<ConstraintViolation<Object>> validResult = validator.validate(arg);
            if (CollectionUtils.isNotEmpty(validResult)) {
                log.info("checkParam not pass, parameter={}, validResult={}", parameter, validResult);
                throw new BlazkowiczException(Code.UNEXPECTED_ERROR, validResult.toString());
            }

        }
    }

    /**
     * 异常处理
     * 
     * @param throwable
     * @return
     */
    private ResultDTO<Void> processException(Throwable throwable) {
        if (throwable instanceof BlazkowiczException) {
            BlazkowiczException blazkowiczException = (BlazkowiczException)throwable;
            log.error(blazkowiczException.getMessage(), blazkowiczException);
            return ResultDTO.buildFailResult(blazkowiczException.getCode(), blazkowiczException.getMsg());
        } else {
            log.error(throwable.getMessage(), throwable);
            return ResultDTO.buildFailResult(Code.UNEXPECTED_ERROR, throwable.getMessage());
        }
    }

    /**
     * 过滤log不用输出的参数
     * 
     * @param args
     * @return
     */
    private Object[] filterParam(Object[] args) {
        List<Object> objectList = Lists.newArrayList();
        for (Object object : args) {
            if (!(object instanceof HttpServletRequest) && !(object instanceof MultipartFile)) {
                objectList.add(object);
            }
        }
        return objectList.toArray();
    }

}
