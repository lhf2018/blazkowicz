package com.bupt.blazkowicz.infrastructure.share.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.bupt.blazkowicz.domain.share.dto.DisposalCustomDTO;
import com.bupt.blazkowicz.domain.share.resp.DisposalResp;
import com.bupt.blazkowicz.infrastructure.share.dal.mapper.StrategyMapper;
import com.bupt.blazkowicz.infrastructure.share.inf.CacheInfService;
import com.bupt.blazkowicz.infrastructure.share.inf.CacheKeyUtil;
import com.bupt.blazkowicz.infrastructure.share.translator.DisposalConfigTranslator;

/**
 * @author lhf2018
 */
@Component
public class PreventionConfigInfService {
    @Autowired
    private CacheInfService cacheInfService;
    @Autowired
    private StrategyMapper strategyMapper;

    public DisposalResp getDisposalResp(String businessIdentity, String preventionType, Long ruleId) {
        String cacheKey = CacheKeyUtil.disposalKey(businessIdentity, preventionType, ruleId);
        String cached = cacheInfService.get(cacheKey);
        if (cached != null) {
            return DisposalConfigTranslator.toDisposalResp(cached);
        }
        String disposalConfig = strategyMapper.getDisposalConfig(businessIdentity, preventionType, ruleId);
        DisposalResp disposalResp = DisposalConfigTranslator.toDisposalResp(disposalConfig);
        if (disposalResp != null) {
            cacheInfService.put(cacheKey, disposalConfig, CacheKeyUtil.defaultExpireSeconds());
        }
        return disposalResp;
    }

    public void publishDisposalResp(DisposalCustomDTO disposalCustomDTO) {
        if (disposalCustomDTO == null || disposalCustomDTO.getRuleId() == null) {
            return;
        }
        String json = DisposalConfigTranslator.toJson(disposalCustomDTO);
        cacheInfService.put(
            CacheKeyUtil.disposalKey(disposalCustomDTO.getBusinessIdentity(), disposalCustomDTO.getPreventionType(),
                disposalCustomDTO.getRuleId()),
            json, CacheKeyUtil.defaultExpireSeconds());
    }
}
