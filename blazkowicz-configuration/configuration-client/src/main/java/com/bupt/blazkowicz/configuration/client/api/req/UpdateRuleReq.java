package com.bupt.blazkowicz.configuration.client.api.req;

import lombok.Data;

/**
 * @author lhf2018
 * @date 2022/12/31 22:42
 */
@Data
public class UpdateRuleReq {
    private String ruleName;
    private String ruleScript;
}
