package com.bupt.running.infrastructure.inf;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.groovy.control.CompilerConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

/**
 * @author lhf2018
 * @date 2022/10/22 15:07
 */
@Component
public class GroovyInfService {
    public static Map<String, GroovyObject> passedClassMap = new HashMap<>();

    private static GroovyClassLoader groovyClassLoader = null;

    static {
        CompilerConfiguration config = new CompilerConfiguration();
        config.setSourceEncoding("UTF-8");
        // 设置该GroovyClassLoader的父ClassLoader为当前线程的加载器(默认)
        groovyClassLoader = new GroovyClassLoader(Thread.currentThread().getContextClassLoader(), config);
    }

    private GroovyObject getGroovyObject(String script) {
        GroovyObject groovyObject = passedClassMap.get(DigestUtils.md5DigestAsHex(script.getBytes()));
        if (groovyObject == null) {
            Class<?> groovyClass = groovyClassLoader.parseClass(script);
            try {
                groovyObject = (GroovyObject)groovyClass.newInstance();
                passedClassMap.put(DigestUtils.md5DigestAsHex(script.getBytes()), groovyObject);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return groovyObject;
    }

    public Object run(String script, String method, Object[] params) {
        GroovyObject groovyObject = getGroovyObject(script);
        if (groovyObject != null) {
            return groovyObject.invokeMethod(method, params);
        } else {
            return null;
        }
    }
}
