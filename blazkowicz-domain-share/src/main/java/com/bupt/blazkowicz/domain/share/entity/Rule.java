package com.bupt.blazkowicz.domain.share.entity;

import java.util.List;

import com.bupt.blazkowicz.domain.share.anno.Entity;

import lombok.Getter;

/**
 * @author lhf2018
 * @date 2022/11/19 20:20
 */
@Getter
@Entity
public class Rule {
    /** 规则id */
    private Integer ruleId;
    /** 名称 */
    private String ruleName;
    /** 条件 */
    private List<Condition> conditionList;
    /** 条件逻辑 */
    private String logic;

    public Rule(Integer ruleId, String ruleName, List<Condition> conditionList, String logic) {
        this.ruleId = ruleId;
        this.ruleName = ruleName;
        this.conditionList = conditionList;
        this.logic = logic;
    }
}
