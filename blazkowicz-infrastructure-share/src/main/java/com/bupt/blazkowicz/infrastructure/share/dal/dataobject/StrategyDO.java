package com.bupt.blazkowicz.infrastructure.share.dal.dataobject;

import lombok.Data;

/**
 * @author lhf2018
 */
@Data
public class StrategyDO {
    private Integer id;
    private Long strategyId;
    private Integer version;
    private String businessIdentity;
    private String preventionType;
    private String name;
    private String description;
    private Long introducedRuleId;
    private String disposalConfig;
}
