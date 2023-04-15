package com.bupt.blazkowicz.configuration.infrastructure.dal.dataobject;

import java.util.Date;

import lombok.Data;

/**
 * @author lhf2018
 * @date 2023/4/15 22:57
 */
@Data
public class StrategyDO {
    private Long strategyId;
    private Date gmtCreate;
    private Date gmtModified;
    private Integer version;
    /** 业务身份 */
    private String businessIdentity;
    /** 防控场景 */
    private String preventionType;
    /** 策略名称 */
    private String name;
    /** 描述 */
    private String description;
    /** 识别规则 */
    private Long introducedRuleId;
    /** 处置 */
    private String disposalList;
}
