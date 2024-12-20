package com.bupt.blazkowicz.running.domain.support.rule;

import com.bupt.blazkowicz.domain.share.entity.Status;

import lombok.Data;

/**
 * @author lhf2018
 * @date 2022/10/29 16:38
 */
@Data
public class IdentityResultResp {
    private Status status;
    private String ruleName;
}
