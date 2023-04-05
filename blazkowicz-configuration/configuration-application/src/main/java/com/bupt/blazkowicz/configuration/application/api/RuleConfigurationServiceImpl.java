package com.bupt.blazkowicz.configuration.application.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bupt.blazkowicz.client.share.dto.result.ResultDTO;
import com.bupt.blazkowicz.configuration.client.api.RuleConfigurationService;
import com.bupt.blazkowicz.configuration.client.api.req.CreateRuleReq;
import com.bupt.blazkowicz.configuration.client.api.req.UpdateRuleReq;
import com.bupt.blazkowicz.domain.share.entity.Condition;
import com.bupt.blazkowicz.domain.share.entity.Rule;
import com.bupt.blazkowicz.domain.share.factory.RuleFactory;
import com.bupt.blazkowicz.domain.share.repo.RuleRepo;

/**
 * @author lhf2018
 * @date 2022/12/10 18:03
 */
@Component
public class RuleConfigurationServiceImpl implements RuleConfigurationService {
    @Autowired
    private RuleRepo ruleRepo;

    @Override
    public ResultDTO<Void> create(CreateRuleReq createRuleReq) {
        ruleRepo.save(RuleFactory.create(createRuleReq.getRuleName(),
            JSON.parseObject(createRuleReq.getRuleConditions(), new TypeReference<List<Condition>>() {}),
            createRuleReq.getRuleLogic()));
        return ResultDTO.buildSuccessResult();
    }

    @Override
    public ResultDTO<Void> update(UpdateRuleReq updateRuleReq) {
        Rule rule = ruleRepo.get(updateRuleReq.getRuleId());
        rule.update(updateRuleReq.getRuleLogic());
        rule.update(JSON.parseObject(updateRuleReq.getRuleConditions(), new TypeReference<List<Condition>>() {}));
        ruleRepo.save(rule);
        return ResultDTO.buildSuccessResult();
    }
}
