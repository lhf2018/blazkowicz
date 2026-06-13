package com.bupt.blazkowicz.configuration.domain.entity;

import com.bupt.blazkowicz.domain.share.anno.Specification;
import lombok.Getter;

/**
 * @author lhf2018
 */
@Getter
@Specification
public class SimpleFeature extends Feature {
    private final String name;
    private final String description;

    public SimpleFeature(FeatureType type, String name, String description) {
        super(type);
        this.name = name;
        this.description = description;
    }
}
