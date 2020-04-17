package com.lanmushan.framework.dto;


import javax.validation.constraints.NotNull;

/**
 * 异步校验请求接收类
 * @author dy
 */
public class AsyncValidate {
    @NotNull(message = "字段名不能为空")
    private String field;
    @NotNull(message = "值不能为空")
    private String value;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
