package com.bupt.blazkowicz.configuration.application.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bupt.blazkowicz.client.share.api.RuleConfigurationService;
import com.bupt.blazkowicz.client.share.api.req.CreateRuleReq;
import com.bupt.blazkowicz.client.share.dto.result.ResultDTO;
import com.bupt.blazkowicz.domain.share.inf.RuleManagerInfService;

/**
 * @author lhf2018
 * @date 2022/12/10 18:03
 */
@Component
public class RuleConfigurationServiceImpl implements RuleConfigurationService {
    // todo只更新参数
    @Autowired
    private RuleManagerInfService ruleManagerInfService;

    @Override
    public ResultDTO<Void> createRule(CreateRuleReq createRuleReq) {
        return null;
    }
}
