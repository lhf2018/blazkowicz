package com.bupt.blazkowicz.common.utils;

import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.JexlExpression;
import org.apache.commons.jexl3.MapContext;

/**
 * @author lhf2018
 * @date 2023/1/15 0:17
 */
public class LogicUtil {

    private static final JexlEngine JEXL = new JexlBuilder().create();

    public static boolean compute(String logicStr) {
        JexlExpression jexlExpression = JEXL.createExpression(logicStr);
        MapContext jexlContext = new MapContext();
        return (boolean)jexlExpression.evaluate(jexlContext);
    }
}
