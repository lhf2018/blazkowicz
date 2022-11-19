package com.bupt.configuration.domain.entity;

import java.util.Date;
import java.util.List;

import com.bupt.domain.share.entity.BusinessIdentity;
import com.bupt.domain.share.entity.PreventionType;
import com.bupt.domain.share.entity.Rule;

import lombok.Getter;

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
    private BusinessIdentity businessIdentity;

    /** 防控场景 */
    private PreventionType preventionType;

    /** 策略名称 */
    private String name;

    /** 描述 */
    private String description;

    /** 识别规则 */
    private Rule rule;

    /** 处置 */
    private List<Disposal> disposalList;

    public Strategy(Long strategyId, Date gmtCreate, Date gmtModified, BusinessIdentity businessIdentity,
        PreventionType preventionType, String name, String description, List<Disposal> disposalList) {
        this.strategyId = strategyId;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
        this.businessIdentity = businessIdentity;
        this.preventionType = preventionType;
        this.name = name;
        this.description = description;
        this.disposalList = disposalList;
    }

    public Strategy(Long strategyId, Date gmtCreate, Date gmtModified, Integer version,
        BusinessIdentity businessIdentity, PreventionType preventionType, String name, String description, Rule rule,
        List<Disposal> disposalList) {
        this.strategyId = strategyId;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
        this.version = version;
        this.businessIdentity = businessIdentity;
        this.preventionType = preventionType;
        this.name = name;
        this.description = description;
        this.rule = rule;
        this.disposalList = disposalList;
    }

    public void addDisposal(Disposal newDisposal) {
        if (newDisposal == null) {
            throw new RuntimeException();
        }
        disposalList.add(newDisposal);

        save();
    }

    public void update(Rule rule) {
        // todo
        this.rule = rule;
    }

    public void save() {
        // todo
    }
}
