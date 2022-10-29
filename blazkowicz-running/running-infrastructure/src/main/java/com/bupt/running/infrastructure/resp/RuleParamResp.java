package com.bupt.running.infrastructure.resp;

import lombok.Data;

/**
 * @author lhf2018
 * @date 2022/10/29 15:49
 */
@Data
public class RuleParamResp {
    /** 脚本参数 */
    private Object[] params;
    /** 脚本名称 */
    private String name;
}
