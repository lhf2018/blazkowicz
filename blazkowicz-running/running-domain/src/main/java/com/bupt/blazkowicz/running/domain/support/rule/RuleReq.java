package com.bupt.blazkowicz.running.domain.support.rule;

import java.util.List;

import com.bupt.blazkowicz.domain.share.entity.Condition;

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
    /** 脚本左参数 */
    private Object leftParam;
    /** 条件列表 */
    private List<Condition> conditionList;
    /** 条件逻辑 */
    private String logic;

    @Tolerate
    public RuleReq() {}
}
