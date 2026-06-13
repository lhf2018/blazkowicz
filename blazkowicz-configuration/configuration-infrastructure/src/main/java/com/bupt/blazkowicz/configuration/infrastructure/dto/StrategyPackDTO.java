package com.bupt.blazkowicz.configuration.infrastructure.dto;

import java.util.List;

import lombok.Data;

/**
 * @author lhf2018
 */
@Data
public class StrategyPackDTO {
    private String owningEvent;
    private String lastOperator;
    private String description;
    private String businessIdentity;
    private String preventionType;
    private String name;
    private List<String> strategyNames;
}
