package com.bupt.infrastructure;

import org.junit.Assert;
import org.junit.Test;

import groovy.lang.Binding;
import groovy.util.GroovyScriptEngine;

/**
 * @author lhf2018
 * @date 2022/10/22 15:09
 */
public class GroovyInfServiceTest {

    @Test
    public void testGroovy2() throws Exception {
        GroovyScriptEngine engine = new GroovyScriptEngine("src/test/java/com/bupt/infrastructure/groovy-rule");
        Object result1 = engine.run("demo_001.groovy", new Binding());
        Assert.assertEquals("123123", result1);
    }

}