package com.bupt.blazkowicz.domain.share.factory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bupt.blazkowicz.domain.share.bridge.SequenceInfService;
import com.bupt.blazkowicz.domain.share.bridge.enums.SequenceType;
import com.bupt.blazkowicz.domain.share.entity.Condition;
import com.bupt.blazkowicz.domain.share.entity.Rule;

/**
 * @author lhf2018
 * @date 2023/3/4 18:33
 */
@Component
public class RuleFactory {
    private static SequenceInfService sequenceInfService;

    @Autowired
    public RuleFactory(SequenceInfService sequenceInfService) {
        RuleFactory.sequenceInfService = sequenceInfService;
    }

    public static Rule create(String ruleName, List<Condition> conditionList, String logic) {
        return new Rule(sequenceInfService.nextSequenceId(SequenceType.RULE_ID), ruleName, conditionList, logic);
    }
}
