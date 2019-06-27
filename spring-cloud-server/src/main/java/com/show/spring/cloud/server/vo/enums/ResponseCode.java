package com.show.spring.cloud.server.vo.enums;

import lombok.Getter;

/**
 * 状态码枚举类
 *
 * @author show
 * @date 15:40 2018/9/18
 * @param
 * @return
 */
public enum ResponseCode {
    /** 成功 */
    SUCCESS("00", "SUCCESS"),
    /** 失败 */
    ERROR("-01", "ERROR"),
    /** HyStrix默认返回 */
    DEFAULT_FALLBACK("99", "DEFAULT_FALLBACK");
    @Getter
    private final String code;
    @Getter
    private final String desc;

    ResponseCode(String code, String desc) {

        this.code = code;
        this.desc = desc;
    }

}
