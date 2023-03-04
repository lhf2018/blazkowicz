package com.bupt.blazkowicz.domain.share.entity;

import lombok.Getter;

/**
 * @author lhf2018
 * @date 2022/11/19 20:29
 */
@Getter
public class ConditionScript {
    private String content;
    private ConditionScriptType type;

    public ConditionScript(String content, ConditionScriptType type) {
        this.content = content;
        this.type = type;
    }
}
