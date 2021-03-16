package site.lanmushan.auth.api.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import site.lanmushan.framework.query.entity.BaseEntity;
import site.lanmushan.framework.uuid.SeqGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 用户表(AuthTbDatascope)实体类
 *
 * @author dy
 * @since 2021-03-07 16:15:14
 */
@Table(name = "auth_tb_datascope")
@Data
public class AuthTbDatascope extends BaseEntity {
    private static final long serialVersionUID = 147390444505364371L;
    /**
     * 编号
     */
    @ExcelProperty(value = " 编号")
    @Id
    @KeySql(genId = SeqGenId.class)
    private Long id;

    @ExcelProperty(value = " ")
    private String appName;

    @ExcelProperty(value = " ")
    private String databaseName;

    @ExcelProperty(value = " ")
    private String tableName;

    @ExcelProperty(value = " ")
    private String ruleName;

    @ExcelProperty(value = " ")
    private Integer rulePriority;

    @ExcelProperty(value = " ")
    private String ruleValue;

    @ExcelProperty(value = " ")
    private String ruleCondition;

    @ExcelProperty(value = " ")
    private String ruleType;
    private Date createTime;

    private Date updateTime;

}