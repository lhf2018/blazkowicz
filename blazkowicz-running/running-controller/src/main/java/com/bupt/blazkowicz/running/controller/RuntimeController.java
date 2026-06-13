package com.bupt.blazkowicz.running.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bupt.blazkowicz.client.share.dto.result.ResultDTO;
import com.bupt.blazkowicz.domain.share.entity.BusinessIdentity;
import com.bupt.blazkowicz.domain.share.entity.PreventionType;
import com.bupt.blazkowicz.domain.share.entity.Rule;
import com.bupt.blazkowicz.domain.share.resp.DisposalResp;
import com.bupt.blazkowicz.infrastructure.share.query.PreventionConfigInfService;
import com.bupt.blazkowicz.infrastructure.share.query.RuleQueryService;
import com.bupt.blazkowicz.running.client.api.resp.RuntimeStrategyResp;

/**
 * @author lhf2018
 */
@RestController
@RequestMapping("/runtime")
public class RuntimeController {
    @Autowired
    private RuleQueryService ruleQueryService;
    @Autowired
    private PreventionConfigInfService preventionConfigInfService;

    @GetMapping("/strategies")
    public ResultDTO<List<RuntimeStrategyResp>> strategies(
        @RequestParam("business_identity") String businessIdentity,
        @RequestParam("prevention_type") String preventionType) {
        List<Rule> ruleList = ruleQueryService.getRuleRespList(BusinessIdentity.valueOf(businessIdentity),
            PreventionType.valueOf(preventionType));
        List<RuntimeStrategyResp> respList = ruleList.stream().map(rule -> {
            RuntimeStrategyResp resp = new RuntimeStrategyResp();
            resp.setRuleId(rule.getRuleId());
            resp.setRuleName(rule.getRuleName());
            resp.setRuleLogic(rule.getLogic());
            DisposalResp disposalResp = preventionConfigInfService.getDisposalResp(businessIdentity, preventionType,
                rule.getRuleId());
            if (disposalResp != null) {
                resp.setDisposalType(disposalResp.getDisposalType());
                resp.setDisposalAction(disposalResp.getAction());
                resp.setDisposalMessage(disposalResp.getMessage());
            }
            return resp;
        }).collect(Collectors.toList());
        return ResultDTO.buildSuccessResult(respList);
    }
}
