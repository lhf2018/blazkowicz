package com.bupt.blazkowicz.configuration.client.api.resp;

import java.util.List;

import lombok.Data;

/**
 * @author lhf2018
 */
@Data
public class EventDetailResp {
    private Long eventId;
    private String name;
    private String code;
    private String lastOperator;
    private String description;
    private String accessMethod;
    private Integer version;
    private List<FeatureResp> features;
    private StrategyPackResp strategyPack;
}
