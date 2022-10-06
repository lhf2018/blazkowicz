package com.bupt.entity;

import lombok.Getter;

import java.util.Date;

/**
 * @author lhf2018
 * @date 2022/10/5 1:12
 */
@Getter
public class Event {
    private Long eventId;
    private Date gmtCreate;
    private Date gmtModified;
    private Integer version;
    /**
     * 基本信息
     */
    private EventInfo eventInfo;
    /**
     * 特征
     */
    private Feature feature;
}
