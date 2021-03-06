package site.lanmushan.auth.api.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import site.lanmushan.framework.query.entity.BaseEntity;
import site.lanmushan.framework.uuid.SeqGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户角色关系表(AuthFkUserRole)实体类
 *
 * @author dy
 * @since 2020-07-13 21:33:05
 */
@Table(name = "auth_fk_user_role")
@Data
public class AuthFkUserRole  extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -11354346482144152L;
    /**
     * 编号
     */
    @Id
    @KeySql(genId = SeqGenId.class)
    @ExcelProperty(value = " 编号")
    private Long id;
    /**
     * 用户名称
     */
    @ExcelProperty(value = " 用户名称")
    private Long fkUserId;
    /**
     * 创建时间
     */
    @ExcelProperty(value = " 创建时间")
    private Date createTime;
    /**
     * 更新时间
     */
    @ExcelProperty(value = " 更新时间")
    private Date updateTime;

    @ExcelProperty(value = " ")
    private String createUserAccount;
    /**
     * 1
     */
    @ExcelProperty(value = " 1")
    private String updateUserAccount;

    @ExcelProperty(value = " ")
    private String fkRoleCode;


}