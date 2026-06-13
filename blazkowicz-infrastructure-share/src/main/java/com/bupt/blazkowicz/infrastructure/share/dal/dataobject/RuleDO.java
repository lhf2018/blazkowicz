package com.bupt.blazkowicz.infrastructure.share.dal.dataobject;

import lombok.Data;

/**
 * @author lhf2018
 */
@Data
public class RuleDO {
    private Integer id;
    /** 规则id */
    private String ruleId;
    private Integer version;
    /** 名称 */
    private String ruleName;
    /** 判断条件 JSON */
    private String conditions;
    /** 条件逻辑 */
    private String logic;
    /** 左参数类型（外部传入） */
    private String leftParamType;
}
