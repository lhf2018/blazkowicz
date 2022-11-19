package com.bupt.domain.share.entity;

import lombok.Getter;

/**
 * @author lhf2018
 * @date 2022/11/19 20:20
 */
@Getter
public class Rule {
    /** 规则id */
    private String ruleId;
    /** 名称 */
    private String ruleName;
    /** 业务身份 */
    private BusinessIdentity businessIdentity;
    /** 防控类型 */
    private PreventionType preventionType;
    /** 脚本 */
    private RuleScript ruleScript;
    /** 右参数（判断条件） */
    private RightParam rightParam;
    /** 左参数类型（外部传入） */
    private LeftParamType leftParamType;

    public Rule(String ruleId, String ruleName, BusinessIdentity businessIdentity, PreventionType preventionType,
        RuleScript ruleScript, RightParam rightParam, LeftParamType leftParamType) {
        this.ruleId = ruleId;
        this.ruleName = ruleName;
        this.businessIdentity = businessIdentity;
        this.preventionType = preventionType;
        this.ruleScript = ruleScript;
        this.rightParam = rightParam;
        this.leftParamType = leftParamType;
    }
}
