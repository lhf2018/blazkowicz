package com.bupt.blazkowicz.running.client.api.resp;

import java.util.List;

import lombok.Data;

/**
 * @author lhf2018
 * @date 2022/10/29 15:36
 */
@Data
public class PreventionResultResp {
    private List<IdentityResultResp> identityResultRespList;
}
