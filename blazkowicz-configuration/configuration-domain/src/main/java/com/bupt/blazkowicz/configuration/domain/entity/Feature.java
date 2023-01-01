package com.bupt.blazkowicz.configuration.domain.entity;

import lombok.Getter;

/**
 * @author lhf2018
 * @date 2022/10/6 18:20
 */
@Getter
public abstract class Feature {
    private final FeatureType type;

    protected Feature(FeatureType type) {
        this.type = type;
    }
}
