package com.bupt.configuration.domain.entity;

import com.bupt.common.exception.BlazkowiczException;
import com.bupt.common.exception.Code;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author lhf2018
 * @date 2022/10/15 16:28
 */
@Getter
public class StrategyPack {
    /** 所属事件 */
    private final String owningEvent;

    /** 操作人 */
    private String lastOperator;

    /** 描述 */
    private String description;
    /** 策略列表 */
    private List<Strategy> strategyList;

    /** 业务身份 */
    private final String businessIdentity;

    /** 防控场景 */
    private final String preventionType;

    /** 策略包名称 */
    private final String name;

    public StrategyPack(String owningEvent, String lastOperator, String description, List<Strategy> strategyList,
        String businessIdentity, String preventionType, String name) {
        this.owningEvent = owningEvent;
        this.lastOperator = lastOperator;
        this.description = description;
        this.strategyList = strategyList;
        this.businessIdentity = businessIdentity;
        this.preventionType = preventionType;
        this.name = name;
    }

    public void addStrategy(Strategy newStrategy) {
        if (newStrategy == null) {
            throw new BlazkowiczException(Code.DOMAIN_STRATEGY_ERROR);
        }
        if (!StringUtils.equals(newStrategy.getBusinessIdentity(), businessIdentity)) {
            throw new BlazkowiczException(Code.DOMAIN_STRATEGY_ERROR);
        }
        if (!StringUtils.equals(newStrategy.getPreventionType(), preventionType)) {
            throw new BlazkowiczException(Code.DOMAIN_STRATEGY_ERROR);
        }
        strategyList.add(newStrategy);
    }

    public void updateLastOperator(String newLastOperator) {
        if (StringUtils.isEmpty(newLastOperator)) {
            throw new BlazkowiczException(Code.DOMAIN_STRATEGY_ERROR);
        }
        lastOperator = newLastOperator;
    }

    public void updateDescription(String newDescription) {
        if (StringUtils.isEmpty(newDescription)) {
            throw new BlazkowiczException(Code.DOMAIN_STRATEGY_ERROR);
        }
        description = newDescription;
    }
}
