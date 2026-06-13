package com.bupt.blazkowicz.running.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bupt.blazkowicz.client.share.dto.result.ResultDTO;
import com.bupt.blazkowicz.running.client.api.PreventionService;
import com.bupt.blazkowicz.running.client.api.req.PreventionReq;
import com.bupt.blazkowicz.running.client.api.resp.PreventionResultResp;

import lombok.extern.slf4j.Slf4j;

/**
 * @author lhf2018
 */
@RestController
@RequestMapping("/prevention")
@Slf4j
public class PreventionController {
    @Autowired
    private PreventionService preventionService;

    @GetMapping("/request")
    public ResultDTO<PreventionResultResp> processGet(@RequestParam("prevention_type") String preventionType,
        @RequestParam("business_identity") String businessIdentity, @RequestParam("user_id") String userId) {
        return preventionService.request(buildReq(preventionType, businessIdentity, userId));
    }

    @PostMapping("/request")
    public ResultDTO<PreventionResultResp> processPost(@RequestBody PreventionReq preventionReq) {
        return preventionService.request(preventionReq);
    }

    private PreventionReq buildReq(String preventionType, String businessIdentity, String userId) {
        PreventionReq preventionReq = new PreventionReq();
        preventionReq.setPreventionType(preventionType);
        preventionReq.setBusinessIdentity(businessIdentity);
        preventionReq.setUserId(userId);
        return preventionReq;
    }
}
