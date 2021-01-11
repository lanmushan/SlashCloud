package site.lanmushan.framework.utils;

import lombok.Data;
import org.junit.Test;
import site.lanmushan.framework.util.utils.JsonUrlUtil;

import java.util.ArrayList;
import java.util.List;

public class JsonUrlUtilTest {

    @Test
    public void test1(){
        User user=new User();
        user.setName("张三");
        user.setSex("男");
        user.setId(1111);
        user.setRoles(new ArrayList<>());
        Role role=new Role();
        role.setRoleCode("test");
        role.setRoleName("角色名称");
        user.getRoles().add(role);
        Role role1=new Role();
        role1.setRoleCode("test1");
        role1.setRoleName("角色名称1");
        user.getRoles().add(role1);
        user.setRole(role);
        System.out.println( JsonUrlUtil.parseNameValue(user));
    }

}
@Data
class User{
    private String name;
    private String sex;
    private int id;
    private List<Role> roles;
    private Role role;
}
@Data
class Role{
    private String roleName;
    private String roleCode;
}