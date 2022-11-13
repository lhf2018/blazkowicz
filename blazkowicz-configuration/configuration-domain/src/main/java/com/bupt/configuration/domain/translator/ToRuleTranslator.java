package com.bupt.configuration.domain.translator;

import java.util.Random;

import com.bupt.configuration.domain.entity.Condition;
import com.bupt.domain.share.entity.ConfigurationRule;

/**
 * @author lhf2018
 * @date 2022/10/15 17:14
 */
public class ToRuleTranslator {
    public static ConfigurationRule toRule(Condition condition) {
        // todo sequence
        return new ConfigurationRule(new Random().nextLong(), condition.getScript(), condition.getName());
    }
}
