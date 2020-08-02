package site.lanmushan.framework.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

/**
 * @Author dy
 * @Date 2020/6/15 22:52
 * @Version 1.0
 */
@Data
public class CurrentUser implements Serializable {
    private Long userId;
    private Long deptId;
    private String nickName;
    private String username;
    private String account;
    private Integer sex;
    private String headImgAddress;
    /**
     * 角色
     */
    private List<String> roleCodes;
    private List<String> apiList;
}
