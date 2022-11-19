package com.bupt.infrastructure.share.resp;

import lombok.Data;

/**
 * @author lhf2018
 * @date 2022/10/29 15:49
 */
@Data
public class RunningRuleResp {
    /** 规则id */
    private String ruleId;
    /** 规则脚本内容 */
    private String script;
    /** 规则脚本参数 */
    private Object[] params;
    /** 规则名称 */
    private String name;
}
