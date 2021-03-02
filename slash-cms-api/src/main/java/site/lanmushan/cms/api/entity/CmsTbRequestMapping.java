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
 * (CmsTbRequestMapping)实体类
 *
 * @author dy
 * @since 2021-02-07 20:39:00
 */
@Table(name = "cms_tb_request_mapping")
@Data
public class CmsTbRequestMapping extends BaseEntity {
    private static final long serialVersionUID = -28886190934967640L;

    @Id
    @KeySql(genId = SeqGenId.class)
    private Long id;

    @ExcelProperty(value = " ")
    private String requestUrl;

    @ExcelProperty(value = " ")
    private String requestMethod;
    /**
     * 映射类型
     */
    @ExcelProperty(value = " 映射类型")
    private String mappingType;
    /**
     * 模板
     */
    @ExcelProperty(value = " 模板")
    private String requestTpl;
    /**
     * 加工脚本
     */
    @ExcelProperty(value = " 加工脚本")
    private String encoderScript;
    /**
     * 静态化
     */
    @ExcelProperty(value = " 静态化")
    private Integer pageReWrite;

    @ExcelProperty(value = " ")
    private Date createTime;

    @ExcelProperty(value = " ")
    private Date updateTime;

    private String targetPage;


}