package com.bupt.blazkowicz.common.exception;

import lombok.Getter;
import lombok.ToString;

/**
 * @author lhf2018
 * @date 2022/10/15 17:01
 */
@Getter
@ToString
public class BlazkowiczException extends RuntimeException {

    private int code;
    private String msg;

    public BlazkowiczException(int code) {
        super();
        this.code = code;
    }

    public BlazkowiczException(int code, String msg) {
        super("code=" + code + ", msg=" + msg);
        this.code = code;
        this.msg = msg;
    }
}
