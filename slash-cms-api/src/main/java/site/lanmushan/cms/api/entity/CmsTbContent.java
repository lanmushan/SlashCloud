package site.lanmushan.cms.api.entity;

import java.util.Date;
import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;
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
 * 文章(CmsTbContent)实体类
 *
 * @author dy
 * @since 2021-01-30 14:44:52
 */
@Table(name = "cms_tb_content")
@Data
public class CmsTbContent extends BaseEntity {
    private static final long serialVersionUID = -99577192324039296L;


    @Id
    @KeySql(genId = SeqGenId.class)
    private Long id;
    /**
     * 所属类别
     */
    @ExcelProperty(value = " 所属类别")
    private String fkCategoryCode;
    /**
     * 文章内容
     */
    @ExcelProperty(value = " 文章内容")
    private String contentDetails;
    /**
     * 文章标题
     */
    @ExcelProperty(value = " 文章标题")
    private String contentTitle;
    /**
     * 关键字
     */
    @ExcelProperty(value = " 关键字")
    private String contentKeyword;
    /**
     * 描述
     */
    @ExcelProperty(value = " 描述")
    private String contentDescription;
    /**
     * 文章缩略图
     */
    @ExcelProperty(value = " 文章缩略图")
    private String contentImgs;
    /**
     * 文章来源
     */
    @ExcelProperty(value = " 文章来源")
    private String contentSource;
    /**
     * 文章作者
     */
    @ExcelProperty(value = " 文章作者")
    private String contentAuthor;
    /**
     * 是否显示
     */
    @ExcelProperty(value = " 是否显示")
    private Integer contentDisplay;
    /**
     * 文章类型
     */
    @ExcelProperty(value = " 文章类型")
    private String contentType;
    /**
     * 发布时间
     */
    @ExcelProperty(value = " 发布时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date contentDatetime;
    /**
     * 自定义顺序
     */
    @ExcelProperty(value = " 自定义顺序")
    private Integer contentSort;
    /**
     * 点击次数
     */
    @ExcelProperty(value = " 点击次数")
    private Integer contentHit;
    /**
     * 修改时间
     */
    @ExcelProperty(value = " 修改时间")
    private Date updateTime;
    /**
     * 创建时间
     */
    @ExcelProperty(value = " 创建时间")
    private Date createTime;
    /**
     * 删除标记
     */
    @ExcelProperty(value = " 删除标记")
    private Integer deleted;

    private Integer recommend;
    private Integer top;
    private Integer supportCount;
}