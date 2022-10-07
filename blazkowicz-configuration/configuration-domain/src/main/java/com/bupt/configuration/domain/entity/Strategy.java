package com.bupt.configuration.domain.entity;

import lombok.Getter;

import java.util.Date;
import java.util.List;

/**
 * 风险识别
 *
 * @author lhf2018
 * @date 2022/10/5 1:12
 */
@Getter
public class Strategy {
    private final Long strategyId;
    private final Date gmtCreate;
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
    private List<Disposal> disposalList;

    public Strategy(Long strategyId, Date gmtCreate, Date gmtModified, StrategyInfo strategyInfo, Condition condition, List<Disposal> disposalList) {
        this.strategyId = strategyId;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
        this.strategyInfo = strategyInfo;
        this.condition = condition;
        this.disposalList = disposalList;
    }

    public Strategy(Long strategyId, Date gmtCreate, Date gmtModified, Integer version, StrategyInfo strategyInfo, Condition condition, List<Disposal> disposalList) {
        this.strategyId = strategyId;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
        this.version = version;
        this.strategyInfo = strategyInfo;
        this.condition = condition;
        this.disposalList = disposalList;
    }

    public void updateStrategyInfo(StrategyInfo newStrategyInfo) {
        if (newStrategyInfo == null) {
            throw new RuntimeException();
        }
        strategyInfo.update(newStrategyInfo);

        save();
    }

    public void addDisposal(Disposal newDisposal) {
        if (newDisposal == null) {
            throw new RuntimeException();
        }
        disposalList.add(newDisposal);

        save();
    }

    public void save() {
        //todo
    }
}
