package com.bupt.blazkowicz.configuration.application.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bupt.blazkowicz.client.share.dto.result.ResultDTO;
import com.bupt.blazkowicz.configuration.client.api.RuleConfigurationService;
import com.bupt.blazkowicz.configuration.client.api.req.CreateRuleReq;
import com.bupt.blazkowicz.configuration.client.api.req.UpdateRuleReq;
import com.bupt.blazkowicz.domain.share.factory.RuleFactory;
import com.bupt.blazkowicz.domain.share.inf.RuleManagerInfService;

/**
 * @author lhf2018
 * @date 2022/12/10 18:03
 */
@Component
public class RuleConfigurationServiceImpl implements RuleConfigurationService {
    @Autowired
    private RuleManagerInfService ruleManagerInfService;

    @Override
    public ResultDTO<Void> createRule(CreateRuleReq createRuleReq) {
        // todo conditionList
        ruleManagerInfService.createRule(RuleFactory.create(createRuleReq.getRuleName(), null, null));
        return ResultDTO.buildSuccessResult();
    }

    @Override
    public ResultDTO<Void> updateRule(UpdateRuleReq updateRuleReq) {
        // todo 只更新参数
        return null;
    }
}
