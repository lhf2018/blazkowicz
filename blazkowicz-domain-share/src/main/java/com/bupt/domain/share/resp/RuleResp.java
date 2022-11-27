package com.bupt.domain.share.resp;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

/**
 * @author lhf2018
 * @date 2022/10/29 16:35
 */
@Data
@Builder
public class RuleResp {
    private String id;
    private String script;
    private Object[] params;

    @Tolerate
    public RuleResp() {}
}
