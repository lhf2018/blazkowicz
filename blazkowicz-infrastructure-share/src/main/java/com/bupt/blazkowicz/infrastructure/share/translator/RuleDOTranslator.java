package com.bupt.blazkowicz.infrastructure.share.translator;

import java.util.Collections;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bupt.blazkowicz.domain.share.entity.Condition;
import com.bupt.blazkowicz.domain.share.entity.LeftParamType;
import com.bupt.blazkowicz.domain.share.entity.Rule;
import com.bupt.blazkowicz.infrastructure.share.dal.dataobject.RuleDO;

/**
 * @author lhf2018
 */
public final class RuleDOTranslator {

    private RuleDOTranslator() {}

    public static RuleDO toDO(Rule rule) {
        RuleDO ruleDO = new RuleDO();
        ruleDO.setRuleId(String.valueOf(rule.getRuleId()));
        ruleDO.setRuleName(rule.getRuleName());
        ruleDO.setConditions(JSON.toJSONString(rule.getConditionList()));
        ruleDO.setLogic(rule.getLogic());
        List<Condition> conditionList = rule.getConditionList();
        if (conditionList != null && !conditionList.isEmpty()) {
            LeftParamType leftParamType = conditionList.get(0).getLeftParamType();
            if (leftParamType != null) {
                ruleDO.setLeftParamType(leftParamType.name());
            }
        }
        return ruleDO;
    }

    public static Rule toDomain(RuleDO ruleDO) {
        if (ruleDO == null) {
            return null;
        }
        List<Condition> conditionList = Collections.emptyList();
        if (ruleDO.getConditions() != null) {
            conditionList = JSON.parseObject(ruleDO.getConditions(), new TypeReference<List<Condition>>() {});
        }
        return new Rule(Long.valueOf(ruleDO.getRuleId()), ruleDO.getRuleName(), conditionList, ruleDO.getLogic());
    }
}
