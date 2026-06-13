package com.bupt.blazkowicz.domain.share.dto;

import lombok.Data;

/**
 * @author lhf2018
 */
@Data
public class DisposalCustomDTO {
    private String businessIdentity;
    private String preventionType;
    private Long ruleId;
    /** AUDIT / PUNISH */
    private String disposalType;
    private String action;
    private String message;
}
