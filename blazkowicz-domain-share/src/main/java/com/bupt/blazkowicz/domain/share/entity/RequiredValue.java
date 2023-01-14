package com.bupt.blazkowicz.domain.share.entity;

import lombok.Getter;

/**
 * 一个RequiredValue，对应一个入参
 * 
 * @author lhf2018
 * @date 2022/11/19 20:47
 */
@Getter
public class RequiredValue {
    /** 条件类型 */
    private RequiredValueType type;
    /** 条件名称 */
    private String name;
    /** 条件值 */
    private Object value;

    public RequiredValue(RequiredValueType type, String name, Object value) {
        this.type = type;
        this.name = name;
        this.value = value;
    }
}
