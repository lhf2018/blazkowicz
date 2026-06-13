package com.bupt.blazkowicz.configuration.infrastructure.repo;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bupt.blazkowicz.configuration.domain.entity.Event;
import com.bupt.blazkowicz.configuration.domain.repo.EventRepo;
import com.bupt.blazkowicz.configuration.infrastructure.translator.EventDOTranslator;
import com.bupt.blazkowicz.infrastructure.share.dal.dataobject.EventDO;
import com.bupt.blazkowicz.infrastructure.share.dal.mapper.EventMapper;

/**
 * @author lhf2018
 */
@Component
public class EventRepoImpl implements EventRepo {
    @Autowired
    private EventMapper eventMapper;

    @Override
    public void save(Event event) {
        EventDO eventDO = EventDOTranslator.toDO(event);
        EventDO existing = eventMapper.getByEventId(event.getEventId());
        if (existing == null) {
            eventDO.setVersion(0);
            eventMapper.insert(eventDO);
        } else {
            eventDO.setVersion(existing.getVersion());
            eventMapper.update(eventDO);
        }
    }

    @Override
    public Event get(Long eventId) {
        return EventDOTranslator.toDomain(eventMapper.getByEventId(eventId));
    }

    @Override
    public Event getByCode(String code) {
        return EventDOTranslator.toDomain(eventMapper.getByCode(code));
    }

    @Override
    public List<Event> listAll() {
        List<EventDO> eventDOList = eventMapper.listAll();
        if (eventDOList == null || eventDOList.isEmpty()) {
            return Collections.emptyList();
        }
        return eventDOList.stream().map(EventDOTranslator::toDomain).collect(Collectors.toList());
    }
}
