package com.bupt.blazkowicz.domain.share.entity;

import lombok.Getter;

/**
 * 一个condition，对应一个入参
 * 
 * @author lhf2018
 * @date 2022/11/19 20:47
 */
@Getter
public class Condition {
    /** 条件类型 */
    private ConditionType type;
    /** 条件名称 */
    private String name;
    /** 条件值 */
    private Object value;

    public Condition(ConditionType type, String name, Object value) {
        this.type = type;
        this.name = name;
        this.value = value;
    }
}
