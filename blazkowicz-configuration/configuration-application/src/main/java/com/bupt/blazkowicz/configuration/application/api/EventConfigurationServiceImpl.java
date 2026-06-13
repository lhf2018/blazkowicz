package com.bupt.blazkowicz.configuration.application.api;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bupt.blazkowicz.client.share.dto.result.ResultDTO;
import com.bupt.blazkowicz.common.exception.BlazkowiczException;
import com.bupt.blazkowicz.common.exception.Code;
import com.bupt.blazkowicz.configuration.client.api.EventConfigurationService;
import com.bupt.blazkowicz.configuration.client.api.req.AddFeatureReq;
import com.bupt.blazkowicz.configuration.client.api.req.CreateEventReq;
import com.bupt.blazkowicz.configuration.client.api.req.UpdateEventReq;
import com.bupt.blazkowicz.configuration.client.api.req.UpdateStrategyPackReq;
import com.bupt.blazkowicz.configuration.client.api.resp.EventDetailResp;
import com.bupt.blazkowicz.configuration.client.api.resp.FeatureResp;
import com.bupt.blazkowicz.configuration.client.api.resp.StrategyPackResp;
import com.bupt.blazkowicz.configuration.domain.entity.AccessMethod;
import com.bupt.blazkowicz.configuration.domain.entity.Event;
import com.bupt.blazkowicz.configuration.domain.entity.EventInfo;
import com.bupt.blazkowicz.configuration.domain.entity.FeatureType;
import com.bupt.blazkowicz.configuration.domain.entity.SimpleFeature;
import com.bupt.blazkowicz.configuration.domain.entity.StrategyPack;
import com.bupt.blazkowicz.configuration.domain.factory.EventFactory;
import com.bupt.blazkowicz.configuration.domain.repo.EventRepo;
import com.bupt.blazkowicz.configuration.infrastructure.translator.EventDOTranslator;
import com.bupt.blazkowicz.domain.share.entity.BusinessIdentity;
import com.bupt.blazkowicz.domain.share.entity.PreventionType;
import com.google.common.collect.Lists;

/**
 * @author lhf2018
 */
@Component
public class EventConfigurationServiceImpl implements EventConfigurationService {
    @Autowired
    private EventRepo eventRepo;

    @Override
    public ResultDTO<Void> create(CreateEventReq createEventReq) {
        validateCreateReq(createEventReq);
        if (eventRepo.getByCode(createEventReq.getCode()) != null) {
            throw new BlazkowiczException(Code.PARAMETER_INVALID, "事件编码已存在");
        }
        EventInfo eventInfo = new EventInfo(createEventReq.getName(), createEventReq.getCode(),
            createEventReq.getLastOperator(), createEventReq.getDescription(),
            AccessMethod.valueOf(createEventReq.getAccessMethod()));
        Event event = EventFactory.create(eventInfo);
        eventRepo.save(event);
        return ResultDTO.buildSuccessResult();
    }

    @Override
    public ResultDTO<Void> update(Long eventId, UpdateEventReq updateEventReq) {
        Event event = requireEvent(eventId);
        EventInfo updatedInfo = new EventInfo(StringUtils.defaultIfBlank(updateEventReq.getName(), event.getEventInfo().getName()),
            event.getEventInfo().getCode(),
            StringUtils.defaultIfBlank(updateEventReq.getLastOperator(), event.getEventInfo().getLastOperator()),
            StringUtils.defaultIfBlank(updateEventReq.getDescription(), event.getEventInfo().getDesc()),
            AccessMethod.valueOf(StringUtils.defaultIfBlank(updateEventReq.getAccessMethod(),
                event.getEventInfo().getAccessMethod().name())));
        event.updateEventInfo(updatedInfo);
        return ResultDTO.buildSuccessResult();
    }

    @Override
    public ResultDTO<List<EventDetailResp>> list() {
        List<Event> events = eventRepo.listAll();
        if (events == null || events.isEmpty()) {
            return ResultDTO.buildSuccessResult(Collections.emptyList());
        }
        return ResultDTO.buildSuccessResult(events.stream().map(this::toDetailResp).collect(Collectors.toList()));
    }

