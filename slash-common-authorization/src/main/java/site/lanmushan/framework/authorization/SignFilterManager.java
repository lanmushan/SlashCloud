package site.lanmushan.framework.authorization;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import site.lanmushan.framework.constant.HTTPCode;
import site.lanmushan.framework.exception.OperateException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
@Slf4j
public class SignFilterManager {
    public SignFilterManager(List<String> excludePatterns) {
        this.excludePatterns = excludePatterns;
    }

    private List<String> excludePatterns = new ArrayList();
    PathMatcher pathMatcher = new AntPathMatcher();

    public Boolean allow(String targetUri, String token) {
        CurrentUser currentUser = CurrentUserUtil.getCurrentUser(token);
        for (int i = 0; i < excludePatterns.size(); i++) {
            Boolean result = pathMatcher.match(excludePatterns.get(i), targetUri);
            if (result) {
                return true;
            }
        }
        //需要用户
        if (currentUser == null) {
            throw new OperateException("未登录", HTTPCode.D600);
        }
        CurrentUserUtil.isLoginOverdue(currentUser,token);
        //验证配置式权限
        return CurrentUserUtil.currentUserHasUriPermissions(targetUri, currentUser);
    }
}
