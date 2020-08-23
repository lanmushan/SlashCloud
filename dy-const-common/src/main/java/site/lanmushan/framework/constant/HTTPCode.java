package site.lanmushan.framework.constant;

/**
 *  状态编号定义
 *  E 200+ 业务层面
 *  K 300+ 进一步操作 需要客服端进一步进行处理，如重定向等
 *  C 400+ 客户端错误   输入型错误
 *  S 500+ 服务端错误   数据库操作失败，远程调用失败
 *  D 600+ 安全型错误 无权限，白名单，黑名单等情况
 *
 *
 * @author dy
 */
public enum HTTPCode {

    /**
     * 业务业务处理层面
     */
    OK(200, "操作成功"),
    Fail(205, "操作失败"),
    E200(200, "操作成功"),
    E204(204, "暂无相关数据"),
    E205(205, "操作失败"),
    /**
     * 需要后续操作的错误
     */
    K300(300, "请求重定向"),
    /**
     * 客户端层面错误
     */
    C400(400, "请求参数"),
    C404(404, "请求地址错误"),
    /**
     * 服务端型层面错误
     */
    S500(500, "服务器内部错误"),
    /**
     * 安全操作层面错误
     */
    D600(600, "未登录"),
    D601(601, "无访问权限"),
    D610(610, "白名单错误"),
    D620(620, "黑名单错误");

    public int code;
    public String msg;
    HTTPCode() {
    }

    HTTPCode(int code, String msg) {
        this.msg = msg;
        this.code = code;
    }

    public static void main(String[] args) {

    }
}
