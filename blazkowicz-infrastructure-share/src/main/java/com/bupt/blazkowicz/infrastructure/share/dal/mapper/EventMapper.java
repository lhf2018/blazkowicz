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

import com.bupt.blazkowicz.infrastructure.share.dal.dataobject.EventDO;

/**
 * @author lhf2018
 */
@Repository
@Mapper
public interface EventMapper {

    @Select("select * from tb_blazkowicz_event where event_id=#{eventId}")
    @Results({@Result(property = "id", column = "id"), @Result(property = "eventId", column = "event_id"),
        @Result(property = "version", column = "version"), @Result(property = "name", column = "name"),
        @Result(property = "code", column = "code"), @Result(property = "lastOperator", column = "last_operator"),
        @Result(property = "description", column = "description"),
        @Result(property = "accessMethod", column = "access_method"), @Result(property = "features", column = "features"),
        @Result(property = "strategyPack", column = "strategy_pack"),
        @Result(property = "gmtCreate", column = "gmt_create"),
        @Result(property = "gmtModified", column = "gmt_modified")})
    EventDO getByEventId(@Param("eventId") Long eventId);

    @Select("select * from tb_blazkowicz_event where code=#{code}")
    @Results({@Result(property = "id", column = "id"), @Result(property = "eventId", column = "event_id"),
        @Result(property = "version", column = "version"), @Result(property = "name", column = "name"),
        @Result(property = "code", column = "code"), @Result(property = "lastOperator", column = "last_operator"),
        @Result(property = "description", column = "description"),
        @Result(property = "accessMethod", column = "access_method"), @Result(property = "features", column = "features"),
        @Result(property = "strategyPack", column = "strategy_pack"),
        @Result(property = "gmtCreate", column = "gmt_create"),
        @Result(property = "gmtModified", column = "gmt_modified")})
    EventDO getByCode(@Param("code") String code);

    @Select("select * from tb_blazkowicz_event order by event_id desc")
    @Results({@Result(property = "id", column = "id"), @Result(property = "eventId", column = "event_id"),
        @Result(property = "version", column = "version"), @Result(property = "name", column = "name"),
        @Result(property = "code", column = "code"), @Result(property = "lastOperator", column = "last_operator"),
        @Result(property = "description", column = "description"),
        @Result(property = "accessMethod", column = "access_method"), @Result(property = "features", column = "features"),
        @Result(property = "strategyPack", column = "strategy_pack"),
        @Result(property = "gmtCreate", column = "gmt_create"),
        @Result(property = "gmtModified", column = "gmt_modified")})
    List<EventDO> listAll();

    @Insert("insert into tb_blazkowicz_event(event_id, version, name, code, last_operator, description, access_method, "
        + "features, strategy_pack) values(#{eventId}, 0, #{name}, #{code}, #{lastOperator}, #{description}, "
        + "#{accessMethod}, #{features}, #{strategyPack})")
    int insert(EventDO eventDO);

    @Update("update tb_blazkowicz_event set name=#{name}, last_operator=#{lastOperator}, description=#{description}, "
        + "access_method=#{accessMethod}, features=#{features}, strategy_pack=#{strategyPack}, version=version+1 "
        + "where event_id=#{eventId} and version=#{version}")
    int update(EventDO eventDO);
}
