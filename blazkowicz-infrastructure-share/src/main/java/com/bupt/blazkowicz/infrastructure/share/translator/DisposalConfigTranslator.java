package com.bupt.blazkowicz.infrastructure.share.translator;

import com.alibaba.fastjson.JSON;
import com.bupt.blazkowicz.domain.share.dto.DisposalCustomDTO;
import com.bupt.blazkowicz.domain.share.resp.DisposalResp;

/**
 * @author lhf2018
 */
public final class DisposalConfigTranslator {

    private DisposalConfigTranslator() {}

    public static DisposalResp toDisposalResp(String disposalConfig) {
        if (disposalConfig == null || disposalConfig.isEmpty()) {
            return null;
        }
        DisposalCustomDTO dto = JSON.parseObject(disposalConfig, DisposalCustomDTO.class);
        return toDisposalResp(dto);
    }

    public static DisposalResp toDisposalResp(DisposalCustomDTO dto) {
        if (dto == null) {
            return null;
        }
        DisposalResp disposalResp = new DisposalResp();
        disposalResp.setDisposalType(dto.getDisposalType());
        disposalResp.setAction(dto.getAction());
        disposalResp.setMessage(dto.getMessage());
        return disposalResp;
    }

    public static String toJson(DisposalCustomDTO dto) {
        return JSON.toJSONString(dto);
    }
}
