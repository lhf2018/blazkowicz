package com.bupt.blazkowicz.configuration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bupt.blazkowicz.client.share.dto.result.ResultDTO;
import com.bupt.blazkowicz.configuration.client.api.RuleConfigurationService;
import com.bupt.blazkowicz.configuration.client.api.req.CreateRuleReq;
import com.bupt.blazkowicz.configuration.client.api.req.UpdateRuleReq;
import com.bupt.blazkowicz.configuration.client.api.resp.RuleDetailResp;

/**
 * @author lhf2018
 */
@RestController
@RequestMapping("/config/rules")
public class RuleConfigurationController {
    @Autowired
    private RuleConfigurationService ruleConfigurationService;

    @GetMapping
    public ResultDTO<?> list() {
        return ruleConfigurationService.list();
    }

    @GetMapping("/{ruleId}")
    public ResultDTO<RuleDetailResp> get(@PathVariable Long ruleId) {
        return ruleConfigurationService.get(ruleId);
    }

    @PostMapping
    public ResultDTO<Void> create(@RequestBody CreateRuleReq createRuleReq) {
        return ruleConfigurationService.create(createRuleReq);
    }

    @PutMapping("/{ruleId}")
    public ResultDTO<Void> update(@PathVariable Long ruleId, @RequestBody UpdateRuleReq updateRuleReq) {
        updateRuleReq.setRuleId(ruleId);
        return ruleConfigurationService.update(updateRuleReq);
    }
}
