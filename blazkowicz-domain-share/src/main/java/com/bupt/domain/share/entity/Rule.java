package com.bupt.domain.share.entity;

import lombok.Getter;

/**
 * @author lhf2018
 * @date 2022/10/15 16:18
 */
@Getter
public class Rule {
    private final Long ruleId;
    /** 脚本内容 */
    private String script;
    /** 脚本名称 */
    private String name;

    public Rule(Long ruleId, String script, String name) {
        this.ruleId = ruleId;
        this.script = script;
        this.name = name;
    }
}
