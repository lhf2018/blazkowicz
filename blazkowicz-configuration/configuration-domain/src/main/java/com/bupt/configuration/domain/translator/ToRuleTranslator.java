package com.bupt.configuration.domain.translator;

import com.bupt.configuration.domain.entity.Condition;
import com.bupt.domain.share.entity.Rule;

/**
 * @author lhf2018
 * @date 2022/10/15 17:14
 */
public class ToRuleTranslator {
    public static Rule toRule(Condition condition) {
        return new Rule(condition.getScriptId(), condition.getScript(), condition.getName());
    }
}
