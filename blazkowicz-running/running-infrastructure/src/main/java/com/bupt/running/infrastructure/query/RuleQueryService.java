package com.bupt.running.infrastructure.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bupt.domain.share.inf.NosqlInfService;
import com.bupt.running.infrastructure.resp.RuleParamResp;
import com.bupt.running.infrastructure.resp.RuleScriptResp;

/**
 * @author lhf2018
 * @date 2022/10/29 15:29
 */
@Component
public class RuleQueryService {
    @Autowired
    private NosqlInfService nosqlInfService;

    public RuleScriptResp getRuleScript(String businessIdentity, String preventionType, String ruleName) {
        String key = businessIdentity + "_" + preventionType + "_" + ruleName;
        String value = nosqlInfService.getValue(key);
        // todo nosql+内容解析
        RuleScriptResp ruleScriptResp = new RuleScriptResp();
        return ruleScriptResp;
    }

    public RuleParamResp getRuleParamResp(String businessIdentity, String preventionType, String ruleName) {
        String key = businessIdentity + "_" + preventionType + "_" + ruleName;
        String value = nosqlInfService.getValue(key);
        // todo nosql+内容解析
        RuleParamResp ruleParamResp = new RuleParamResp();
        return ruleParamResp;
    }
}
