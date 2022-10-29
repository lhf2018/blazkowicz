package com.bupt.running.application.api;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bupt.running.application.translator.ToIdentityResultRespList;
import com.bupt.running.client.api.PreventionService;
import com.bupt.running.client.api.req.PreventionReq;
import com.bupt.running.client.api.resp.PreventionResultResp;
import com.bupt.running.domain.entity.Prevention;

/**
 * @author lhf2018
 * @date 2022/10/29 15:45
 */
@Component
public class PreventionServiceImpl implements PreventionService {

    @Override
    public PreventionResultResp request(PreventionReq preventionReq) {
        List<com.bupt.running.domain.resp.PreventionResultResp> result =
            new Prevention().run(preventionReq.getBusinessIdentity(), preventionReq.getPreventionType());

        PreventionResultResp preventionResultResp = new PreventionResultResp();
        preventionResultResp.setIdentityResultRespList(ToIdentityResultRespList.toIdentityResultRespList(result));
        return preventionResultResp;
    }
}
