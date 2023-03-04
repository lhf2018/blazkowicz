package com.bupt.blazkowicz.running.domain.entity;

import com.bupt.blazkowicz.domain.share.anno.Entity;
import com.bupt.blazkowicz.domain.share.entity.Rule;

import lombok.Getter;

/**
 * 运行态规则，无法修改rule
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
