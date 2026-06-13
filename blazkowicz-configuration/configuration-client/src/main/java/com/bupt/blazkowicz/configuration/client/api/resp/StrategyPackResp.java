package com.bupt.blazkowicz.configuration.client.api.resp;

import java.util.List;

import lombok.Data;

/**
 * @author lhf2018
 */
@Data
public class StrategyPackResp {
    private String owningEvent;
    private String lastOperator;
    private String description;
    private String businessIdentity;
    private String preventionType;
    private String name;
    private List<String> strategyNames;
}
