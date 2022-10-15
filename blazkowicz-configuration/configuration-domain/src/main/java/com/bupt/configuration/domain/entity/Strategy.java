package com.bupt.configuration.domain.entity;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

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

    /** 业务身份 */
    private String businessIdentity;

    /** 防控场景 */
    private String preventionType;

    /** 策略名称 */
    private String name;

    /** 描述 */
    private String description;

    /** 条件表达式 */
    private Condition condition;

    /** 处置 */
    private List<Disposal> disposalList;

    public Strategy(Long strategyId, Date gmtCreate, Date gmtModified, String businessIdentity, String preventionType,
        String name, String description, Condition condition, List<Disposal> disposalList) {
        this.strategyId = strategyId;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
        this.businessIdentity = businessIdentity;
        this.preventionType = preventionType;
        this.name = name;
        this.description = description;
        this.condition = condition;
        this.disposalList = disposalList;
    }

    public Strategy(Long strategyId, Date gmtCreate, Date gmtModified, Integer version, String businessIdentity,
        String preventionType, String name, String description, Condition condition, List<Disposal> disposalList) {
        this.strategyId = strategyId;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
        this.version = version;
        this.businessIdentity = businessIdentity;
        this.preventionType = preventionType;
        this.name = name;
        this.description = description;
        this.condition = condition;
        this.disposalList = disposalList;
    }

    public void addDisposal(Disposal newDisposal) {
        if (newDisposal == null) {
            throw new RuntimeException();
        }
        disposalList.add(newDisposal);

        save();
    }

    public void updateCondition(Condition newCondition) {
        if (this.condition == null) {
            this.condition = newCondition;
            return;
        }
        if (StringUtils.isNotEmpty(newCondition.getName())) {
            this.condition.updateName(newCondition.getName());
        }
        if (StringUtils.isNotEmpty(newCondition.getScript())) {
            this.condition.updateName(newCondition.getScript());
        }
    }

    public void save() {
        // todo
    }
}
