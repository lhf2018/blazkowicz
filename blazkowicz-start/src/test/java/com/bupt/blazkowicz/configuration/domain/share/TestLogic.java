package com.bupt.blazkowicz.configuration.domain.share;

import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.JexlExpression;
import org.apache.commons.jexl3.MapContext;
import org.junit.Assert;
import org.junit.Test;

import com.bupt.ApplicationTest;

/**
 * @author lhf2018
 * @date 2023/1/15 0:02
 */
public class TestLogic extends ApplicationTest {
    @Test
    public void test0() {
        String str = "(true  || false) && !false || true";
        JexlBuilder jexlBuilder = new JexlBuilder();
        JexlEngine jexl = jexlBuilder.create();
        JexlExpression jexlExpression = jexl.createExpression(str);
        MapContext jexlContext = new MapContext();
        Object result = jexlExpression.evaluate(jexlContext);
        Assert.assertTrue((boolean)result);
    }
}
