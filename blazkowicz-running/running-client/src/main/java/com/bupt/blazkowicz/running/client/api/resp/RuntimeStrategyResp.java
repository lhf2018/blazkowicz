package com.bupt.blazkowicz.running.client.api.resp;

import lombok.Data;

/**
 * @author lhf2018
 */
@Data
public class RuntimeStrategyResp {
    private Long ruleId;
    private String ruleName;
    private String ruleLogic;
    private String disposalType;
    private String disposalAction;
    private String disposalMessage;
}
