package com.bupt.blazkowicz.domain.share.entity;

import java.util.List;

import com.bupt.blazkowicz.domain.share.anno.Entity;

import lombok.Getter;

/**
 * 脚本类规则
 *
 * @author lhf2018
 * @date 2022/11/19 20:20
 */
@Getter
@Entity
public class Rule extends BaseRule {
    private Long ruleId;
    /** 名称 */
    private String ruleName;
    /** 条件 */
    private List<Condition> conditionList;
    /** 条件逻辑 */
    private String logic;

    public Rule(Long ruleId, String ruleName, List<Condition> conditionList, String logic) {
        super(RuleType.SCRIPT);
        this.ruleId = ruleId;
        this.ruleName = ruleName;
        this.conditionList = conditionList;
        this.logic = logic;
    }

    public void update(List<Condition> conditionList) {
        this.conditionList = conditionList;
    }

    public void update(String logic) {
        this.logic = logic;
    }
}
