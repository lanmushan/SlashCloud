package com.lanmushan.framework.constant;

/**
 * 1xx 消息，一般是告诉客户端，请求已经收到了，正在处理，别急...
 * <p>
 * 2xx 处理成功，一般表示：请求收悉、我明白你要的、请求已受理、已经处理完成等信息.
 * <p>
 * 3xx 重定向到其它地方。它让客户端再发起一个请求以完成整个处理。
 * <p>
 * 4xx 处理发生错误，责任在客户端，如客户端的请求一个不存在的资源，客户端未被授权，禁止访问等。
 * <p>
 * 5xx 处理发生错误，责任在服务端，如服务端抛出异常，路由出错，HTTP版本不支持等。
 * 状态编号定义
 *
 * @author dy
 */
public enum HTTPCode {
    //操作成功，在查询时有数据进行返回
    OK(200, "操作成功"),
    //操作成功的情况，但是没有数据的情况
    OK204(204, "暂无相关数据"),
    Fail(205, "操作失败"),
    //请求地址无效
    NotFound(404, "请求地址无效"),
    Unauthorized(401, "无访问权限"),
    PramError(400, "请求参数错误"),
    InnerError(500, "服务器内部错误"),
    // 重定向
    Redirect(300, "请求重定向");

    public String msg;
    public Integer code;

    HTTPCode() {
    }

    HTTPCode(int code, String msg) {
        this.msg = msg;
        this.code = code;
    }

    public static void main(String[] args) {
        System.out.println(OK.code);
    }
}
