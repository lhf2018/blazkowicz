package com.bupt.blazkowicz.infrastructure.share.dal.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.bupt.blazkowicz.infrastructure.share.dal.dataobject.RuleDO;

/**
 * @author lhf2018
 */
@Repository
@Mapper
public interface RuleMapper {

    @Select("select * from tb_blazkowicz_rule where rule_name=#{ruleName}")
    @Results({@Result(property = "id", column = "id"), @Result(property = "ruleId", column = "rule_id"),
        @Result(property = "version", column = "version"), @Result(property = "ruleName", column = "rule_name"),
        @Result(property = "conditions", column = "conditions"), @Result(property = "logic", column = "logic"),
        @Result(property = "leftParamType", column = "left_param_type")})
    RuleDO getByName(@Param("ruleName") String ruleName);

    @Select("select * from tb_blazkowicz_rule where rule_id=#{ruleId}")
    @Results({@Result(property = "id", column = "id"), @Result(property = "ruleId", column = "rule_id"),
        @Result(property = "version", column = "version"), @Result(property = "ruleName", column = "rule_name"),
        @Result(property = "conditions", column = "conditions"), @Result(property = "logic", column = "logic"),
        @Result(property = "leftParamType", column = "left_param_type")})
    RuleDO getByRuleId(@Param("ruleId") Long ruleId);

    @Select("select * from tb_blazkowicz_rule where rule_id in "
        + "(select introduced_rule_id from tb_blazkowicz_strategy "
        + "where business_identity=#{businessIdentity} and prevention_type=#{preventionType})")
    @Results({@Result(property = "id", column = "id"), @Result(property = "ruleId", column = "rule_id"),
        @Result(property = "version", column = "version"), @Result(property = "ruleName", column = "rule_name"),
        @Result(property = "conditions", column = "conditions"), @Result(property = "logic", column = "logic"),
        @Result(property = "leftParamType", column = "left_param_type")})
    List<RuleDO> listByBusinessAndPrevention(@Param("businessIdentity") String businessIdentity,
        @Param("preventionType") String preventionType);

    @Select("select * from tb_blazkowicz_rule order by rule_id desc")
    @Results({@Result(property = "id", column = "id"), @Result(property = "ruleId", column = "rule_id"),
        @Result(property = "version", column = "version"), @Result(property = "ruleName", column = "rule_name"),
        @Result(property = "conditions", column = "conditions"), @Result(property = "logic", column = "logic"),
        @Result(property = "leftParamType", column = "left_param_type")})
    List<RuleDO> listAll();

    @Insert("insert into tb_blazkowicz_rule(rule_id, version, rule_name, conditions, logic, left_param_type) "
        + "values(#{ruleId}, 0, #{ruleName}, #{conditions}, #{logic}, #{leftParamType})")
    int insert(RuleDO ruleDO);

    @Update("update tb_blazkowicz_rule set conditions=#{conditions}, logic=#{logic}, left_param_type=#{leftParamType}, "
        + "version=version+1 where rule_id=#{ruleId} and version=#{version}")
    int update(RuleDO ruleDO);
}
