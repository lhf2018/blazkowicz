package com.bupt.blazkowicz.infrastructure.share.dal.dataobject;

import lombok.Getter;
import lombok.Setter;

/**
 * @author lhf2018
 * @date 2023/1/1 16:29
 */
@Getter
@Setter
public class SequenceDO {
    private Integer id;
    private Integer sequenceId;
    private String sequenceType;
}
