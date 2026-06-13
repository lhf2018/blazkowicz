package com.bupt.blazkowicz.configuration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bupt.blazkowicz.client.share.dto.result.ResultDTO;
import com.bupt.blazkowicz.configuration.client.api.StrategyConfigurationService;
import com.bupt.blazkowicz.configuration.client.api.req.CreateStrategyReq;
import com.bupt.blazkowicz.configuration.client.api.req.UpdateStrategyReq;
import com.bupt.blazkowicz.configuration.client.api.resp.StrategyDetailResp;

/**
 * @author lhf2018
 */
@RestController
@RequestMapping("/config/strategies")
public class StrategyConfigurationController {
    @Autowired
    private StrategyConfigurationService strategyConfigurationService;

    @GetMapping
    public ResultDTO<?> list(@RequestParam("business_identity") String businessIdentity,
        @RequestParam("prevention_type") String preventionType) {
        return strategyConfigurationService.list(businessIdentity, preventionType);
    }

    @GetMapping("/detail")
    public ResultDTO<StrategyDetailResp> get(@RequestParam("business_identity") String businessIdentity,
        @RequestParam("prevention_type") String preventionType, @RequestParam("name") String name) {
        return strategyConfigurationService.get(businessIdentity, preventionType, name);
    }

    @PostMapping
    public ResultDTO<Void> create(@RequestBody CreateStrategyReq createStrategyReq) {
        return strategyConfigurationService.create(createStrategyReq);
    }

    @PutMapping
    public ResultDTO<Void> update(@RequestBody UpdateStrategyReq updateStrategyReq) {
        return strategyConfigurationService.update(updateStrategyReq);
    }
}
