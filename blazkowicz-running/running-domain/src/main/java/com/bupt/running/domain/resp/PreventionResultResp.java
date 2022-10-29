package com.bupt.running.domain.resp;

import lombok.Builder;
import lombok.Data;

/**
 * @author lhf2018
 * @date 2022/10/29 15:36
 */
@Data
@Builder
public class PreventionResultResp {
    private String status;
    private String name;
}
