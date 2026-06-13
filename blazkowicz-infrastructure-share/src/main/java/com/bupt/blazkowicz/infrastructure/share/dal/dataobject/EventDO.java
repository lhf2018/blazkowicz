package com.bupt.blazkowicz.infrastructure.share.dal.dataobject;

import java.util.Date;

import lombok.Data;

/**
 * @author lhf2018
 */
@Data
public class EventDO {
    private Integer id;
    private Long eventId;
    private Integer version;
    private String name;
    private String code;
    private String lastOperator;
    private String description;
    private String accessMethod;
    private String features;
    private String strategyPack;
    private Date gmtCreate;
    private Date gmtModified;
}
