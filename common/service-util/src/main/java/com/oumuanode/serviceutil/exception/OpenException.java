package com.oumuanode.serviceutil.exception;

import com.oumuanode.commonutil.result.ResultCodeEnum;
import lombok.Data;

@Data
public class OpenException extends RuntimeException{  private Integer code;//状态码
    private String msg;//描述信息

    public OpenException(Integer code,String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    /**
     * 接收枚举类型对象
     * @param resultCodeEnum
     */
    public OpenException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
        this.msg = resultCodeEnum.getMessage();
    }

    @Override
    public String toString() {
        return "OpenException{" +
                "code=" + code +
                ", message=" + this.getMessage() +
                '}';
    }
}