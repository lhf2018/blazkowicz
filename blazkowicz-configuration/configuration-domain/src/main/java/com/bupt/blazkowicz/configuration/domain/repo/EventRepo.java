package com.bupt.blazkowicz.configuration.domain.repo;

import java.util.List;

import com.bupt.blazkowicz.configuration.domain.entity.Event;

/**
 * @author lhf2018
 */
public interface EventRepo {
    void save(Event event);

    Event get(Long eventId);

    Event getByCode(String code);

    List<Event> listAll();
}
