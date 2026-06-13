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

import com.bupt.blazkowicz.infrastructure.share.dal.dataobject.StrategyDO;

/**
 * @author lhf2018
 */
@Repository
@Mapper
public interface StrategyMapper {

    @Select("select * from tb_blazkowicz_strategy where business_identity=#{businessIdentity} "
        + "and prevention_type=#{preventionType} and name=#{name}")
    @Results({@Result(property = "id", column = "id"), @Result(property = "strategyId", column = "strategy_id"),
        @Result(property = "version", column = "version"),
        @Result(property = "businessIdentity", column = "business_identity"),
        @Result(property = "preventionType", column = "prevention_type"), @Result(property = "name", column = "name"),
        @Result(property = "description", column = "description"),
        @Result(property = "introducedRuleId", column = "introduced_rule_id"),
        @Result(property = "disposalConfig", column = "disposal_config")})
    StrategyDO get(@Param("businessIdentity") String businessIdentity, @Param("preventionType") String preventionType,
        @Param("name") String name);

    @Select("select introduced_rule_id from tb_blazkowicz_strategy where business_identity=#{businessIdentity} "
        + "and prevention_type=#{preventionType}")
    List<Long> listRuleIds(@Param("businessIdentity") String businessIdentity,
        @Param("preventionType") String preventionType);

    @Select("select disposal_config from tb_blazkowicz_strategy where business_identity=#{businessIdentity} "
        + "and prevention_type=#{preventionType} and introduced_rule_id=#{ruleId} limit 1")
    String getDisposalConfig(@Param("businessIdentity") String businessIdentity,
        @Param("preventionType") String preventionType, @Param("ruleId") Long ruleId);

    @Select("select * from tb_blazkowicz_strategy where business_identity=#{businessIdentity} "
        + "and prevention_type=#{preventionType} order by strategy_id")
    @Results({@Result(property = "id", column = "id"), @Result(property = "strategyId", column = "strategy_id"),
        @Result(property = "version", column = "version"),
        @Result(property = "businessIdentity", column = "business_identity"),
        @Result(property = "preventionType", column = "prevention_type"), @Result(property = "name", column = "name"),
        @Result(property = "description", column = "description"),
        @Result(property = "introducedRuleId", column = "introduced_rule_id"),
        @Result(property = "disposalConfig", column = "disposal_config")})
    List<StrategyDO> listByBusinessAndPrevention(@Param("businessIdentity") String businessIdentity,
        @Param("preventionType") String preventionType);

    @Insert("insert into tb_blazkowicz_strategy(strategy_id, version, business_identity, prevention_type, name, "
        + "description, introduced_rule_id, disposal_config) values(#{strategyId}, 0, #{businessIdentity}, "
        + "#{preventionType}, #{name}, #{description}, #{introducedRuleId}, #{disposalConfig})")
    int insert(StrategyDO strategyDO);

    @Update("update tb_blazkowicz_strategy set description=#{description}, introduced_rule_id=#{introducedRuleId}, "
        + "disposal_config=#{disposalConfig}, version=version+1 where business_identity=#{businessIdentity} "
        + "and prevention_type=#{preventionType} and name=#{name} and version=#{version}")
    int update(StrategyDO strategyDO);
}
