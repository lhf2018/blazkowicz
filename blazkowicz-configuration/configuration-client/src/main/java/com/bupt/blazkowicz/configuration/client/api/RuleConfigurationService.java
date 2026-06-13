package com.bupt.blazkowicz.configuration.client.api;

import java.util.List;

import com.bupt.blazkowicz.client.share.dto.result.ResultDTO;
import com.bupt.blazkowicz.configuration.client.api.req.CreateRuleReq;
import com.bupt.blazkowicz.configuration.client.api.req.UpdateRuleReq;
import com.bupt.blazkowicz.configuration.client.api.resp.RuleDetailResp;

/**
 * @author lhf2018
 */
public interface RuleConfigurationService {

    ResultDTO<Void> create(CreateRuleReq createRuleReq);

    ResultDTO<Void> update(UpdateRuleReq updateRuleReq);

    ResultDTO<List<RuleDetailResp>> list();

    ResultDTO<RuleDetailResp> get(Long ruleId);
}
