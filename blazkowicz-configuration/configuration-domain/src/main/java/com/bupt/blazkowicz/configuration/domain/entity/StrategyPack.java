package com.bupt.blazkowicz.configuration.domain.entity;

import com.bupt.blazkowicz.common.exception.BlazkowiczException;
import com.bupt.blazkowicz.common.exception.Code;
import com.bupt.blazkowicz.domain.share.anno.Entity;
import com.bupt.blazkowicz.domain.share.entity.BusinessIdentity;
import com.bupt.blazkowicz.domain.share.entity.PreventionType;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author lhf2018
 * @date 2022/10/15 16:28
 */
@Getter
@Entity
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
    private final BusinessIdentity businessIdentity;

    /** 防控场景 */
    private final PreventionType preventionType;

    /** 策略包名称 */
    private final String name;

    public StrategyPack(String owningEvent, String lastOperator, String description,
        List<Strategy> strategyList, BusinessIdentity businessIdentity, PreventionType preventionType, String name) {
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
        if (newStrategy.getBusinessIdentity() != businessIdentity) {
            throw new BlazkowiczException(Code.DOMAIN_STRATEGY_ERROR);

        }
        if (newStrategy.getPreventionType() != preventionType) {
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
