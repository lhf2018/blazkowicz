package com.bupt.blazkowicz.infrastructure.share.dal.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import com.bupt.blazkowicz.infrastructure.share.dal.dataobject.RuleDO;

/**
 * @author lhf2018
 * @date 2023/1/8 0:40
 */
@Repository
@Mapper
public interface RuleMapper {
    @Select("select * from tb_blazkowicz_rule where rule_name=#{ruleName}")
    @Results({@Result(property = "id", column = "id"), @Result(property = "ruleId", column = "rule_id"),
        @Result(property = "version", column = "version"), @Result(property = "ruleName", column = "rule_name"),
        @Result(property = "ruleScript", column = "rule_script"),
        @Result(property = "conditions", column = "conditions"),
        @Result(property = "leftParamType", column = "left_param_type"),})
    RuleDO get(@Param("ruleName") String ruleName);

    @Insert("insert into tb_blazkowicz_rule(rule_id, version, rule_name, rule_script, conditions, left_param_type) values(#{ruleId}, 0, #{ruleName}, #{ruleScript}, #{conditions}, #{leftParamType})")
    int insert(RuleDO ruleDO);

    @Update("update tb_blazkowicz_rule set rule_script=#{ruleScript} and conditions=#{conditions} and left_param_type=#{leftParamType} where rule_name=#{ruleName} and version=#{version}")
    int update(RuleDO ruleDO);
}
