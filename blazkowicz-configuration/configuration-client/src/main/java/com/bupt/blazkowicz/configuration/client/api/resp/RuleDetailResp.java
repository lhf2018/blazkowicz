package com.bupt.blazkowicz.configuration.client.api.resp;

import lombok.Data;

/**
 * @author lhf2018
 */
@Data
public class RuleDetailResp {
    private Long ruleId;
    private String ruleName;
    private String ruleConditions;
    private String ruleLogic;
    private String leftParamType;
    private Integer version;
}
