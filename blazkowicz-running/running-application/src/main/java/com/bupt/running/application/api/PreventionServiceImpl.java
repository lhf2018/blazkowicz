package com.bupt.running.application.api;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bupt.client.share.dto.result.ResultDTO;
import com.bupt.domain.share.entity.BusinessIdentity;
import com.bupt.domain.share.entity.PreventionType;
import com.bupt.running.application.translator.ToIdentityResultRespList;
import com.bupt.running.client.api.PreventionService;
import com.bupt.running.client.api.req.PreventionReq;
import com.bupt.running.client.api.resp.PreventionResultResp;
import com.bupt.running.domain.entity.Prevention;
import com.bupt.running.domain.support.rule.IdentityResultResp;

/**
 * @author lhf2018
 * @date 2022/10/29 15:45
 */
@Service
public class PreventionServiceImpl implements PreventionService {

    @Override
    public ResultDTO<PreventionResultResp> request(PreventionReq preventionReq) {
        BusinessIdentity businessIdentity = BusinessIdentity.valueOf(preventionReq.getBusinessIdentity());
        PreventionType preventionType = PreventionType.valueOf(preventionReq.getPreventionType());
        // todo 创建逻辑
        Prevention prevention = new Prevention(businessIdentity, preventionType);
        prevention.run(preventionReq.getUserId());
        List<IdentityResultResp> result = prevention.getLatestResult();

        PreventionResultResp preventionResultResp = new PreventionResultResp();
        preventionResultResp.setIdentityResultRespList(ToIdentityResultRespList.toIdentityResultRespList(result));
        return ResultDTO.buildSuccessResult(preventionResultResp);
    }
}
