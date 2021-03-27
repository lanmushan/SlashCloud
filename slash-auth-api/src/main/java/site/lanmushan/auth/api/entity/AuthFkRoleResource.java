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
 * (AuthFkRoleResource)实体类
 *
 * @author dy
 * @since 2020-06-15 22:13:47
 */
@Table(name = "auth_fk_role_resource")
@Data
public class AuthFkRoleResource  extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -53753227212321539L;
    @Id
    @KeySql(genId = SeqGenId.class)
    @ExcelProperty(value = " ")
    private Long id;



    @ExcelProperty(value = " ")
    private Long fkResourceId;

    @ExcelProperty(value = " ")
    private Date createTime;

    @ExcelProperty(value = " ")
    private Date updateTime;

    @ExcelProperty(value = " ")
    private String fkRoleCode;

}