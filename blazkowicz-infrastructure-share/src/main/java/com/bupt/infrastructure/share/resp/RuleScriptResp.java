package com.bupt.infrastructure.share.resp;

import lombok.Data;

/**
 * @author lhf2018
 * @date 2022/10/29 15:49
 */
@Data
public class RuleScriptResp {
    /** 脚本内容 */
    private String script;
    /** 脚本名称 */
    private String name;
}
