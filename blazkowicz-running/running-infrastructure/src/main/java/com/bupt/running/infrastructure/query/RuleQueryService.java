package com.bupt.running.infrastructure.query;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bupt.domain.share.inf.NosqlInfService;
import com.bupt.running.infrastructure.resp.RuleParamResp;
import com.bupt.running.infrastructure.resp.RuleScriptResp;
import com.google.common.collect.Lists;

/**
 * @author lhf2018
 * @date 2022/10/29 15:29
 */
@Component
public class RuleQueryService {
    @Autowired
    private NosqlInfService nosqlInfService;

    private static final String RULE_SCRIPT_PREFIX = "rule_script_";
    private static final String RULE_PARAM_PREFIX = "rule_param_";
    private static final String RULE_LIST_PREFIX = "rule_list_";

    /**
     * 返回ruleId列表
     * 
     * @param businessIdentity
     * @param preventionType
     * @return
     */
    public List<String> getRuleList(String businessIdentity, String preventionType) {
        String key = RULE_LIST_PREFIX + businessIdentity + "_" + preventionType;
        String value = nosqlInfService.getValue(key);
        // todo nosql+内容解析
        return Lists.newArrayList();
    }

    public RuleScriptResp getRuleScriptResp(String businessIdentity, String preventionType, String ruleId) {
        String key = RULE_SCRIPT_PREFIX + businessIdentity + "_" + preventionType + "_" + ruleId;;
        String value = nosqlInfService.getValue(key);
        // todo nosql+内容解析
        return new RuleScriptResp();
    }

    public RuleParamResp getRuleParamResp(String businessIdentity, String preventionType, String ruleId) {
        String key = RULE_PARAM_PREFIX + businessIdentity + "_" + preventionType + "_" + ruleId;
        String value = nosqlInfService.getValue(key);
        // todo nosql+内容解析
        RuleParamResp ruleParamResp = new RuleParamResp();
        return ruleParamResp;
    }

}
