package com.bupt.common.exception;

/**
 * @author lhf2018
 * @date 2022/10/15 17:01
 */
public class BlazkowiczException extends RuntimeException {

    private int code;

    public BlazkowiczException(int code) {
        super();
        this.code = code;
    }
}
