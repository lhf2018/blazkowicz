package com.bupt.blazkowicz.infrastructure.share.query;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bupt.blazkowicz.domain.share.entity.BusinessIdentity;
import com.bupt.blazkowicz.domain.share.entity.PreventionType;
import com.bupt.blazkowicz.domain.share.entity.Rule;
import com.bupt.blazkowicz.domain.share.repo.RuleRepo;
import com.bupt.blazkowicz.infrastructure.share.inf.CacheInfService;
import com.bupt.blazkowicz.infrastructure.share.inf.CacheKeyUtil;

/**
 * 规则查询
 *
 * @author lhf2018
 */
@Component
public class RuleQueryService {
    @Autowired
    private CacheInfService cacheInfService;
    @Autowired
    private RuleRepo ruleRepo;

    public List<Rule> getRuleRespList(BusinessIdentity businessIdentity, PreventionType preventionType) {
        String cacheKey = CacheKeyUtil.rulesKey(businessIdentity.name(), preventionType.name());
        String cached = cacheInfService.get(cacheKey);
        if (cached != null) {
            return JSON.parseObject(cached, new TypeReference<List<Rule>>() {});
        }
        List<Rule> rules = ruleRepo.get(businessIdentity, preventionType);
        if (rules == null) {
            rules = Collections.emptyList();
        }
        if (!rules.isEmpty()) {
            cacheInfService.put(cacheKey, JSON.toJSONString(rules), CacheKeyUtil.defaultExpireSeconds());
        }
        return rules;
    }

    public void invalidateRulesCache(String businessIdentity, String preventionType) {
        cacheInfService.delete(CacheKeyUtil.rulesKey(businessIdentity, preventionType));
    }
}
