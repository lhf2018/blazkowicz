package com.bupt.blazkowicz.configuration.infrastructure.translator;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bupt.blazkowicz.configuration.domain.entity.AccessMethod;
import com.bupt.blazkowicz.configuration.domain.entity.Event;
import com.bupt.blazkowicz.configuration.domain.entity.EventInfo;
import com.bupt.blazkowicz.configuration.domain.entity.Feature;
import com.bupt.blazkowicz.configuration.domain.entity.FeatureType;
import com.bupt.blazkowicz.configuration.domain.entity.SimpleFeature;
import com.bupt.blazkowicz.configuration.domain.entity.StrategyPack;
import com.bupt.blazkowicz.configuration.infrastructure.dto.FeatureItemDTO;
import com.bupt.blazkowicz.configuration.infrastructure.dto.StrategyPackDTO;
import com.bupt.blazkowicz.domain.share.entity.BusinessIdentity;
import com.bupt.blazkowicz.domain.share.entity.PreventionType;
import com.bupt.blazkowicz.infrastructure.share.dal.dataobject.EventDO;
import com.google.common.collect.Lists;

/**
 * @author lhf2018
 */
public final class EventDOTranslator {

    private EventDOTranslator() {}

    public static EventDO toDO(Event event) {
        EventDO eventDO = new EventDO();
        eventDO.setEventId(event.getEventId());
        eventDO.setVersion(event.getVersion() != null ? event.getVersion() : 0);
        eventDO.setName(event.getEventInfo().getName());
        eventDO.setCode(event.getEventInfo().getCode());
        eventDO.setLastOperator(event.getEventInfo().getLastOperator());
        eventDO.setDescription(event.getEventInfo().getDesc());
        eventDO.setAccessMethod(event.getEventInfo().getAccessMethod().name());
        eventDO.setFeatures(JSON.toJSONString(toFeatureItems(event.getFeatureList())));
        eventDO.setStrategyPack(JSON.toJSONString(toStrategyPackDTO(event)));
        eventDO.setGmtCreate(event.getGmtCreate());
        eventDO.setGmtModified(event.getGmtModified());
        return eventDO;
    }

    public static Event toDomain(EventDO eventDO) {
        if (eventDO == null) {
            return null;
        }
        EventInfo eventInfo = new EventInfo(eventDO.getName(), eventDO.getCode(), eventDO.getLastOperator(),
            eventDO.getDescription(), AccessMethod.valueOf(eventDO.getAccessMethod()));
        List<Feature> featureList = toFeatures(eventDO.getFeatures());
        StrategyPack strategyPack = toStrategyPack(eventDO.getStrategyPack());
        List<String> strategyNameRefs = parseStrategyNames(eventDO.getStrategyPack());
        return new Event(eventDO.getEventId(), eventDO.getGmtCreate(), eventDO.getGmtModified(), eventDO.getVersion(),
            eventInfo, featureList, strategyPack, strategyNameRefs);
    }

    public static List<FeatureItemDTO> toFeatureItems(List<Feature> featureList) {
        if (featureList == null || featureList.isEmpty()) {
            return Collections.emptyList();
        }
        return featureList.stream().map(feature -> {
            FeatureItemDTO item = new FeatureItemDTO();
            item.setType(feature.getType().name());
            if (feature instanceof SimpleFeature) {
                SimpleFeature simpleFeature = (SimpleFeature)feature;
                item.setName(simpleFeature.getName());
                item.setDescription(simpleFeature.getDescription());
            }
            return item;
        }).collect(Collectors.toList());
    }

    public static StrategyPackDTO toStrategyPackDTO(Event event) {
        StrategyPackDTO dto = new StrategyPackDTO();
        if (event.getStrategyPack() != null) {
            StrategyPack pack = event.getStrategyPack();
            dto.setOwningEvent(pack.getOwningEvent());
            dto.setLastOperator(pack.getLastOperator());
            dto.setDescription(pack.getDescription());
            dto.setBusinessIdentity(pack.getBusinessIdentity().name());
            dto.setPreventionType(pack.getPreventionType().name());
            dto.setName(pack.getName());
        } else {
            dto.setOwningEvent(event.getEventInfo().getCode());
        }
        dto.setStrategyNames(event.getStrategyNameRefs());
        return dto;
    }

    private static List<Feature> toFeatures(String featuresJson) {
        if (featuresJson == null || featuresJson.isEmpty()) {
            return Lists.newArrayList();
        }
        List<FeatureItemDTO> items = JSON.parseObject(featuresJson, new TypeReference<List<FeatureItemDTO>>() {});
        return items.stream()
            .map(item -> new SimpleFeature(FeatureType.valueOf(item.getType()), item.getName(), item.getDescription()))
            .collect(Collectors.toList());
    }

    private static StrategyPack toStrategyPack(String strategyPackJson) {
        if (strategyPackJson == null || strategyPackJson.isEmpty()) {
            return null;
        }
        StrategyPackDTO dto = JSON.parseObject(strategyPackJson, StrategyPackDTO.class);
        if (dto.getBusinessIdentity() == null || dto.getPreventionType() == null || dto.getName() == null) {
            return null;
        }
        return new StrategyPack(dto.getOwningEvent(), dto.getLastOperator(), dto.getDescription(),
            Lists.newArrayList(), BusinessIdentity.valueOf(dto.getBusinessIdentity()),
            PreventionType.valueOf(dto.getPreventionType()), dto.getName());
    }

    public static List<String> parseStrategyNames(String strategyPackJson) {
        if (strategyPackJson == null || strategyPackJson.isEmpty()) {
            return Lists.newArrayList();
        }
        StrategyPackDTO dto = JSON.parseObject(strategyPackJson, StrategyPackDTO.class);
        return dto.getStrategyNames() != null ? dto.getStrategyNames() : Lists.newArrayList();
    }
}
