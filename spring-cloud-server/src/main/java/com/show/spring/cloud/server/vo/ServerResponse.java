package com.show.spring.cloud.server.vo;

import com.show.spring.cloud.server.vo.enums.ResponseCode;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 前端交互对象
 * @author show
 * @date 15:40 2018/9/18
 */
@ToString
@Getter
public class ServerResponse<T> implements Serializable {
    private String status;
    private String message;
    private T data;

    /** 私有构造器 */
    private ServerResponse(String status) {

        this.status = status;
    }

    private ServerResponse(String status, T data) {

        this.status = status;
        this.data = data;
    }

    private ServerResponse(String status, String message, T data) {

        this.status = status;
        this.message = message;
        this.data = data;
    }

    private ServerResponse(String status, String message) {

        this.status = status;
        this.message = message;
    }

    /** 成功的构造器 */
    public static <T> ServerResponse<T> createBySuccess() {

        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode());
    }

    public static <T> ServerResponse<T> createBySuccessMessage(String message) {

        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), message);
    }

    public static <T> ServerResponse<T> createBySuccess(T data) {

        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), data);
    }

    public static <T> ServerResponse<T> createBySuccess(String message, T data) {

        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), message, data);
    }

    public static <T> ServerResponse<T> createBySuccessDiyCode(String code, T data) {

        return new ServerResponse<T>(code, data);
    }

    /** 失败的构造器 */
    public static <T> ServerResponse<T> createByError() {

        return new ServerResponse<T>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getDesc());
    }

    public static <T> ServerResponse<T> createByErrorMessage(String errorMessage) {

        return new ServerResponse<T>(ResponseCode.ERROR.getCode(), errorMessage);
    }

    public static <T> ServerResponse<T> createByErrorCodeMessage(String errorCode, String errorMessage) {

        return new ServerResponse<T>(errorCode, errorMessage);
    }

    /** 默认断路器构造器   */
    public static <T> ServerResponse<T> createByDefaultFallback() {

        return new ServerResponse<T>(ResponseCode.DEFAULT_FALLBACK.getCode(), "默认提示：太拥挤了，请稍后再试");
    }

    /** 自定义错误信息断路器构造器   */
    public static <T> ServerResponse<T> createByFallbackMessage(String fallbackMessage) {

        return new ServerResponse<T>(ResponseCode.DEFAULT_FALLBACK.getCode(), fallbackMessage);
    }
}
