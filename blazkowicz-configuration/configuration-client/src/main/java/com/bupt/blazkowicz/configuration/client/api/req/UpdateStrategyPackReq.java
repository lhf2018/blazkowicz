package com.bupt.blazkowicz.configuration.client.api.req;

import java.util.List;

import lombok.Data;

/**
 * @author lhf2018
 */
@Data
public class UpdateStrategyPackReq {
    private String lastOperator;
    private String description;
    private String businessIdentity;
    private String preventionType;
    private String name;
    private List<String> strategyNames;
}
