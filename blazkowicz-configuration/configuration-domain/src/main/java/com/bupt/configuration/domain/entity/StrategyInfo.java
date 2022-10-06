package com.bupt.configuration.domain.entity;

import lombok.Getter;

/**
 * 策略基本信息
 *
 * @author lhf2018
 * @date 2022/10/6 17:15
 */
@Getter
public class StrategyInfo {
    /**
     * 所属事件
     */
    private String owningEvent;
    /**
     * 操作人
     */
    private String operator;
}
