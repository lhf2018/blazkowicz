package com.bupt.blazkowicz.common;

import org.junit.Test;

import com.bupt.ApplicationTest;
import com.bupt.blazkowicz.common.utils.LogicUtil;

/**
 * @author lhf2018
 * @date 2023/4/7 23:21
 */
public class LogicUtilTest extends ApplicationTest {
    @Test
    public void testLogic() {
        System.out.println(LogicUtil.compute("true&&true&&true"));
    }
}
