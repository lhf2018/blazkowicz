package com.bupt.running.application.translator;

import java.util.List;
import java.util.stream.Collectors;

import com.bupt.running.client.api.resp.IdentityResultResp;

/**
 * @author lhf2018
 * @date 2022/10/30 0:14
 */
public class ToIdentityResultRespList {

    public static List<IdentityResultResp>
        toIdentityResultRespList(List<com.bupt.running.domain.support.rule.IdentityResultResp> identityResultRespList) {
        return identityResultRespList.stream().map(ToIdentityResultRespList::toIdentityResultResp)
            .collect(Collectors.toList());
    }

    public static IdentityResultResp
        toIdentityResultResp(com.bupt.running.domain.support.rule.IdentityResultResp identityResultResp) {
        IdentityResultResp resp = new IdentityResultResp();
        resp.setName(identityResultResp.getRuleName());
        resp.setStatus(identityResultResp.getStatus().name());
        return resp;
    }
}
