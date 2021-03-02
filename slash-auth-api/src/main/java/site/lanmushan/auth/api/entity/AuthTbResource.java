package site.lanmushan.auth.api.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import site.lanmushan.framework.uuid.SeqGenId;
import site.lanmushan.framework.query.entity.BaseEntity;
import site.lanmushan.framework.query.entity.TreeNode;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 菜单表(AuthTbResource)实体类
 *
 * @author dy
 * @since 2020-06-25 13:03:43
 */
@Table(name = "auth_tb_resource")
@Data
public class AuthTbResource extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -48376736617826881L;
    /**
     * 编号
     */
    @ExcelProperty(value = " 编号")
    @Id
    @KeySql(genId = SeqGenId.class)
    private Long id;
    /**
     * 菜单名称
     */
    @ExcelProperty(value = " 菜单名称")
    private String resourceName;
    /**
     * 菜单描述
     */
    @ExcelProperty(value = " 菜单描述")
    private String resourceDescription;
    /**
     * 菜单链接
     */
    @ExcelProperty(value = " 菜单链接")
    private String resourceUrl;
    /**
     * 图标
     */
    @ExcelProperty(value = " 图标")
    private String iconDefault;
    /**
     * 上级
     */
    @ExcelProperty(value = " 上级")
    private Long fkParentId;
    /**
     * page,api,menu,elm
     */
    @ExcelProperty(value = " page,api,menu,elm")
    private String resourceType;
    /**
     * 禁用
     */
    @ExcelProperty(value = " 禁用")
    private Integer disabled;
    /**
     * 使用于页面元素
     */
    @ExcelProperty(value = " 使用于页面元素")
    private String resourceId;
    /**
     * 排序
     */
    @ExcelProperty(value = " 排序")
    private Integer orderIndex;

    @ExcelProperty(value = " ")
    private Long createUserId;

    @ExcelProperty(value = " ")
    private Date createTime;

    @ExcelProperty(value = " ")
    private Date updateTime;

    private JSONObject resourceAttr;


    @Override
    public boolean isRoot() {
        if (fkParentId != null) {
            return fkParentId.compareTo(0L) == 0;
        }
        return false;
    }

    @Override
    public boolean isChildren(TreeNode treeNode) {
        AuthTbResource authTbResource = (AuthTbResource) treeNode;
        return this.getId().equals(authTbResource.getFkParentId());

    }
}