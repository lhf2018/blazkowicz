package com.bupt.running.domain.support.rule;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

/**
 * @author lhf2018
 * @date 2022/11/5 15:31
 */
@Data
@Builder
public class RuleReq {
    /** 脚本内容 */
    private String script;
    /** 脚本id */
    private String id;
    /** 脚本参数 */
    private Object[] params;

    @Tolerate
    public RuleReq() {}
}