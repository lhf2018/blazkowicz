package com.bupt.blazkowicz.domain.share.entity;

import lombok.Getter;

/**
 * 配置态规则 //todo 值对象
 * 
 * @author lhf2018
 * @date 2022/10/15 16:18
 */
@Getter
public class ConfigurationRule {
    private Long id;
    /** 脚本内容 */
    private String script;
    /** 脚本名称 */
    private String name;

    public ConfigurationRule(Long id, String script, String name) {
        this.id = id;
        this.script = script;
        this.name = name;
    }
}
