package com.bupt.infrastructure.share.query;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bupt.domain.share.entity.Rule;
import com.bupt.domain.share.repo.RuleRepo;
import com.bupt.infrastructure.share.inf.NosqlInfService;
import com.bupt.infrastructure.share.resp.RunningRuleResp;
import com.google.common.collect.Lists;

/**
 * 规则查询
 * 
 * @author lhf2018
 * @date 2022/10/29 15:29
 */
@Component
public class RuleQueryService {
    @Autowired
    private NosqlInfService nosqlInfService;
    @Autowired
    private RuleRepo ruleRepo;

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
        return JSON.parseObject(value, new TypeReference<List<String>>() {});
    }

    public List<RunningRuleResp> getRunningRuleRespList(String businessIdentity, String preventionType) {
        String key = RULE_LIST_PREFIX + businessIdentity + "_" + preventionType;
        String value = nosqlInfService.getValue(key);
        List<String> ruleIds = JSON.parseObject(value, new TypeReference<List<String>>() {});

        List<RunningRuleResp> runningRuleRespList = Lists.newArrayList();
        ruleIds.forEach(ruleId -> {
            Rule rule = ruleRepo.get(ruleId);
            RunningRuleResp resp = new RunningRuleResp();
            resp.setName(rule.getRuleName());
            resp.setScript(rule.getRuleScript().getContent());
            resp.setParams(Lists.newArrayList(null, rule.getRightParam().getValue()).toArray());
            resp.setRuleId(rule.getRuleId());
            runningRuleRespList.add(resp);
        });
        return runningRuleRespList;
    }
}
