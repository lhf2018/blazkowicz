package com.bupt.blazkowicz.client.share.api;

import com.bupt.blazkowicz.client.share.api.req.CreateRuleReq;
import com.bupt.blazkowicz.client.share.dto.result.ResultDTO;

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
    ResultDTO<Void> createRule(CreateRuleReq createRuleReq);
}
