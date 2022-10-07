package com.bupt.domain.share.bridge;

import com.bupt.common.beanfinder.Finder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author lhf2018
 * @date 2022/10/6 18:52
 */
@Component
public class DomainShareBridge {
    private static Finder finder;
    public static final String CURRENT_PACKAGE = "com.bupt.domain.share";

    @Autowired
    public void setPrototypeBeanFinder(Finder finder) {
        DomainShareBridge.finder = finder;
    }

    public static <T> T getAdapter(Class<T> clazz) {
        if (!clazz.getName().startsWith(CURRENT_PACKAGE)) {
            throw new RuntimeException();
        }
        return (T)finder.getBean(clazz);
    }
}