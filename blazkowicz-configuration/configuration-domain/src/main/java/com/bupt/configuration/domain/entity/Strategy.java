package com.bupt.configuration.domain.entity;

import lombok.Getter;

import java.util.Date;

/**
 * 风险识别
 *
 * @author lhf2018
 * @date 2022/10/5 1:12
 */
@Getter
public class Strategy {
    private Long strategyId;
    private Date gmtCreate;
    private Date gmtModified;
    private Integer version;
    /**
     * 事件基础信息
     */
    private StrategyInfo strategyInfo;
    /**
     * 条件表达式
     */
    private Condition condition;
    /**
     * 处置
     */
    private Disposal disposal;
}
