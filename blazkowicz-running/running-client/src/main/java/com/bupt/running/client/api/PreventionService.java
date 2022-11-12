package com.bupt.running.client.api;

import com.bupt.running.client.api.req.PreventionReq;
import com.bupt.running.client.api.resp.PreventionResultResp;
import com.bupt.running.client.api.result.ResultDTO;

/**
 * @author lhf2018
 * @date 2022/10/5 1:18
 */
public interface PreventionService {
    /**
     * 风控服务入口
     * 
     * @param preventionReq
     * @return
     */
    ResultDTO<PreventionResultResp> request(PreventionReq preventionReq);
}
