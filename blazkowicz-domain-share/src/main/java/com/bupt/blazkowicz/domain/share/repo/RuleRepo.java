package com.bupt.blazkowicz.domain.share.repo;

import java.util.List;

import com.bupt.blazkowicz.domain.share.entity.BusinessIdentity;
import com.bupt.blazkowicz.domain.share.entity.PreventionType;
import com.bupt.blazkowicz.domain.share.entity.Rule;

/**
 * 规则仓库
 * 
 * @author lhf2018
 * @date 2022/11/5 15:17
 */
public interface RuleRepo {
    /**
     * 根据业务身份获取策略
     * 
     * @param businessIdentity
     * @param preventionType
     * @return
     */
    List<Rule> get(BusinessIdentity businessIdentity, PreventionType preventionType);

    /**
     * 根据id获取单条策略
     * 
     * @param ruleId
     * @return
     */
    Rule get(Long ruleId);

    /**
     * 保存策略
     * 
     * @param rule
     */
    void save(Rule rule);
}
