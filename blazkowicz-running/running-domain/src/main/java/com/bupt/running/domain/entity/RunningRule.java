package com.bupt.running.domain.entity;

import lombok.Getter;

/**
 * 运行态规则
 * 
 * @author lhf2018
 * @date 2022/11/13 23:13
 */
@Getter
public class RunningRule {
    private String id;
    /** 脚本 */
    private String script;
    /** 参数 */
    private Object[] rightParams;

    public RunningRule(String id, String script, Object[] rightParams) {
        this.id = id;
        this.script = script;
        this.rightParams = rightParams;
    }
}
