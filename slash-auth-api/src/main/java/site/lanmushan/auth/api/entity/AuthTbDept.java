package site.lanmushan.auth.api.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import site.lanmushan.framework.uuid.SeqGenId;
import site.lanmushan.query.entity.BaseEntity;
import site.lanmushan.query.entity.TreeNode;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 部门表(AuthTbDept)实体类
 *
 * @author dy
 * @since 2020-07-02 22:14:51
 */
@Table(name = "auth_tb_dept")
@Data
public class AuthTbDept extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -29261044802391350L;
    /**
     * 部门id
     */
    @Id
    @KeySql(genId = SeqGenId.class)
    @ExcelProperty(value = " 部门id")
    private Long id;
    /**
     * 父部门id
     */
    @ExcelProperty(value = " 父部门id")
    private Long fkParentId;
    /**
     * 部门名称
     */
    @ExcelProperty(value = " 部门名称")
    private String deptName;

    @ExcelProperty(value = "部门全称")
    private String fullDeptName;
    /**
     * 显示顺序
     */
    @ExcelProperty(value = " 显示顺序")
    private Integer orderIndex;
    /**
     * 负责人
     */
    @ExcelProperty(value = " 负责人")
    private String leader;
    /**
     * 联系电话
     */
    @ExcelProperty(value = " 联系电话")
    private String phone;
    /**
     * 邮箱
     */
    @ExcelProperty(value = " 邮箱")
    private String email;
    /**
     * 部门状态（0正常 1停用）
     */
    @ExcelProperty(value = " 部门状态（0正常 1停用）")
    private Integer deleted;
    /**
     * 创建者
     */
    @ExcelProperty(value = " 创建者")
    private String createUserId;
    /**
     * 创建时间
     */
    @ExcelProperty(value = " 创建时间")
    private Date createTime;
    /**
     * 更新者
     */
    @ExcelProperty(value = " 更新者")
    private String updateUserId;
    /**
     * 更新时间
     */
    @ExcelProperty(value = " 更新时间")
    private Date updateTime;

    @Override
    public boolean isRoot() {
        if (fkParentId != null) {
            return fkParentId.compareTo(0L) == 0;
        }
        return false;
    }

    @Override
    public boolean isChildren(TreeNode treeNode) {
        AuthTbDept authTbDept = (AuthTbDept) treeNode;
        return this.getId().equals(authTbDept.getFkParentId());

    }
}