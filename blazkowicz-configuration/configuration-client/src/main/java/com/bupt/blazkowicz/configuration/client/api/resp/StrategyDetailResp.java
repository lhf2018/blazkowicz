package com.bupt.blazkowicz.configuration.client.api.resp;

import lombok.Data;

/**
 * @author lhf2018
 */
@Data
public class StrategyDetailResp {
    private Long strategyId;
    private String businessIdentity;
    private String preventionType;
    private String name;
    private String description;
    private Long introducedRuleId;
    private String ruleName;
    private String disposalType;
    private String disposalAction;
    private String disposalMessage;
    private Integer version;
}
