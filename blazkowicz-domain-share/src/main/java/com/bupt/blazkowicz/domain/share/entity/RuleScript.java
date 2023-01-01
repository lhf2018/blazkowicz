package com.bupt.blazkowicz.domain.share.entity;

import lombok.Getter;

/**
 * @author lhf2018
 * @date 2022/11/19 20:29
 */
@Getter
public class RuleScript {
    private String content;
    private RuleScriptType type;

    public RuleScript(String content, RuleScriptType type) {
        this.content = content;
        this.type = type;
    }
}
