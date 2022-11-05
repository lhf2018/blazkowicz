package com.bupt.running.application.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bupt.domain.share.entity.BusinessIdentity;
import com.bupt.domain.share.entity.PreventionType;
import com.bupt.running.application.translator.ToIdentityResultRespList;
import com.bupt.running.client.api.PreventionService;
import com.bupt.running.client.api.req.PreventionReq;
import com.bupt.running.client.api.resp.PreventionResultResp;
import com.bupt.running.domain.support.rule.IdentityResultResp;
import com.bupt.running.domain.support.rule.RulePort;

/**
 * @author lhf2018
 * @date 2022/10/29 15:45
 */
@Component
public class PreventionServiceImpl implements PreventionService {

    @Autowired
    private RulePort rulePort;

    @Override
    public PreventionResultResp request(PreventionReq preventionReq) {
        BusinessIdentity businessIdentity = BusinessIdentity.valueOf(preventionReq.getBusinessIdentity());
        PreventionType preventionType = PreventionType.valueOf(preventionReq.getPreventionType());
        List<IdentityResultResp> result = rulePort.run(businessIdentity.name(), preventionType.name());

        PreventionResultResp preventionResultResp = new PreventionResultResp();
        preventionResultResp.setIdentityResultRespList(ToIdentityResultRespList.toIdentityResultRespList(result));
        return preventionResultResp;
    }
}
