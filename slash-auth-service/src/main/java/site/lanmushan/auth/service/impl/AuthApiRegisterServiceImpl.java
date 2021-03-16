package site.lanmushan.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import site.lanmushan.auth.api.vo.VoUrlRoles;
import site.lanmushan.auth.mapper.AuthTbResourceMapper;
import site.lanmushan.auth.api.service.AuthApiRegisterService;
import site.lanmushan.framework.authorization.CurrentUserUtil;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 将所有的api存储到redis中
 * @author Administrator
 */
@Service
public class AuthApiRegisterServiceImpl implements AuthApiRegisterService {
    @Autowired
    AuthTbResourceMapper authTbResourceMapper;
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Override
    public void refreshAllApiJurisdiction() {
        List<VoUrlRoles> urlRolesList = authTbResourceMapper.selectApiRelationRoles(null);
        if (urlRolesList == null || urlRolesList.isEmpty()) {
            return;
        }
        Map<String, String> maps = new HashMap<>();
        for (VoUrlRoles it : urlRolesList) {
            maps.put(it.getResourceUrl(), it.getRoles());
        }
        redisTemplate.opsForHash().putAll(CurrentUserUtil.REDIS_API_HASH_KEY, maps);
    }

    @Override
    public void refreshApiJurisdiction(String url) {
        List<VoUrlRoles> urlRolesList = authTbResourceMapper.selectApiRelationRoles(url);
        if (urlRolesList == null || urlRolesList.isEmpty()) {
            return;
        }
        for (VoUrlRoles it : urlRolesList) {
            redisTemplate.opsForHash().put(CurrentUserUtil.REDIS_API_HASH_KEY, it.getResourceUrl(), it.getRoles());
        }
    }

    @PostConstruct
    public void init() {
        this.refreshAllApiJurisdiction();

    }

}
