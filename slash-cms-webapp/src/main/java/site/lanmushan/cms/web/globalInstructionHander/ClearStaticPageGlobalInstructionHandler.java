package site.lanmushan.cms.web.globalInstructionHander;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import site.lanmushan.cms.web.view.FreeMarkerStaticView;
import site.lanmushan.framework.constant.GlobalInstructionConstant;
import site.lanmushan.framework.redis.GlobalInstructionEntity;
import site.lanmushan.framework.redis.subscribe.GlobalInstructionHandler;
import site.lanmushan.framework.redis.subscribe.GlobalInstructionSubscription;

import javax.annotation.PostConstruct;
import java.util.Set;

/**
 * @author Administrator
 */
@Component
@Slf4j
public class ClearStaticPageGlobalInstructionHandler implements GlobalInstructionHandler {
    @Autowired
    GlobalInstructionSubscription globalInstructionSubscription;
    private static String cmd = GlobalInstructionConstant.CLEAR_STATICS_PAGE;
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Override
    public void doInstructionExecute(GlobalInstructionEntity instruction) {
        Set<String> valueSet = redisTemplate.keys(FreeMarkerStaticView.STATIC_URL_PREFIX + "*");
        redisTemplate.delete(valueSet);
    }

    @PostConstruct
    public void init() {
        globalInstructionSubscription.registerGlobalInstructionHandler(cmd, null, this);
    }
}
