package site.lanmushan.framework.datasope.bean;

import lombok.Data;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
@Data
public class DataScopeUserContext {
    private Long userId;
    private Long deptId;
    private String nickName;
    private String username;
    private String account;
    private Integer sex;
    private String headImgAddress;
    private List<String> roleCodes;
    public boolean hasRole(String ...role){
        if(roleCodes==null||role.length==0)
        {
           return false;
        }
        List<String> codes= Arrays.asList(role);

        return  roleCodes.stream().allMatch(codes::contains);
    }

    public static void main(String[] args) {
        DataScopeUserContext dataScopeUserContext=new DataScopeUserContext();
        dataScopeUserContext.setRoleCodes(Arrays.asList("admin"));
        System.out.println(dataScopeUserContext.hasRole("admin"));
    }
}
