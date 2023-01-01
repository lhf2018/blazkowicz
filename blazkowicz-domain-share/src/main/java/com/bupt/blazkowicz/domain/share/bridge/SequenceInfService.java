package com.bupt.blazkowicz.domain.share.bridge;

import com.bupt.blazkowicz.domain.share.bridge.enums.SequenceType;

/**
 * @author lhf2018
 * @date 2022/12/31 23:05
 */
public interface SequenceInfService {
    Integer nextSequenceId(SequenceType sequenceType);
}
