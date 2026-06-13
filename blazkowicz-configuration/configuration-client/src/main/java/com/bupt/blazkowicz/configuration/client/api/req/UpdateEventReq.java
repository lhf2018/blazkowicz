package com.bupt.blazkowicz.configuration.client.api.req;

import lombok.Data;

/**
 * @author lhf2018
 */
@Data
public class UpdateEventReq {
    private String name;
    private String lastOperator;
    private String description;
    private String accessMethod;
}
