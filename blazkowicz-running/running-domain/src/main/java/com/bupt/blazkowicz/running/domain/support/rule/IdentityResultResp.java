package com.bupt.blazkowicz.running.domain.support.rule;

import com.bupt.blazkowicz.domain.share.entity.Status;

import lombok.Data;

/**
 * @author lhf2018
 */
@Data
public class IdentityResultResp {
    private Status status;
    private String ruleName;
    private String disposalType;
    private String disposalAction;
    private String disposalMessage;
}
