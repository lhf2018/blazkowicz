package com.bupt.running.domain.entity;

import com.bupt.domain.share.anno.Entity;
import com.bupt.domain.share.entity.Rule;

import lombok.Getter;

/**
 * 运行态规则
 * 
 * @author lhf2018
 * @date 2022/11/13 23:13
 */
@Getter
@Entity
public class RunningStrategy {
    /** 规则 */
    private Rule rule;

    public RunningStrategy(Rule rule) {
        this.rule = rule;
    }
}
