package com.bupt.blazkowicz.infrastructure.share.dal.dataobject;

import lombok.Data;

/**
 * @author lhf2018
 * @date 2023/1/8 0:35
 */
@Data
public class RuleDO {
    private Integer id;
    /** 规则id */
    private String ruleId;
    private Integer version;
    /** 名称 */
    private String ruleName;
    /** 脚本 */
    private String ruleScript;
    /** 判断条件 */
    private String conditions;
    /** 左参数类型（外部传入） */
    private String leftParamType;
}
