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
 * (CmsTbRequestMappingDatasource)实体类
 *
 * @author dy
 * @since 2021-02-08 20:19:23
 */
@Table(name = "cms_tb_request_mapping_datasource")
@Data
public class CmsTbRequestMappingDatasource extends BaseEntity {
    private static final long serialVersionUID = -76302570406683654L;

    @ExcelProperty(value = " ")
    @Id
    @KeySql(genId = SeqGenId.class)
    private Long id;

    @ExcelProperty(value = " ")
    private String fkRequestUrl;

    @ExcelProperty(value = " ")
    private String fkDatasourceCode;

    @ExcelProperty(value = " ")
    private String fkDatasourceParamsMapping;

    @ExcelProperty(value = " ")
    private Date createTime;

    @ExcelProperty(value = " ")
    private Date updateTime;

    private Integer allowNull;

}