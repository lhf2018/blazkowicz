package com.bupt.blazkowicz.common.exception;

/**
 * @author lhf2018
 * @date 2022/10/15 16:58
 */
public interface Code {
    /** 参数无效 */
    int PARAMETER_INVALID = 10301;
    /** 策略实体异常 */
    int DOMAIN_STRATEGY_ERROR = 10401;

    /** 通用异常 */
    int UNEXPECTED_ERROR = 30000;
}
