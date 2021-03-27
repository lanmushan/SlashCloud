package site.lanmushan.sys.api.entity;

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
 * (SysTbDict)实体类
 *
 * @author dy
 * @since 2020-06-14 21:15:02
 */
@Table(name = "sys_tb_dict")
@Data
public class SysTbDict extends BaseEntity {
    private static final long serialVersionUID = -29556091738693085L;

    @Id
    @KeySql(genId = SeqGenId.class)
    @ExcelProperty(value = " ")
    private Long id;
    /**
     * 映射值
     */
    @ExcelProperty(value = " 映射值")
    private String dictValue;
    /**
     * 映射名称
     */
    @ExcelProperty(value = " 映射名称")
    private String dictName;
    /**
     * 映射编码
     */
    @ExcelProperty(value = " 映射编码")
    private String dictCode;
    /**
     * 所属分组
     */
    @ExcelProperty(value = " 所属分组")
    private String fkDictGroupCode;
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
    /**
     * 创建人
     */
    @ExcelProperty(value = " 创建人")
    private String createUserAccount;
    /**
     * 默认
     */
    @ExcelProperty(value = " 默认")
    private Integer isDefault;
    /**
     * 排序
     */
    @ExcelProperty(value = " 排序")
    private Integer orderIndex;
    /**
     * 允许编辑
     */
    @ExcelProperty(value = " 允许编辑")
    private Integer allowEdit;


}