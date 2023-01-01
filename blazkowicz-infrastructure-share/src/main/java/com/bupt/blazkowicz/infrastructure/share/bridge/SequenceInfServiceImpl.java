package com.bupt.blazkowicz.infrastructure.share.bridge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bupt.blazkowicz.domain.share.bridge.SequenceInfService;
import com.bupt.blazkowicz.domain.share.bridge.enums.SequenceType;
import com.bupt.blazkowicz.infrastructure.share.dal.dataobject.SequenceDO;
import com.bupt.blazkowicz.infrastructure.share.dal.mapper.SequenceMapper;

/**
 * @author lhf2018
 * @date 2023/1/1 17:10
 */
@Component
public class SequenceInfServiceImpl implements SequenceInfService {
    @Autowired
    private SequenceMapper sequenceMapper;

    @Override
    public synchronized Integer nextSequenceId(SequenceType sequenceType) {
        SequenceDO sequenceDO = sequenceMapper.get(sequenceType.name());
        if (sequenceDO != null) {
            sequenceMapper.update(sequenceDO);
            return sequenceDO.getSequenceId();
        } else {
            SequenceDO newSequenceDO = new SequenceDO();
            newSequenceDO.setSequenceType(sequenceType.name());
            sequenceMapper.insert(newSequenceDO);
            return sequenceMapper.get(sequenceType.name()).getSequenceId();
        }
    }
}
