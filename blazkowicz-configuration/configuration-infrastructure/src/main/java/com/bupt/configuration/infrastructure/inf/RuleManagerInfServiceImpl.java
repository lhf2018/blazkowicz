package com.bupt.configuration.infrastructure.inf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bupt.configuration.domain.inf.RuleManagerInfService;
import com.bupt.domain.share.entity.ConfigurationRule;
import com.bupt.infrastructure.share.inf.NosqlInfService;

/**
 * @author lhf2018
 * @date 2022/10/6 18:50
 */
@Component
public class RuleManagerInfServiceImpl implements RuleManagerInfService {
    @Autowired
    private NosqlInfService nosqlInfService;

    @Override
    public void createRule(ConfigurationRule configurationRule) {
        // todo 写道一个公用的地方，比如nosql系统
    }

}
