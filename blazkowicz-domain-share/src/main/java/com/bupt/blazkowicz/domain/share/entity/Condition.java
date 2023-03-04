package com.bupt.blazkowicz.domain.share.entity;

import java.util.List;

import com.bupt.blazkowicz.domain.share.anno.Specification;

import lombok.Getter;

/**
 * 条件，一个condition等于一条识别逻辑
 * 
 * @author lhf2018
 * @date 2023/1/14 23:17
 */
@Getter
@Specification
public class Condition {
    /** 条件id，用于处理复杂逻辑 */
    private Integer conditionId;
    /** 脚本 */
    private ConditionScript conditionScript;
    /** 判断条件 */
    private List<RequiredValue> requiredValues;
    /** 左参数类型（外部传入） */
    private LeftParamType leftParamType;

    public Condition(Integer conditionId, ConditionScript conditionScript, List<RequiredValue> requiredValues,
        LeftParamType leftParamType) {
        this.conditionId = conditionId;
        this.conditionScript = conditionScript;
        this.requiredValues = requiredValues;
        this.leftParamType = leftParamType;
    }
}
