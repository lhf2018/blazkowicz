package com.bupt.blazkowicz.configuration.client.api;

import java.util.List;

import com.bupt.blazkowicz.client.share.dto.result.ResultDTO;
import com.bupt.blazkowicz.configuration.client.api.req.CreateStrategyReq;
import com.bupt.blazkowicz.configuration.client.api.req.UpdateStrategyReq;
import com.bupt.blazkowicz.configuration.client.api.resp.StrategyDetailResp;

/**
 * @author lhf2018
 */
public interface StrategyConfigurationService {

    ResultDTO<Void> create(CreateStrategyReq createStrategyReq);

    ResultDTO<Void> update(UpdateStrategyReq updateStrategyReq);

    ResultDTO<List<StrategyDetailResp>> list(String businessIdentity, String preventionType);

    ResultDTO<StrategyDetailResp> get(String businessIdentity, String preventionType, String name);
}
