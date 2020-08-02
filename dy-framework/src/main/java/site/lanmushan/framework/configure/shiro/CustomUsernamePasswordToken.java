package site.lanmushan.framework.configure.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 登录使用的账号密码传输
 */
public class CustomUsernamePasswordToken extends UsernamePasswordToken {
    private String dpassword;
    private String salt;
    public CustomUsernamePasswordToken(String username, String password) {
        super(username, (char[])(password != null ? password.toCharArray() : null), false, (String)null);
    }
    public String getDpassword() {
        return dpassword;
    }

    public void setDpassword(String dpassword) {
        this.dpassword = dpassword;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
