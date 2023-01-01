package com.bupt.blazkowicz.running.domain.translator;

import com.bupt.blazkowicz.domain.share.entity.Condition;

/**
 * @author lhf2018
 * @date 2022/12/11 0:37
 */
public class ToParamObjectTranslator {
    public static Object toParamObject(Condition condition) {
        return condition.getValue();
    }
}
