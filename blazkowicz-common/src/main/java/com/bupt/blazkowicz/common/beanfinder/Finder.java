package com.bupt.blazkowicz.common.beanfinder;

import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;

/**
 * @author lhf2018
 * @date 2022/10/6 18:56
 */
@Component
public class Finder implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public <T> Object getBean(Class<T> clazz) {
        return getBean(clazz, Maps.<String, Object>newHashMap());
    }

    public Object getBean(Class<?> clazz, Map<String, Object> props) {
        try {
            Object obj = applicationContext.getBean(clazz);
            for (Map.Entry<String, Object> entry : props.entrySet()) {
                BeanUtils.setProperty(obj, entry.getKey(), entry.getValue());
            }
            return obj;
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }
}
