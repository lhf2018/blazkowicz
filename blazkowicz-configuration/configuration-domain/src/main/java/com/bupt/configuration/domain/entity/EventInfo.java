package com.bupt.configuration.domain.entity;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * @author lhf2018
 * @date 2022/10/6 18:17
 */
@Getter
public class EventInfo {
    private final String name;
    private final String code;
    /**
     * 最后操作人
     */
    private String lastOperator;
    /**
     * 事件描述
     */
    private String desc;
    /**
     * 接入方式
     */
    private final AccessMethod accessMethod;

    public EventInfo(String name, String code, String lastOperator, String desc, AccessMethod accessMethod) {
        this.name = name;
        this.code = code;
        this.lastOperator = lastOperator;
        this.desc = desc;
        this.accessMethod = accessMethod;
    }

    public void update(EventInfo eventInfo) {
        if (StringUtils.isNotEmpty(eventInfo.getDesc())) {
            this.desc = eventInfo.getDesc();
        }
        if (StringUtils.isNotEmpty(eventInfo.getLastOperator())) {
            this.lastOperator = eventInfo.getLastOperator();
        }
    }
}
