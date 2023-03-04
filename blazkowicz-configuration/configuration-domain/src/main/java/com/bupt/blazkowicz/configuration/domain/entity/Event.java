package com.bupt.blazkowicz.configuration.domain.entity;

import com.bupt.blazkowicz.domain.share.anno.AggRoot;
import com.google.common.collect.Lists;
import lombok.Getter;

import java.util.Date;
import java.util.List;

/**
 * 事件
 *
 * @author lhf2018
 * @date 2022/10/5 1:12
 */
@Getter
@AggRoot
public class Event {
    private final Long eventId;
    private final Date gmtCreate;
    private Date gmtModified;
    private Integer version;
    /**
     * 基本信息
     */
    private EventInfo eventInfo;
    /**
     * 特征
     */
    private List<Feature> featureList;

    /**
     * 策略包
     */
    private StrategyPack strategyPack;

    public Event(Long eventId, Date gmtCreate, Date gmtModified, EventInfo eventInfo) {
        this.eventId = eventId;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
        this.eventInfo = eventInfo;
        this.featureList = Lists.newArrayList();
    }

    public Event(Long eventId, Date gmtCreate, Date gmtModified, Integer version, EventInfo eventInfo,
                 List<Feature> featureList, StrategyPack strategyPack) {
        this.eventId = eventId;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
        this.version = version;
        this.eventInfo = eventInfo;
        this.featureList = featureList;
        this.strategyPack = strategyPack;
    }

    public void addFeature(Feature newFeature) {
        if (newFeature == null) {
            throw new RuntimeException();
        }
        featureList.add(newFeature);

        save();
    }

    public void updateEventInfo(EventInfo newEventInfo) {
        if (newEventInfo == null) {
            throw new RuntimeException();
        }
        eventInfo.update(newEventInfo);

        save();
    }

    public void save() {
        // todo
    }

}
