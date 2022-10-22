package com.bupt.running.infrastructure.inf;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bupt.domain.share.entity.Rule;
import com.bupt.domain.share.entity.Status;
import com.bupt.running.domain.inf.RuleEngineInfService;

/**
 * @author lhf2018
 * @date 2022/10/22 18:28
 */
@Component
public class RuleEngineInfServiceImpl implements RuleEngineInfService {
    @Autowired
    private GroovyInfService groovyInfService;

    @Override
    public List<Rule> getRuleList(String businessIdentity, String preventionType, String name) {
        // todo
        return null;
    }

    @Override
    public Status runRule(String script, String method, String args) {
        // todo
        return null;
    }
}
