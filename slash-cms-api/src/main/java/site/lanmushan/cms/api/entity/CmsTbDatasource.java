package site.lanmushan.cms.api.entity;

import java.util.Date;
import java.io.Serializable;

import lombok.Data;
import com.alibaba.fastjson.JSONObject;

import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.excel.annotation.ExcelProperty;
import site.lanmushan.framework.query.entity.BaseEntity;
import site.lanmushan.framework.typehandler.JsonTypeHandler;
import site.lanmushan.framework.uuid.SeqGenId;
import tk.mybatis.mapper.annotation.KeySql;

/**
 * (CmsTbDatasource)实体类
 *
 * @author dy
 * @since 2021-01-30 14:44:55
 */
@Table(name = "cms_tb_datasource")
@Data
public class CmsTbDatasource extends BaseEntity {
    private static final long serialVersionUID = -69419294468371973L;

    @Id
    @KeySql(genId = SeqGenId.class)
    private Long id;

    @ExcelProperty(value = " ")
    private String datasourceName;

    @ExcelProperty(value = " ")
    private String datasourceCode;

    @ExcelProperty(value = " ")
    private String datasourceSource;

    @ExcelProperty(value = " ")
    private String fkDatasourceTypeCode;

    @ExcelProperty(value = " ")
    private String datasourceContent;

    @ExcelProperty(value = " ")
    private Date createTime;

    @ExcelProperty(value = " ")
    private Date updateTime;

    private String datasourceParams;
}