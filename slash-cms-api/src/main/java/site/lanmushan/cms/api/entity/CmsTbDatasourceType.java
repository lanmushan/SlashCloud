package site.lanmushan.cms.api.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import site.lanmushan.framework.query.entity.BaseEntity;
import site.lanmushan.framework.query.entity.TreeNode;
import site.lanmushan.framework.uuid.SeqGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * (CmsTbDatasourceType)实体类
 *
 * @author dy
 * @since 2021-01-30 14:44:58
 */
@Table(name = "cms_tb_datasource_type")
@Data
public class CmsTbDatasourceType extends BaseEntity {
    private static final long serialVersionUID = 742850622395074247L;

    @Id
    @KeySql(genId = SeqGenId.class)
    private Long id;

    @ExcelProperty(value = " ")
    private String typeCode;

    @ExcelProperty(value = " ")
    private String fkParentTypeCode;

    @ExcelProperty(value = " ")
    private String typeDesc;

    @ExcelProperty(value = " ")
    private String typeName;

    @ExcelProperty(value = " ")
    private Date createTime;

    @ExcelProperty(value = " ")
    private Date updateTime;

    @Override
    public boolean isRoot() {
        return this.typeCode.equals(this.fkParentTypeCode);
    }

    @Override
    public boolean isChildren(TreeNode treeNode) {
        CmsTbDatasourceType authTbDept = (CmsTbDatasourceType) treeNode;
        return this.typeCode.equals(authTbDept.getFkParentTypeCode());
    }
}