package com.bupt.blazkowicz.infrastructure.share.query;

import org.springframework.stereotype.Component;

import com.bupt.blazkowicz.domain.share.dto.DisposalCustomDTO;
import com.bupt.blazkowicz.domain.share.resp.DisposalResp;

/**
 * 公共类，用于更新和查询公共规则信息
 * 
 * @author lhf2018
 * @date 2023/4/7 23:03
 */
@Component
public class PreventionConfigInfService {
    public DisposalResp getDisposalResp(String businessIdentity, String preventionType, Long ruleId) {
        // todo
        return null;
    }

    public void publishDisposalResp(String businessIdentity, String preventionType, Long ruleId,
        DisposalCustomDTO disposalCustomDTO) {
        // todo
    }
}
