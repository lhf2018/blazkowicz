package com.bupt.infrastructure.share.query;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bupt.domain.share.entity.BusinessIdentity;
import com.bupt.domain.share.entity.PreventionType;
import com.bupt.domain.share.resp.RuleResp;
import com.bupt.infrastructure.share.inf.NosqlInfService;

/**
 * 规则查询
 * 
 * @author lhf2018
 * @date 2022/10/29 15:29
 */
@Component
public class RuleQueryService {
    @Autowired
    private NosqlInfService nosqlInfService;

    /**
     * 返回策略列表
     * 
     * @param businessIdentity
     * @param preventionType
     * @return
     */
    public List<RuleResp> getRuleRespList(BusinessIdentity businessIdentity, PreventionType preventionType) {
        // todo
        return null;
    }
}
