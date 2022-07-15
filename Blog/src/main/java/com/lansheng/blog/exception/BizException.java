package com.lansheng.blog.exception;

import com.lansheng.blog.enums.StatusCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import static com.lansheng.blog.enums.StatusCodeEnum.FAIL;
/**
 * @description:
 * @author: 兰生
 * @date: 2022/07/14 23:11
 * @version: 1.0
 */
@Getter
@AllArgsConstructor
public class BizException extends RuntimeException {

    /**
     * 错误码
     */
    private Integer code = FAIL.getCode();

    /**
     * 错误信息
     */
    private final String message;

    public BizException(String message) {
        this.message = message;
    }

    public BizException(StatusCodeEnum statusCodeEnum) {
        this.code = statusCodeEnum.getCode();
        this.message = statusCodeEnum.getDesc();
    }


}
