package com.bupt.running.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bupt.running.client.api.PreventionService;
import com.bupt.running.client.api.req.PreventionReq;
import com.bupt.running.client.api.resp.PreventionResultResp;

import groovy.util.logging.Slf4j;

/**
 * @author lhf2018
 * @date 2022/11/5 16:44
 */
@RestController
@RequestMapping("/prevention")
@Slf4j
public class PreventionController {
    @Autowired
    private PreventionService preventionService;

    @RequestMapping("/request")
    public PreventionResultResp process(HttpServletRequest request) {
        PreventionReq preventionReq = new PreventionReq();
        String preventionType = request.getParameter("prevention_type");
        String businessIdentity = request.getParameter("business_identity");
        preventionReq.setPreventionType(preventionType);
        preventionReq.setBusinessIdentity(businessIdentity);
        return preventionService.request(preventionReq);
    }
}
