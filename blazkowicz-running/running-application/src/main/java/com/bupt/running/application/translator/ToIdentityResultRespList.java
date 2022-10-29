package com.bupt.running.application.translator;

import java.util.List;
import java.util.stream.Collectors;

import com.bupt.running.client.api.resp.IdentityResultResp;
import com.bupt.running.domain.resp.PreventionResultResp;

/**
 * @author lhf2018
 * @date 2022/10/30 0:14
 */
public class ToIdentityResultRespList {
    public static List<IdentityResultResp>
        toIdentityResultRespList(List<PreventionResultResp> preventionResultRespList) {
        return preventionResultRespList.stream().map(ToIdentityResultRespList::toIdentityResultResp)
            .collect(Collectors.toList());
    }

    public static IdentityResultResp toIdentityResultResp(PreventionResultResp preventionResultResp) {
        IdentityResultResp identityResultResp = new IdentityResultResp();
        identityResultResp.setName(preventionResultResp.getName());
        identityResultResp.setStatus(preventionResultResp.getStatus());
        return identityResultResp;
    }
}
