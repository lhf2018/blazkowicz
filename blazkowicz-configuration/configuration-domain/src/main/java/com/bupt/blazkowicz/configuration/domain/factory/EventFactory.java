package com.bupt.blazkowicz.configuration.domain.factory;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bupt.blazkowicz.configuration.domain.entity.Event;
import com.bupt.blazkowicz.configuration.domain.entity.EventInfo;
import com.bupt.blazkowicz.domain.share.bridge.SequenceInfService;
import com.bupt.blazkowicz.domain.share.bridge.enums.SequenceType;

/**
 * @author lhf2018
 */
@Component
public class EventFactory {
    private static SequenceInfService sequenceInfService;

    @Autowired
    public void init(SequenceInfService sequenceInfService) {
        EventFactory.sequenceInfService = sequenceInfService;
    }

    public static Event create(EventInfo eventInfo) {
        return new Event(sequenceInfService.nextSequenceId(SequenceType.EVENT_ID), new Date(), new Date(), eventInfo);
    }
}
