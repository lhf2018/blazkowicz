package com.bupt.blazkowicz.configuration.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bupt.blazkowicz.client.share.dto.result.ResultDTO;
import com.bupt.blazkowicz.configuration.domain.entity.AccessMethod;
import com.bupt.blazkowicz.configuration.domain.entity.DisposalType;
import com.bupt.blazkowicz.configuration.domain.entity.FeatureType;
import com.bupt.blazkowicz.domain.share.entity.BusinessIdentity;
import com.bupt.blazkowicz.domain.share.entity.ConditionScriptType;
import com.bupt.blazkowicz.domain.share.entity.LeftParamType;
import com.bupt.blazkowicz.domain.share.entity.PreventionType;
import com.bupt.blazkowicz.domain.share.entity.RequiredValueType;
import com.bupt.blazkowicz.domain.share.entity.Status;

/**
 * @author lhf2018
 */
@RestController
@RequestMapping("/config/meta")
public class MetaConfigurationController {

    @GetMapping("/enums")
    public ResultDTO<Map<String, List<String>>> enums() {
        Map<String, List<String>> enums = new HashMap<>();
        enums.put("businessIdentity", enumNames(BusinessIdentity.values()));
        enums.put("preventionType", enumNames(PreventionType.values()));
        enums.put("disposalType", enumNames(DisposalType.values()));
        enums.put("accessMethod", enumNames(AccessMethod.values()));
        enums.put("featureType", enumNames(FeatureType.values()));
        enums.put("leftParamType", enumNames(LeftParamType.values()));
        enums.put("requiredValueType", enumNames(RequiredValueType.values()));
        enums.put("conditionScriptType", enumNames(ConditionScriptType.values()));
        enums.put("status", enumNames(Status.values()));
        return ResultDTO.buildSuccessResult(enums);
    }

    private <E extends Enum<E>> List<String> enumNames(E[] values) {
        return Arrays.stream(values).map(Enum::name).collect(Collectors.toList());
    }
}
