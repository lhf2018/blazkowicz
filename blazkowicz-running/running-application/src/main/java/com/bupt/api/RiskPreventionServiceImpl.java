package com.bupt.api;

import org.springframework.stereotype.Component;

import com.bupt.running.domain.entity.Prevention;
import com.bupt.running.domain.resp.PreventionResultResp;

import api.RiskPreventionService;
import api.req.RiskPreventionReq;
import api.resp.RiskPreventionResultResp;

/**
 * @author lhf2018
 * @date 2022/10/29 15:45
 */
@Component
public class RiskPreventionServiceImpl implements RiskPreventionService {

    @Override
    public RiskPreventionResultResp prevention(RiskPreventionReq riskPreventionReq) {
        PreventionResultResp preventionResultResp = new Prevention().run(riskPreventionReq.getBusinessIdentity(),
            riskPreventionReq.getPreventionPointType(), riskPreventionReq.getRuleName());

        RiskPreventionResultResp riskPreventionResultResp = new RiskPreventionResultResp();
        riskPreventionResultResp.setName(preventionResultResp.getName());
        riskPreventionResultResp.setStatus(preventionResultResp.getStatus());
        return riskPreventionResultResp;
    }
}
