package com.bupt.blazkowicz.configuration.domain.bridge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bupt.blazkowicz.common.beanfinder.Finder;

/**
 * @author lhf2018
 * @date 2022/10/6 18:52
 */
@Component
public class ConfigurationDomainBridge {
    private static Finder finder;
    public static final String CURRENT_PACKAGE = "com.bupt.blazkowicz.configuration.domain";

    @Autowired
    public void setPrototypeBeanFinder(Finder finder) {
        ConfigurationDomainBridge.finder = finder;
    }

    public static <T> T getAdapter(Class<T> clazz) {
        if (!clazz.getName().startsWith(CURRENT_PACKAGE)) {
            throw new RuntimeException();
        }
        return (T)finder.getBean(clazz);
    }
}
