package com.bupt.domain.share.entity;

import lombok.Getter;

/**
 * 最终生产管理的规则 //todo 值对象
 * 
 * @author lhf2018
 * @date 2022/10/15 16:18
 */
@Getter
public class Rule {
    /** 脚本内容 */
    private String script;
    /** 脚本名称 */
    private String name;

    public Rule(String name, String script) {
        this.script = script;
        this.name = name;
    }
}
