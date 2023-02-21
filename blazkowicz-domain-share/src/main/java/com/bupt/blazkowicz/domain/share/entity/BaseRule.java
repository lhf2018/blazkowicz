package com.bupt.blazkowicz.domain.share.entity;

import lombok.Getter;

/**
 * @author lhf2018
 * @date 2022/11/19 20:20
 */
@Getter
public class BaseRule {
    private final RuleType ruleType;

    public BaseRule(RuleType ruleType) {
        this.ruleType = ruleType;
    }
}
