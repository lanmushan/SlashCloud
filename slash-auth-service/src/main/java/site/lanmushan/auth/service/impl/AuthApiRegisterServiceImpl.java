package site.lanmushan.auth.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import site.lanmushan.auth.api.service.AuthApiRegisterService;
import site.lanmushan.auth.api.vo.VoUrlRoles;
import site.lanmushan.auth.mapper.AuthTbResourceMapper;
import site.lanmushan.framework.authorization.Authority;
import site.lanmushan.framework.authorization.CurrentUserUtil;
import site.lanmushan.framework.authorization.SignFilterManager;
import site.lanmushan.framework.constant.GlobalInstructionConstant;
import site.lanmushan.framework.redis.GlobalInstructionEntity;
import site.lanmushan.framework.redis.publish.GlobalInstructionPublish;

import java.util.*;

/**
 * 将所有的api存储到redis中
 *
 * @author Administrator
 */
@Service
@Slf4j
public class AuthApiRegisterServiceImpl implements AuthApiRegisterService, ApplicationRunner {
    @Autowired
    AuthTbResourceMapper authTbResourceMapper;
    @Autowired
    RedisTemplate<String, String> redisTemplate;
    private static PathMatcher pathMatcher = new AntPathMatcher();
    @Autowired
    GlobalInstructionPublish globalInstructionPublish;

    @Override
    public void refreshAllApiJurisdiction() {
        List<VoUrlRoles> urlRolesList = authTbResourceMapper.selectApiRelationRoles(null);
        if (urlRolesList == null || urlRolesList.isEmpty()) {
            return;
        }
        //单个
        Map<String, String> singleMaps = new HashMap<>(urlRolesList.size());
        List<Authority> pathPattern = new ArrayList<>();
        for (VoUrlRoles it : urlRolesList) {
            if (it.getRoles() == null) {
                continue;
            }
            if (!pathMatcher.isPattern(it.getResourceUrl())) {
                singleMaps.put(it.getResourceUrl(), it.getRoles());
            } else {
                pathPattern.add(toAuthority(it));
            }
        }
        redisTemplate.opsForHash().putAll(CurrentUserUtil.REDIS_API_HASH_KEY, singleMaps);

        GlobalInstructionEntity globalInstructionEntity = new GlobalInstructionEntity();
        globalInstructionEntity.setCmd(GlobalInstructionConstant.UPDATE_API_DATA);
        globalInstructionEntity.setData(pathPattern);
        globalInstructionPublish.publish(globalInstructionEntity);
        log.info("发送Api权限成功 共{}个 指定路径{} 正则路径{} 其余无效", urlRolesList.size(), singleMaps.size(), pathPattern.size());
    }

    @Override
    public void refreshApiJurisdiction(String url) {
        List<VoUrlRoles> urlRolesList = authTbResourceMapper.selectApiRelationRoles(url);
        if (urlRolesList == null || urlRolesList.isEmpty()) {
            return;
        }
        List<Authority> pathPattern = new ArrayList<>();

        for (VoUrlRoles it : urlRolesList) {
            if (it.getRoles() == null) {
                continue;
            }
            if (!pathMatcher.isPattern(it.getResourceUrl())) {
                redisTemplate.opsForHash().put(CurrentUserUtil.REDIS_API_HASH_KEY, it.getResourceUrl(), it.getRoles());
            } else {
                pathPattern.add(toAuthority(it));
            }
        }
        GlobalInstructionEntity globalInstructionEntity = new GlobalInstructionEntity();
        globalInstructionEntity.setCmd(GlobalInstructionConstant.UPDATE_API_DATA);
        globalInstructionEntity.setData(pathPattern);
        globalInstructionPublish.publish(globalInstructionEntity);
        log.info("刷新Api权限成功 URL:{}", url);

    }

    private Authority toAuthority(VoUrlRoles voUrlRoles) {
        Authority authority = new Authority();
        authority.setUrl(voUrlRoles.getResourceUrl());
        authority.setOrder(voUrlRoles.getOrderIndex()==null?0:voUrlRoles.getOrderIndex());
        authority.setRoleCodes(Arrays.asList(voUrlRoles.getRoles().split(",")));
        return authority;
    }

    @Override
    public void run(ApplicationArguments args) {
        this.refreshAllApiJurisdiction();
    }
}
