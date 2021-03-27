package site.lanmushan.auth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.lanmushan.auth.api.bo.BoAuthFkRoleResource;
import site.lanmushan.auth.api.service.AuthFkRoleResourceService;
import site.lanmushan.auth.api.req.RoleRelationResource;
import site.lanmushan.framework.dto.Message;
import site.lanmushan.framework.query.controller.BaseController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
@RestController
@RequestMapping("/authFkRoleResource")
@Slf4j
public class AuthFkRoleResourceController extends BaseController {
    @Autowired
    AuthFkRoleResourceService authFkRoleResourceService;

    /**
     * 角色管理资源
     *
     * @param roleRelationResource
     * @return
     */
    @RequestMapping("/updateRoleRelation")
    public Message updateRoleRelationMenu(@RequestBody RoleRelationResource roleRelationResource) {
        Message msg = Message.getInstance();
        authFkRoleResourceService.updateRoleRelationResource(roleRelationResource);
        msg.success("操作成功");
        return msg;
    }
}
