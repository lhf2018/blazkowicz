package com.bupt.configuration.domain.entity;

import lombok.Getter;

/**
 * 特征类型
 *
 * @author lhf2018
 * @date 2022/10/6 18:20
 */
@Getter
public enum FeatureType {
    /**
     * 属性
     */
    ATTRIBUTE,
    /**
     * 防控实体
     */
    PREVENTION_ENTITY,
    /**
     * 数据服务
     */
    DATA_SERVICE,
    /**
     * 策略服务
     */
    STRATEGY_SERVICE;
}
