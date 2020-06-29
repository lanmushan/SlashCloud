package com.lanmushan.authservice.rpcservice;

import com.alibaba.excel.annotation.ExcelProperty;
import com.lanmushan.framework.util.uuid.SeqGenId;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * (SysTbDictGroup)实体类
 *
 * @author makejava
 * @since 2020-06-14 21:15:05
 */
@Table(name = "sys_tb_dict_group")
@Data
public class SysTbDictGroup implements Serializable {
    private static final long serialVersionUID = 830480348476080507L;
    @Id
    @KeySql(genId = SeqGenId.class)
    @ExcelProperty(value = " ")
    private Long id;
    /**
     * 字典名称
     */
    @ExcelProperty(value = " 字典名称")
    private String dictGroupName;
    /**
     * 字典编码
     */
    @ExcelProperty(value = " 字典编码")
    private String dictGroupCode;
    /**
     * 允许编辑
     */
    @ExcelProperty(value = " 允许编辑")
    private Integer allowEdit;
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
     * 创建人
     */
    @ExcelProperty(value = " 创建人")
    private Long createUserId;


}