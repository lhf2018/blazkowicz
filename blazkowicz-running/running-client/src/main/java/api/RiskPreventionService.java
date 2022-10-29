package api;

import api.req.RiskPreventionReq;
import api.resp.RiskPreventionResultResp;

/**
 * @author lhf2018
 * @date 2022/10/5 1:18
 */
public interface RiskPreventionService {
    /**
     * 风控服务入口
     * 
     * @param riskPreventionReq
     * @return
     */
    RiskPreventionResultResp prevention(RiskPreventionReq riskPreventionReq);
}
