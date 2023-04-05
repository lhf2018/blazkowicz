package com.bupt.blazkowicz.configuration.client.api;

import com.bupt.blazkowicz.client.share.dto.result.ResultDTO;
import com.bupt.blazkowicz.configuration.client.api.req.CreateRuleReq;
import com.bupt.blazkowicz.configuration.client.api.req.UpdateRuleReq;

/**
 * 识别规则服务
 * 
 * @author lhf2018
 * @date 2022/12/10 18:02
 */
public interface RuleConfigurationService {
    /**
     * 创建识别规则
     * 
     * @param createRuleReq
     * @return
     */
    ResultDTO<Void> create(CreateRuleReq createRuleReq);

    /**
     * 更新規則
     * 
     * @param updateRuleReq
     * @return
     */
    ResultDTO<Void> update(UpdateRuleReq updateRuleReq);
}
