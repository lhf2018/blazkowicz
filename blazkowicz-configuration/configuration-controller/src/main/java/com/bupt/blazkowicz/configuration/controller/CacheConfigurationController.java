package com.bupt.blazkowicz.configuration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bupt.blazkowicz.client.share.dto.result.ResultDTO;
import com.bupt.blazkowicz.infrastructure.share.query.RuleQueryService;

/**
 * @author lhf2018
 */
@RestController
@RequestMapping("/config/cache")
public class CacheConfigurationController {
    @Autowired
    private RuleQueryService ruleQueryService;

    @PostMapping("/invalidate")
    public ResultDTO<Void> invalidate(@RequestParam("business_identity") String businessIdentity,
        @RequestParam("prevention_type") String preventionType) {
        ruleQueryService.invalidateRulesCache(businessIdentity, preventionType);
        return ResultDTO.buildSuccessResult();
    }
}
