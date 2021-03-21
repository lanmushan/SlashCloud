package site.lanmushan.auth.controller;

import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.lanmushan.auth.api.bo.BoAuthTbResource;
import site.lanmushan.auth.api.constant.ResourceConstant;
import site.lanmushan.auth.api.entity.AuthTbResource;
import site.lanmushan.auth.api.entity.AuthTbRole;
import site.lanmushan.auth.mapper.AuthTbResourceMapper;
import site.lanmushan.auth.mapper.AuthTbRoleMapper;
import site.lanmushan.auth.api.service.AuthTbResourceService;
import site.lanmushan.framework.authorization.CurrentUser;
import site.lanmushan.framework.authorization.CurrentUserUtil;
import site.lanmushan.framework.dto.Message;
import site.lanmushan.framework.dto.QueryInfo;
import site.lanmushan.framework.query.annotations.RequestQueryInfo;
import site.lanmushan.framework.query.controller.BaseController;
import site.lanmushan.framework.query.util.TreeUtil;

import javax.validation.Valid;
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
public class AuthTbResourceController extends BaseController {
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
    public Message delete(@RequestBody BoAuthTbResource obj) {
        Message msg = new Message();
        authTbResourceService.deleteServiceByIds(obj.getIds());
        msg.success("删除成功");
        return msg;
    }

    @GetMapping("/selectTreeList")
    public Message selectTreeList(@RequestQueryInfo QueryInfo queryInfo) {
        Message msg = new Message();
        startPage();
        List<AuthTbResource> authResourceList = authTbResourceMapper.selectList(queryInfo);
        sortResource(authResourceList);
        PageInfo pageInfo = new PageInfo(authResourceList);
        msg.setCurrentPage(pageInfo.getPageNum());
        msg.setPageSize(pageInfo.getPageSize());
        msg.setTotal((int) pageInfo.getTotal());
        msg.setRows(TreeUtil.listToTree((List) authResourceList));
        return msg;
    }

    /**
     * 返回当前用户拥有的所有菜单
     *
     * @return
     */
    @GetMapping("/select/menu")
    public Message selectMenus() {
        Message msg = Message.getInstance();
        List menuList = selectResource(ResourceConstant.RESOURCE_MENU);
        if (null != menuList && menuList.size() > 0) {
            msg.setRows(TreeUtil.listToTree(menuList));
            msg.success("查询成功");
        } else {
            msg.error("没有您权限范围内的资源");
        }
        return msg;
    }

    private List selectResource(String type) {
        CurrentUser currentUser = CurrentUserUtil.getCurrentUser();
        List<AuthTbRole> roleList = authTbRoleMapper.selectRolesByUserId(currentUser.getUserId());
        if (null == roleList || roleList.size() == 0) {
            return null;
        }
        List<AuthTbResource> authResourceList;
        if (currentUser.isAdmin()) {
            authResourceList = authTbResourceMapper.selectResourceByRoleCodes(null, type);
        } else {
            List<String> roleCodeList = roleList.stream().map(AuthTbRole::getRoleCode).collect(Collectors.toList());
            String roleCodes = StringUtils.join(roleCodeList, ",");
            authResourceList = authTbResourceMapper.selectResourceByRoleCodes(roleCodes, type);
        }
        sortResource(authResourceList);
        return authResourceList;
    }

    private void sortResource(List<AuthTbResource> authResourceList) {
        authResourceList.sort((s1, s2) -> {
            int result = s1.getFkParentId().compareTo(s2.getFkParentId());
            if (result != 0) {
                return result;
            }
            result = s1.getOrderIndex().compareTo(s2.getOrderIndex());
            if (result != 0) {
                return result;
            }
            return s1.getId().compareTo(s2.getId());
        });
    }
}