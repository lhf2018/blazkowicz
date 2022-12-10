package com.bupt.domain.share.entity;

import java.util.List;

import com.bupt.domain.share.anno.Entity;

import lombok.Getter;

/**
 * @author lhf2018
 * @date 2022/11/19 20:20
 */
@Getter
@Entity
public class Rule {
    /** 规则id */
    private String ruleId;
    /** 名称 */
    private String ruleName;
    /** 脚本 */
    private RuleScript ruleScript;
    /** 判断条件 */
    private List<Condition> conditions;
    /** 左参数类型（外部传入） */
    private LeftParamType leftParamType;

    public Rule(String ruleId, String ruleName, RuleScript ruleScript, List<Condition> conditions,
        LeftParamType leftParamType) {
        this.ruleId = ruleId;
        this.ruleName = ruleName;
        this.ruleScript = ruleScript;
        this.conditions = conditions;
        this.leftParamType = leftParamType;
    }
}
