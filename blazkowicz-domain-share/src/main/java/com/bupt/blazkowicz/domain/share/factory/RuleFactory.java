package com.bupt.blazkowicz.domain.share.factory;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bupt.blazkowicz.domain.share.entity.Condition;
import com.bupt.blazkowicz.domain.share.entity.Rule;

/**
 * @author lhf2018
 * @date 2023/3/4 18:33
 */
@Component
public class RuleFactory {

    public static Rule create(String ruleName, List<Condition> conditionList, String logic) {
        return new Rule(ruleName, conditionList, logic);
    }
}
