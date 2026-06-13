package com.bupt.blazkowicz.configuration.domain.entity;

import com.bupt.blazkowicz.common.exception.BlazkowiczException;
import com.bupt.blazkowicz.common.exception.Code;
import com.bupt.blazkowicz.configuration.domain.bridge.ConfigurationDomainBridge;
import com.bupt.blazkowicz.configuration.domain.repo.EventRepo;
import com.bupt.blazkowicz.domain.share.anno.AggRoot;
import com.google.common.collect.Lists;
import lombok.Getter;

import java.util.Date;
import java.util.List;

/**
 * 事件
 *
 * @author lhf2018
 */
@Getter
@AggRoot
public class Event {
    private final Long eventId;
    private final Date gmtCreate;
    private Date gmtModified;
    private Integer version;
    private EventInfo eventInfo;
    private List<Feature> featureList;
    private StrategyPack strategyPack;
    private List<String> strategyNameRefs;

    public Event(Long eventId, Date gmtCreate, Date gmtModified, EventInfo eventInfo) {
        this.eventId = eventId;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
        this.eventInfo = eventInfo;
        this.featureList = Lists.newArrayList();
        this.strategyNameRefs = Lists.newArrayList();
    }

    public Event(Long eventId, Date gmtCreate, Date gmtModified, Integer version, EventInfo eventInfo,
        List<Feature> featureList, StrategyPack strategyPack) {
        this(eventId, gmtCreate, gmtModified, version, eventInfo, featureList, strategyPack, Lists.newArrayList());
    }

    public Event(Long eventId, Date gmtCreate, Date gmtModified, Integer version, EventInfo eventInfo,
        List<Feature> featureList, StrategyPack strategyPack, List<String> strategyNameRefs) {
        this.eventId = eventId;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
        this.version = version;
        this.eventInfo = eventInfo;
        this.featureList = featureList != null ? featureList : Lists.newArrayList();
        this.strategyPack = strategyPack;
        this.strategyNameRefs = strategyNameRefs != null ? strategyNameRefs : Lists.newArrayList();
    }

    public void addFeature(Feature newFeature) {
        if (newFeature == null) {
            throw new BlazkowiczException(Code.PARAMETER_INVALID, "特征不能为空");
        }
        featureList.add(newFeature);
        save();
    }

    public void updateEventInfo(EventInfo newEventInfo) {
        if (newEventInfo == null) {
            throw new BlazkowiczException(Code.PARAMETER_INVALID, "事件信息不能为空");
        }
        eventInfo.update(newEventInfo);
        save();
    }

    public void updateStrategyPack(StrategyPack newStrategyPack, List<String> strategyNames) {
        if (newStrategyPack == null) {
            throw new BlazkowiczException(Code.PARAMETER_INVALID, "策略包不能为空");
        }
        this.strategyPack = newStrategyPack;
        this.strategyNameRefs = strategyNames != null ? strategyNames : Lists.newArrayList();
        save();
    }

    public void save() {
        this.gmtModified = new Date();
        ConfigurationDomainBridge.getAdapter(EventRepo.class).save(this);
    }
}
