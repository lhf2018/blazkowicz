package com.bupt.blazkowicz.domain.share.resp;

import lombok.Getter;
import lombok.Setter;

/**
 * @author lhf2018
 */
@Getter
@Setter
public class DisposalResp {
    /** AUDIT / PUNISH */
    private String disposalType;
    private String action;
    private String message;
}