    @Override
    public ResultDTO<EventDetailResp> get(Long eventId) {
        return ResultDTO.buildSuccessResult(toDetailResp(requireEvent(eventId)));
    }

    @Override
    public ResultDTO<EventDetailResp> getByCode(String code) {
        Event event = eventRepo.getByCode(code);
        if (event == null) {
            throw new BlazkowiczException(Code.PARAMETER_INVALID, "事件不存在");
        }
        return ResultDTO.buildSuccessResult(toDetailResp(event));
    }

    @Override
    public ResultDTO<Void> addFeature(Long eventId, AddFeatureReq addFeatureReq) {
        Event event = requireEvent(eventId);
        if (StringUtils.isAnyBlank(addFeatureReq.getType(), addFeatureReq.getName())) {
            throw new BlazkowiczException(Code.PARAMETER_INVALID, "特征类型和名称不能为空");
        }
        SimpleFeature feature = new SimpleFeature(FeatureType.valueOf(addFeatureReq.getType()), addFeatureReq.getName(),
            addFeatureReq.getDescription());
        event.addFeature(feature);
        return ResultDTO.buildSuccessResult();
    }

    @Override
    public ResultDTO<Void> updateStrategyPack(Long eventId, UpdateStrategyPackReq updateStrategyPackReq) {
        Event event = requireEvent(eventId);
        if (StringUtils.isAnyBlank(updateStrategyPackReq.getBusinessIdentity(), updateStrategyPackReq.getPreventionType(),
            updateStrategyPackReq.getName())) {
            throw new BlazkowiczException(Code.PARAMETER_INVALID, "策略包标识不完整");
        }
        StrategyPack strategyPack = new StrategyPack(event.getEventInfo().getCode(),
            updateStrategyPackReq.getLastOperator(), updateStrategyPackReq.getDescription(), Lists.newArrayList(),
            BusinessIdentity.valueOf(updateStrategyPackReq.getBusinessIdentity()),
            PreventionType.valueOf(updateStrategyPackReq.getPreventionType()), updateStrategyPackReq.getName());
        event.updateStrategyPack(strategyPack, updateStrategyPackReq.getStrategyNames());
        return ResultDTO.buildSuccessResult();
    }

    private Event requireEvent(Long eventId) {
        Event event = eventRepo.get(eventId);
        if (event == null) {
            throw new BlazkowiczException(Code.PARAMETER_INVALID, "事件不存在");
        }
        return event;
    }

    private void validateCreateReq(CreateEventReq req) {
        if (StringUtils.isAnyBlank(req.getName(), req.getCode(), req.getAccessMethod())) {
            throw new BlazkowiczException(Code.PARAMETER_INVALID, "事件名称、编码、接入方式不能为空");
        }
    }

    private EventDetailResp toDetailResp(Event event) {
        EventDetailResp resp = new EventDetailResp();
        resp.setEventId(event.getEventId());
        resp.setName(event.getEventInfo().getName());
        resp.setCode(event.getEventInfo().getCode());
        resp.setLastOperator(event.getEventInfo().getLastOperator());
        resp.setDescription(event.getEventInfo().getDesc());
        resp.setAccessMethod(event.getEventInfo().getAccessMethod().name());
        resp.setVersion(event.getVersion());
        resp.setFeatures(EventDOTranslator.toFeatureItems(event.getFeatureList()).stream().map(item -> {
            FeatureResp featureResp = new FeatureResp();
            featureResp.setType(item.getType());
            featureResp.setName(item.getName());
            featureResp.setDescription(item.getDescription());
            return featureResp;
        }).collect(Collectors.toList()));
        StrategyPackResp packResp = new StrategyPackResp();
        if (event.getStrategyPack() != null) {
            StrategyPack pack = event.getStrategyPack();
            packResp.setOwningEvent(pack.getOwningEvent());
            packResp.setLastOperator(pack.getLastOperator());
            packResp.setDescription(pack.getDescription());
            packResp.setBusinessIdentity(pack.getBusinessIdentity().name());
            packResp.setPreventionType(pack.getPreventionType().name());
            packResp.setName(pack.getName());
        }
        packResp.setStrategyNames(event.getStrategyNameRefs());
        resp.setStrategyPack(packResp.getName() != null || packResp.getBusinessIdentity() != null ? packResp : null);
        return resp;
    }
}
