package com.bupt.configuration.domain.entity;

import lombok.Getter;

/**
 * @author lhf2018
 * @date 2022/10/6 17:19
 */
@Getter
public abstract class Disposal {
    private final DisposalType type;

    protected Disposal(DisposalType type) {
        this.type = type;
    }
}
