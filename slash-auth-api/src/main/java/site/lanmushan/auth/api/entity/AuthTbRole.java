package site.lanmushan.auth.api.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import site.lanmushan.framework.query.entity.BaseEntity;
import site.lanmushan.framework.uuid.SeqGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 角色表(AuthTbRole)实体类
 *
 * @author dy
 * @since 2020-07-13 21:28:55
 */
@Table(name = "auth_tb_role")
@Data
@EqualsAndHashCode
public class AuthTbRole extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 169860209737041092L;
    /**
     * 编号
     */
    @Id
    @KeySql(genId = SeqGenId.class)
    @ExcelProperty(value = " 编号")
    private Long id;
    /**
     * 角色名称
     */
    @ExcelProperty(value = " 角色名称")
    private String roleName;
    /**
     * 角色编码
     */
    @ExcelProperty(value = " 角色编码")
    private String roleCode;
    /**
     * 是否默认
     */
    @ExcelProperty(value = " 是否默认")
    private Integer isDefault;

    private Integer priorityLevel;
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
    /**
     * 禁用
     */
    @ExcelProperty(value = " 禁用")
    private Integer disabled;


}