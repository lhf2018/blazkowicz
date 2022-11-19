package com.bupt.domain.share.repo;

import java.util.List;

import com.bupt.domain.share.entity.BusinessIdentity;
import com.bupt.domain.share.entity.PreventionType;
import com.bupt.domain.share.entity.Rule;

/**
 * 规则仓库
 * 
 * @author lhf2018
 * @date 2022/11/5 15:17
 */
public interface RuleRepo {
    List<Rule> get(BusinessIdentity businessIdentity, PreventionType preventionType);

    Rule get(String ruleId);
}
