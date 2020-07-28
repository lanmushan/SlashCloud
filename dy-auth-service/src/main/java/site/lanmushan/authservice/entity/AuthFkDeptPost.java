package site.lanmushan.authservice.entity;

import java.util.Date;
import java.io.Serializable;

import site.lanmushan.framework.util.uuid.SeqGenId;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.excel.annotation.ExcelProperty;
import tk.mybatis.mapper.annotation.KeySql;

/**
 * 角色和部门关联表(AuthFkDeptPost)实体类
 *
 * @author dy
 * @since 2020-06-15 22:13:47
 */
@Table(name = "auth_fk_dept_post")
@Data
public class AuthFkDeptPost implements Serializable {
    private static final long serialVersionUID = 993792563010466745L;
    @Id
    @KeySql(genId = SeqGenId.class)
    @ExcelProperty(value = " ")
    private Long id;

    @ExcelProperty(value = " ")
    private Long fkDeptId;

    @ExcelProperty(value = " ")
    private Long fkPostId;

    @ExcelProperty(value = " ")
    private Date createTime;

    @ExcelProperty(value = " ")
    private Date updateTime;

    @ExcelProperty(value = " ")
    private String createUser;

    @ExcelProperty(value = " ")
    private String updateUser;


}