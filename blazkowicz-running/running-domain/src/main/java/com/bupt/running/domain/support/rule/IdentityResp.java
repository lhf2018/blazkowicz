package com.bupt.running.domain.support.rule;

import com.bupt.domain.share.entity.Status;

import lombok.Data;

/**
 * @author lhf2018
 * @date 2022/10/29 16:38
 */
@Data
public class IdentityResp {
    private Status status;
    private String ruleName;
}
