package com.bupt.running.domain.support.rule;

import lombok.Data;

/**
 * @author lhf2018
 * @date 2022/10/29 16:35
 */
@Data
public class RunningFullRuleResp {
    private String name;
    private String script;
    private Object[] params;
}
