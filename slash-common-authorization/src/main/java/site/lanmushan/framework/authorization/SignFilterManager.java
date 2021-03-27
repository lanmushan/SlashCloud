package site.lanmushan.framework.authorization;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import site.lanmushan.framework.constant.GlobalInstructionConstant;
import site.lanmushan.framework.redis.GlobalInstructionEntity;
import site.lanmushan.framework.redis.subscribe.GlobalInstructionHandler;
import site.lanmushan.framework.redis.subscribe.GlobalInstructionSubscription;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Administrator
 */
@Slf4j
public class SignFilterManager implements GlobalInstructionHandler {
    @Autowired
    GlobalInstructionSubscription globalInstructionSubscription;
    private final CopyOnWriteArrayList<Authority> dynamicAuthorityList = new CopyOnWriteArrayList<>();
    final PathMatcher pathMatcher = new AntPathMatcher();

    public SignFilterManager(List<Authority> authorities) {
        dynamicAuthorityList.addAll(authorities);
    }

    public SignFilterManager() {
    }

    @PostConstruct
    private void init() {
        globalInstructionSubscription.registerGlobalInstructionHandler(GlobalInstructionConstant.UPDATE_API_DATA, null, this);

    }

    public Boolean allow(String targetUri, String token) {
        CurrentUser currentUser = CurrentUserUtil.getCurrentUser(token);
        //判断动态的
        for (Authority authority : dynamicAuthorityList) {
            Boolean result = pathMatcher.match(authority.getUrl(), targetUri);
            if (!result) {
                continue;
            }
            if (authority.getRoleCodes().contains(CurrentUserUtil.ANON_CODE)) {
                return true;
            } else if (authority.getRoleCodes().containsAll(currentUser.getRoleCodes())) {
                return true;
            }
        }
        //先判断是不是admin
        if (currentUser != null && currentUser.isAdmin()) {
            return true;
        }
        if (CurrentUserUtil.currentUserHasUriPermissions(targetUri, currentUser)) {
            return true;
        }
        //判断是不是指定url，不然所有的都判断性能太低
        if (CurrentUserUtil.isLoginOverdue(currentUser, token)) {
            return true;
        }
        return false;
    }

    public void registerUrl(String url, String roleCodes[], Integer order) {
        Authority authority = new Authority();
        authority.setOrder(order);
        authority.setRoleCodes(Arrays.asList(roleCodes));
        authority.setUrl(url);
        this.registerUrl(authority);
    }

    public void registerUrl(Authority authority) {
        this.dynamicAuthorityList.add(authority);
        this.dynamicAuthorityList.stream().sorted((a, b) -> {
            return a.getOrder() - b.getOrder();
        });
    }

    public void removeUrl(String url) {
        this.dynamicAuthorityList.remove(url);
    }

    @Override
    public void doInstructionExecute(GlobalInstructionEntity instruction) {
        JSONArray jsonArray = (JSONArray) instruction.getData();
        List<Authority> authorityList = jsonArray.toJavaList(Authority.class);
        for (Authority authority : authorityList) {
            if (pathMatcher.isPattern(authority.getUrl())) {
                this.registerUrl(authority);
            }
            log.info("注册新的动态API权限{}={}", authority.getUrl(), JSON.toJSONString(authority.getRoleCodes()));
        }

    }
}

