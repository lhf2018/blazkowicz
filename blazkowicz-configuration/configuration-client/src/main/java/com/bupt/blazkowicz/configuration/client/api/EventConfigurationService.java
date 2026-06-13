package com.bupt.blazkowicz.configuration.client.api;

import java.util.List;

import com.bupt.blazkowicz.client.share.dto.result.ResultDTO;
import com.bupt.blazkowicz.configuration.client.api.req.AddFeatureReq;
import com.bupt.blazkowicz.configuration.client.api.req.CreateEventReq;
import com.bupt.blazkowicz.configuration.client.api.req.UpdateEventReq;
import com.bupt.blazkowicz.configuration.client.api.req.UpdateStrategyPackReq;
import com.bupt.blazkowicz.configuration.client.api.resp.EventDetailResp;

/**
 * @author lhf2018
 */
public interface EventConfigurationService {

    ResultDTO<Void> create(CreateEventReq createEventReq);

    ResultDTO<Void> update(Long eventId, UpdateEventReq updateEventReq);

    ResultDTO<List<EventDetailResp>> list();

    ResultDTO<EventDetailResp> get(Long eventId);

    ResultDTO<EventDetailResp> getByCode(String code);

    ResultDTO<Void> addFeature(Long eventId, AddFeatureReq addFeatureReq);

    ResultDTO<Void> updateStrategyPack(Long eventId, UpdateStrategyPackReq updateStrategyPackReq);
}
