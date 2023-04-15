package com.bupt.blazkowicz.configuration.infrastructure.dal;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import com.bupt.blazkowicz.configuration.infrastructure.dal.dataobject.StrategyDO;

/**
 * @author lhf2018
 * @date 2023/1/1 16:12
 */
@Repository
@Mapper
public interface StrategyMapper {
    @Select("select * from tb_blazkowicz_strategy where strategy_id=#{strategyId}")
    @Results({@Result(property = "strategyId", column = "strategy_id"),
        @Result(property = "gmtCreate", column = "gmt_create"),
        @Result(property = "gmtModified", column = "gmt_modified"), @Result(property = "version", column = "version"),
        @Result(property = "businessIdentity", column = "business_identity"),
        @Result(property = "preventionType", column = "prevention_type"), @Result(property = "name", column = "name"),
        @Result(property = "description", column = "description"),
        @Result(property = "introducedRuleId", column = "introduced_ruleId"),
        @Result(property = "disposalList", column = "disposal_list"),})
    StrategyDO get(@Param("strategyId") Long strategyId);

    @Select("select * from tb_blazkowicz_strategy where business_identity=#{businessIdentity} and prevention_type=#{preventionType} and name=#{name}")
    @Results({@Result(property = "strategyId", column = "strategy_id"),
        @Result(property = "gmtCreate", column = "gmt_create"),
        @Result(property = "gmtModified", column = "gmt_modified"), @Result(property = "version", column = "version"),
        @Result(property = "businessIdentity", column = "business_identity"),
        @Result(property = "preventionType", column = "prevention_type"), @Result(property = "name", column = "name"),
        @Result(property = "description", column = "description"),
        @Result(property = "introducedRuleId", column = "introduced_ruleId"),
        @Result(property = "disposalList", column = "disposal_list"),})
    StrategyDO get(@Param("businessIdentity") String businessIdentity, @Param("preventionType") String preventionType,
        @Param("name") String name);

    @Insert("insert into tb_blazkowicz_strategy(strategy_id, gmt_create, gmt_modified, version, business_identity, prevention_type, name, description, introduced_ruleId, disposal_list) "
        + "values(#{strategyId}, #{gmtCreate}, #{gmtModified}, 0, #{businessIdentity}, #{preventionType}, #{name}, #{description}, #{introducedRuleId}, #{disposalList})")
    int insert(StrategyDO strategyDO);

    @Update("update tb_blazkowicz_strategy set gmt_modified=now() and version=#{version}+1 and description=#{description} and introduced_ruleId=#{introducedRuleId} and disposal_list=#{disposalList} where strategy_id=#{strategyId} and version=#{version}")
    int update(StrategyDO strategyDO);
}
