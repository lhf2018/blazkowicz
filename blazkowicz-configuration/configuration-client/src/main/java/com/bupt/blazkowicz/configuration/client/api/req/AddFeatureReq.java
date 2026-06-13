package com.bupt.blazkowicz.configuration.client.api.req;

import lombok.Data;

/**
 * @author lhf2018
 */
@Data
public class AddFeatureReq {
    private String type;
    private String name;
    private String description;
}
