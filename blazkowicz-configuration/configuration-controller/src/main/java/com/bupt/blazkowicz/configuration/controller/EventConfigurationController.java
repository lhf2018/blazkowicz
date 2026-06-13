package com.bupt.blazkowicz.configuration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bupt.blazkowicz.client.share.dto.result.ResultDTO;
import com.bupt.blazkowicz.configuration.client.api.EventConfigurationService;
import com.bupt.blazkowicz.configuration.client.api.req.AddFeatureReq;
import com.bupt.blazkowicz.configuration.client.api.req.CreateEventReq;
import com.bupt.blazkowicz.configuration.client.api.req.UpdateEventReq;
import com.bupt.blazkowicz.configuration.client.api.req.UpdateStrategyPackReq;
import com.bupt.blazkowicz.configuration.client.api.resp.EventDetailResp;

/**
 * @author lhf2018
 */
@RestController
@RequestMapping("/config/events")
public class EventConfigurationController {
    @Autowired
    private EventConfigurationService eventConfigurationService;

    @GetMapping
    public ResultDTO<?> list() {
        return eventConfigurationService.list();
    }

    @GetMapping("/{eventId}")
    public ResultDTO<EventDetailResp> get(@PathVariable Long eventId) {
        return eventConfigurationService.get(eventId);
    }

    @GetMapping("/by-code")
    public ResultDTO<EventDetailResp> getByCode(@RequestParam("code") String code) {
        return eventConfigurationService.getByCode(code);
    }

    @PostMapping
    public ResultDTO<Void> create(@RequestBody CreateEventReq createEventReq) {
        return eventConfigurationService.create(createEventReq);
    }

    @PutMapping("/{eventId}")
    public ResultDTO<Void> update(@PathVariable Long eventId, @RequestBody UpdateEventReq updateEventReq) {
        return eventConfigurationService.update(eventId, updateEventReq);
    }

    @PostMapping("/{eventId}/features")
    public ResultDTO<Void> addFeature(@PathVariable Long eventId, @RequestBody AddFeatureReq addFeatureReq) {
        return eventConfigurationService.addFeature(eventId, addFeatureReq);
    }

    @PutMapping("/{eventId}/strategy-pack")
    public ResultDTO<Void> updateStrategyPack(@PathVariable Long eventId,
        @RequestBody UpdateStrategyPackReq updateStrategyPackReq) {
        return eventConfigurationService.updateStrategyPack(eventId, updateStrategyPackReq);
    }
}
