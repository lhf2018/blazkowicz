package com.bupt.configuration.domain.entity;

import com.bupt.configuration.domain.inf.RuleManagerInfService;
import com.bupt.configuration.domain.translator.ToRuleTranslator;
import com.bupt.domain.share.bridge.DomainShareBridge;

import lombok.Getter;

/**
 * 条件
 *
 * @author lhf2018
 * @date 2022/10/6 17:16
 */
@Getter
public class Condition {

    /** 脚本内容 */
    private String script;

    /** 脚本名称 */
    private String name;

    /** id */
    private final Long scriptId;

    public Condition(String script, String name, Long scriptId) {
        this.script = script;
        this.name = name;
        this.scriptId = scriptId;
    }

    public void updateName(String newName) {
        name = newName;
    }

    public void updateScript(String newScript) {
        script = newScript;
        DomainShareBridge.getAdapter(RuleManagerInfService.class).createRule(ToRuleTranslator.toRule(this));
    }
}
