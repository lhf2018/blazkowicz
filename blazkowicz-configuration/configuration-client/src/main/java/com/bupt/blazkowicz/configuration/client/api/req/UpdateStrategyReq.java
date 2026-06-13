package com.bupt.blazkowicz.configuration.client.api.req;

import lombok.Data;

/**
 * @author lhf2018
 */
@Data
public class UpdateStrategyReq {
    private String businessIdentity;
    private String preventionType;
    private String name;
    private String description;
    private Long introducedRuleId;
    private String disposalType;
    private String disposalAction;
    private String disposalMessage;
}
