package com.bupt.configuration.domain.entity;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

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
    private final String owningEvent;
    /**
     * 操作人
     */
    private String lastOperator;

    public StrategyInfo(String owningEvent, String lastOperator) {
        this.owningEvent = owningEvent;
        this.lastOperator = lastOperator;
    }

    public void update(StrategyInfo strategyInfo) {
        if (StringUtils.isNotEmpty(strategyInfo.getLastOperator())) {
            lastOperator = strategyInfo.getLastOperator();
        }
    }
}
