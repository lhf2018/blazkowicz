package com.bupt.blazkowicz.infrastructure.share.dal.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import com.bupt.blazkowicz.infrastructure.share.dal.dataobject.SequenceDO;

/**
 * @author lhf2018
 * @date 2023/1/1 16:12
 */
@Repository
@Mapper
public interface SequenceMapper {
    @Select("select * from blazkowicz_sequence where sequence_type=#{sequenceType}")
    @Results({@Result(property = "id", column = "id"), @Result(property = "sequenceId", column = "sequence_id"),
        @Result(property = "sequenceType", column = "sequence_type")})
    SequenceDO get(@Param("sequenceType") String sequenceType);

    @Insert("insert into blazkowicz_sequence(sequence_id,sequence_type) values(1, #{sequenceType})")
    int insert(SequenceDO sequenceDO);

    @Update("update blazkowicz_sequence set sequence_id=#{sequenceId}+1 where sequence_type=#{sequenceType}")
    int update(SequenceDO sequenceDO);
}
