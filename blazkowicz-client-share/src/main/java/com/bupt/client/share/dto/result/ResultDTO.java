package com.bupt.client.share.dto.result;

import java.io.Serializable;

import lombok.Builder;
import lombok.Getter;

/**
 * @author lhf2018
 * @date 2022/11/13 2:46
 */
@Getter
@Builder
public class ResultDTO<T> implements Serializable {
    private static final int SUCCESS = 0;
    private Boolean success;
    private Integer code;
    private String msg;
    private T data;

    public static <T> ResultDTO<T> buildSuccessResult(T data) {
        return ResultDTO.<T>builder().success(true).code(SUCCESS).data(data).build();
    }

    public static <T> ResultDTO<Void> buildSuccessResult() {
        return ResultDTO.<Void>builder().success(true).code(SUCCESS).build();
    }

    public static <T> ResultDTO<Void> buildFailResult(Integer code, String msg) {
        return ResultDTO.<Void>builder().code(code).success(false).msg(msg).build();
    }

    public static <T> ResultDTO<Void> buildFailResult(Integer code) {
        return ResultDTO.<Void>builder().code(code).success(false).build();
    }
}
