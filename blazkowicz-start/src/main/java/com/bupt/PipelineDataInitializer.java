package com.bupt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.bupt.blazkowicz.domain.share.bridge.SequenceInfService;
import com.bupt.blazkowicz.domain.share.bridge.enums.SequenceType;
import com.bupt.blazkowicz.domain.share.dto.DisposalCustomDTO;
import com.bupt.blazkowicz.domain.share.entity.BusinessIdentity;
import com.bupt.blazkowicz.domain.share.entity.Condition;
import com.bupt.blazkowicz.domain.share.entity.ConditionScript;
import com.bupt.blazkowicz.domain.share.entity.ConditionScriptType;
import com.bupt.blazkowicz.domain.share.entity.LeftParamType;
import com.bupt.blazkowicz.domain.share.entity.PreventionType;
import com.bupt.blazkowicz.domain.share.entity.RequiredValue;
import com.bupt.blazkowicz.domain.share.entity.RequiredValueType;
import com.bupt.blazkowicz.domain.share.entity.Rule;
import com.bupt.blazkowicz.domain.share.factory.RuleFactory;
import com.bupt.blazkowicz.domain.share.repo.RuleRepo;
import com.bupt.blazkowicz.infrastructure.share.dal.dataobject.RuleDO;
import com.bupt.blazkowicz.infrastructure.share.dal.dataobject.StrategyDO;
import com.bupt.blazkowicz.infrastructure.share.dal.mapper.RuleMapper;
import com.bupt.blazkowicz.infrastructure.share.dal.mapper.StrategyMapper;
import com.bupt.blazkowicz.infrastructure.share.query.PreventionConfigInfService;
import com.bupt.blazkowicz.infrastructure.share.translator.DisposalConfigTranslator;
import com.bupt.blazkowicz.infrastructure.share.translator.RuleDOTranslator;
import com.google.common.collect.Lists;

/**
 * 初始化 TEST 场景下的规则与策略，打通配置→持久化→运行态读取→处置输出主链路。
 */
@Component
@Order(1)
public class PipelineDataInitializer implements CommandLineRunner {

    private static final String DEFAULT_STRATEGY_NAME = "default";
    private static final String SCRIPT = "class Main {\n" + "    static boolean run(String userId, String param) {\n"
        + "        if (userId == \"114515\") {\n" + "            return param == \"test\"\n" + "        } else {\n"
        + "            return false;\n" + "        }\n" + "    }\n" + "}";

    private static final String TEST_RULE_NAME = "测试";

    @Autowired
    private RuleRepo ruleRepo;
    @Autowired
    private RuleMapper ruleMapper;
    @Autowired
    private StrategyMapper strategyMapper;
    @Autowired
    private PreventionConfigInfService preventionConfigInfService;
    @Autowired
    private SequenceInfService sequenceInfService;

    @Override
    public void run(String... args) {
        StrategyDO existingStrategy = strategyMapper.get(BusinessIdentity.TEST.name(), PreventionType.TEST.name(),
            DEFAULT_STRATEGY_NAME);
        if (existingStrategy != null) {
            return;
        }

        Rule rule = ensureTestRule();

        StrategyDO strategyDO = new StrategyDO();
        strategyDO.setStrategyId(sequenceInfService.nextSequenceId(SequenceType.STRATEGY_ID));
        strategyDO.setBusinessIdentity(BusinessIdentity.TEST.name());
        strategyDO.setPreventionType(PreventionType.TEST.name());
        strategyDO.setName(DEFAULT_STRATEGY_NAME);
        strategyDO.setDescription("TEST 场景默认策略");
        strategyDO.setIntroducedRuleId(rule.getRuleId());

        DisposalCustomDTO disposalCustomDTO = new DisposalCustomDTO();
        disposalCustomDTO.setBusinessIdentity(BusinessIdentity.TEST.name());
        disposalCustomDTO.setPreventionType(PreventionType.TEST.name());
        disposalCustomDTO.setRuleId(rule.getRuleId());
        disposalCustomDTO.setDisposalType("AUDIT");
        disposalCustomDTO.setAction("MANUAL_REVIEW");
        disposalCustomDTO.setMessage("风险命中，需人工审核");
        strategyDO.setDisposalConfig(DisposalConfigTranslator.toJson(disposalCustomDTO));

        strategyMapper.insert(strategyDO);
        preventionConfigInfService.publishDisposalResp(disposalCustomDTO);
    }

    private Rule ensureTestRule() {
        RuleDO existingRule = ruleMapper.getByName(TEST_RULE_NAME);
        if (existingRule != null) {
            return RuleDOTranslator.toDomain(existingRule);
        }
        Rule rule = createTestRule();
        ruleRepo.save(rule);
        return rule;
    }

    private Rule createTestRule() {
        ConditionScript conditionScript = new ConditionScript(SCRIPT, ConditionScriptType.GROOVY);
        List<RequiredValue> requiredValueList =
            Lists.newArrayList(new RequiredValue(RequiredValueType.CONSTANT, "param", "test"));
        Condition condition1 = new Condition(1, conditionScript, requiredValueList, LeftParamType.ACCOUNT);
        Condition condition2 = new Condition(2, conditionScript, requiredValueList, LeftParamType.ACCOUNT);
        Condition condition3 = new Condition(3, conditionScript, requiredValueList, LeftParamType.ACCOUNT);
        return RuleFactory.create(TEST_RULE_NAME, Lists.newArrayList(condition1, condition2, condition3), "1&&2&&3");
    }
}
