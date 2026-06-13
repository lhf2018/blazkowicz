package com.bupt.blazkowicz.running.client.api.resp;

import lombok.Data;

/**
 * @author lhf2018
 */
@Data
public class IdentityResultResp {
    private String status;
    private String name;
    private String disposalType;
    private String disposalAction;
    private String disposalMessage;
}
