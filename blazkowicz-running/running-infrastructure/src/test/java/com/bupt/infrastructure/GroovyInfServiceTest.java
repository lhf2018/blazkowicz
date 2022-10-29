package com.bupt.infrastructure;

import org.junit.Assert;
import org.junit.Test;

import com.bupt.running.infrastructure.inf.GroovyInfService;

import groovy.lang.Binding;
import groovy.util.GroovyScriptEngine;

/**
 * @author lhf2018
 * @date 2022/10/22 15:09
 */
public class GroovyInfServiceTest {

    @Test
    public void testGroovy1() throws Exception {
        GroovyScriptEngine engine = new GroovyScriptEngine("src/test/java/com/bupt/infrastructure/groovy-rule");
        Object result1 = engine.run("demo_001.groovy", new Binding());
        Assert.assertEquals("123123", result1);
    }

    @Test
    public void testGroovy2() {
        GroovyInfService groovyInfService = new GroovyInfService();
        String script =
            "class HelloWorld {\n" + "    static boolean main() {\n" + "        return false;\n" + "    }\n" + "}";
        Object result = groovyInfService.run(script, "main", null);
        Assert.assertNotEquals(true, result);
    }

    @Test
    public void testGroovy3() {
        GroovyInfService groovyInfService = new GroovyInfService();
        String script = "class HelloWorld {\n" + "    static boolean main(boolean flag) {\n" + "        return flag;\n"
            + "    }\n" + "}";
        boolean flag = (boolean)groovyInfService.run(script, "main", new Object[] {true});
        Assert.assertTrue(flag);
        flag = (boolean)groovyInfService.run(script, "main", new Object[] {false});
        Assert.assertFalse(flag);
    }
}