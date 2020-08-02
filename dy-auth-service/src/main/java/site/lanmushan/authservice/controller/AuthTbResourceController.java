package site.lanmushan.authservice.controller;

import org.apache.commons.lang3.StringUtils;
import site.lanmushan.authservice.constant.ResourceConstant;
import site.lanmushan.authservice.entity.AuthTbResource;
import site.lanmushan.authservice.entity.AuthTbRole;
import site.lanmushan.authservice.mapper.AuthTbRoleMapper;
import site.lanmushan.authservice.service.AuthTbResourceService;
import org.springframework.web.bind.annotation.*;
import site.lanmushan.framework.dto.Message;
import site.lanmushan.authservice.bo.BoAuthTbResource;
import site.lanmushan.authservice.mapper.AuthTbResourceMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import site.lanmushan.framework.entity.CurrentUser;
import site.lanmushan.framework.util.CurrentUserUtil;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜单表(AuthTbResource)表控制层
 *
 * @author dy
 * @since 2020-06-15 22:13:48
 */
@RestController
@RequestMapping("/authTbResource")
@Slf4j
public class AuthTbResourceController {
    @Autowired
    private AuthTbResourceMapper authTbResourceMapper;
    @Autowired
    private AuthTbResourceService authTbResourceService;
    @Autowired
    private AuthTbRoleMapper authTbRoleMapper;

    @GetMapping("/selectById")
    public Message selectById(@RequestParam("id") Long id) {
        Message msg = new Message();
        AuthTbResource authTbResource = authTbResourceMapper.selectByPrimaryKey(id);
        msg.setRow(authTbResource);
        return msg;
    }

    @PostMapping("/add")
    public Message add(@RequestBody BoAuthTbResource obj) {
        Message msg = new Message();
        authTbResourceService.insertService(obj);
        msg.setRow(obj).success("添加成功");
        return msg;
    }

    /**
     * @param obj
     * @return
     */
    @PostMapping("/update")
    public Message update(@RequestBody @Valid BoAuthTbResource obj) {
        Message msg = new Message();
        authTbResourceService.updateService(obj);
        msg.setRow(obj).success("更新成功");
        return msg;
    }

    /**
     * @param ids
     * @return
     */
    @PostMapping("/delete")
    public Message delete(@RequestParam List<Long> ids) {
        Message msg = new Message();
        authTbResourceService.deleteServiceByIds(ids);
        msg.success("删除成功");
        return msg;
    }

    /**
     * 返回当前用户拥有的所有菜单
     * @return
     */
    @GetMapping("/select/menu")
    public Message selectMenus() {
        return selectResource(ResourceConstant.RESOURCE_MENU);
    }
    private Message selectResource(String type){
        Message msg = new Message();
        CurrentUser currentUser = CurrentUserUtil.getCurrentUser();
            List<AuthTbRole> roleList = authTbRoleMapper.selectRolesByUserId(currentUser.getUserId());
        if (roleList.size() == 0) {
            msg.error("您没有角色，请联系管理员");
            return msg;
        }

        List<AuthTbResource> authResourceList;
       if( CurrentUserUtil.isAdmin())
       {
           authResourceList = authTbResourceMapper.selectResourceByRoleCodes(null, type);
       }else {
           List<String> roleCodeList=roleList.stream().map(AuthTbRole::getRoleCode).collect(Collectors.toList());
           String roleCodes =  StringUtils.join(roleCodeList, ",");
           authResourceList = authTbResourceMapper.selectResourceByRoleCodes(roleCodes, type);
       }
        Collections.sort(authResourceList, new Comparator<AuthTbResource>() {
            @Override
            public int compare(AuthTbResource s1, AuthTbResource s2) {
                int result = s1.getFkParentId().compareTo(s2.getFkParentId());
                if (result != 0) {
                    return result;
                }
                result = s1.getOrderIndex().compareTo(s2.getOrderIndex());
                if (result != 0) {
                    return result;
                }
                return s1.getId().compareTo(s2.getId());
            }
        });

        if (authResourceList.size() > 0) {
            msg.setRows(authResourceList);
            msg.success("查询成功");
        } else {
            msg.error("没有您权限范围内的资源");
        }
        return msg;
    }
}