package com.bupt.blazkowicz.running.client.api.req;

import lombok.Data;

/**
 * @author lhf2018
 * @date 2022/10/29 15:43
 */
@Data
public class PreventionReq {
    /** 业务身份 */
    private String businessIdentity;
    /** 防控类型 */
    private String preventionType;
    /** 用户id */
    private String userId;
}
