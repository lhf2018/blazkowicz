package com.bupt.blazkowicz.infrastructure.share.repo;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.bupt.blazkowicz.domain.share.entity.BusinessIdentity;
import com.bupt.blazkowicz.domain.share.entity.PreventionType;
import com.bupt.blazkowicz.domain.share.entity.Rule;
import com.bupt.blazkowicz.domain.share.repo.RuleRepo;
import com.bupt.blazkowicz.infrastructure.share.dal.dataobject.RuleDO;
import com.bupt.blazkowicz.infrastructure.share.dal.mapper.RuleMapper;
import com.bupt.blazkowicz.infrastructure.share.inf.CacheInfService;
import com.bupt.blazkowicz.infrastructure.share.inf.CacheKeyUtil;
import com.bupt.blazkowicz.infrastructure.share.translator.RuleDOTranslator;

/**
 * @author lhf2018
 */
@Component
public class RuleRepoImpl implements RuleRepo {
    @Autowired
    private CacheInfService cacheInfService;
    @Autowired
    private RuleMapper ruleMapper;

    @Override
    public List<Rule> get(BusinessIdentity businessIdentity, PreventionType preventionType) {
        List<RuleDO> ruleDOList =
            ruleMapper.listByBusinessAndPrevention(businessIdentity.name(), preventionType.name());
        if (ruleDOList == null || ruleDOList.isEmpty()) {
            return Collections.emptyList();
        }
        return ruleDOList.stream().map(RuleDOTranslator::toDomain).collect(Collectors.toList());
    }

    @Override
    public Rule get(Long ruleId) {
        String cacheKey = CacheKeyUtil.ruleKey(ruleId);
        String cached = cacheInfService.get(cacheKey);
        if (cached != null) {
            return RuleDOTranslator.toDomain(JSON.parseObject(cached, RuleDO.class));
        }
        RuleDO ruleDO = ruleMapper.getByRuleId(ruleId);
        if (ruleDO == null) {
            return null;
        }
        cacheInfService.put(cacheKey, JSON.toJSONString(ruleDO), CacheKeyUtil.defaultExpireSeconds());
        return RuleDOTranslator.toDomain(ruleDO);
    }

    @Override
    public void save(Rule rule) {
        RuleDO ruleDO = RuleDOTranslator.toDO(rule);
        RuleDO existing = ruleMapper.getByRuleId(rule.getRuleId());
        if (existing == null) {
            ruleMapper.insert(ruleDO);
        } else {
            ruleDO.setVersion(existing.getVersion());
            ruleMapper.update(ruleDO);
            ruleDO = ruleMapper.getByRuleId(rule.getRuleId());
        }
        cacheInfService.put(CacheKeyUtil.ruleKey(rule.getRuleId()), JSON.toJSONString(ruleDO),
            CacheKeyUtil.defaultExpireSeconds());
    }
}
