package site.lanmushan.cms.api.entity;

import java.util.Date;
import java.io.Serializable;

import lombok.Data;
import com.alibaba.fastjson.JSONObject;

import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.excel.annotation.ExcelProperty;
import site.lanmushan.framework.query.entity.BaseEntity;
import site.lanmushan.framework.query.entity.TreeNode;
import site.lanmushan.framework.typehandler.JsonTypeHandler;
import site.lanmushan.framework.uuid.SeqGenId;
import tk.mybatis.mapper.annotation.KeySql;

/**
 * (CmsTbCategory)实体类
 *
 * @author dy
 * @since 2021-01-30 14:44:11
 */
@Table(name = "cms_tb_category")
@Data
public class CmsTbCategory extends BaseEntity {
    private static final long serialVersionUID = 994521469370823541L;

    @Id
    @KeySql(genId = SeqGenId.class)
    private Long id;

    @ExcelProperty(value = " ")
    private String categoryCode;

    @ExcelProperty(value = " ")
    private String fkParentCategoryCode;

    @ExcelProperty(value = " ")
    private String categoryDesc;

    @ExcelProperty(value = " ")
    private String categoryName;

    @ExcelProperty(value = " ")
    private Date createTime;

    @ExcelProperty(value = " ")
    private Date updateTime;

    @Override
    public boolean isRoot() {
        return this.categoryCode.equals(this.fkParentCategoryCode);
    }

    @Override
    public boolean isChildren(TreeNode treeNode) {
        CmsTbCategory authTbDept = (CmsTbCategory) treeNode;
        return this.getCategoryCode().equals(authTbDept.getFkParentCategoryCode());
    }
}