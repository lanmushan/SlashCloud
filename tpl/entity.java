##引入宏定义
        $!define

        ##使用宏定义设置回调（保存位置与文件后缀）
        #save("/entity",".java")

        ##使用宏定义设置包后缀
        #setPackageSuffix("entity")

        ##使用全局变量实现默认包导入
        $!autoImport
import java.io.Serializable;

import lombok.Data;
import com.alibaba.fastjson.JSONObject;

import javax.persistence.Table;

import com.alibaba.excel.annotation.ExcelProperty;
import site.lanmushan.framework.query.entity.BaseEntity;
import site.lanmushan.framework.typehandler.JsonTypeHandler;
##使用宏定义实现类注释信息
        #tableComment("实体类")

@Table(name = "$!tableInfo.obj.name")
@Data
public class $!{tableInfo.name}extends BaseEntity{
private static final long serialVersionUID=$!tool.serial();
        #foreach($column in $tableInfo.fullColumn)
        #if(${column.comment})/**
 * ${column.comment}
 */#end

@ExcelProperty(value = " #if(${column.comment})${column.comment}#end")
private $!{tool.getClsNameByFullName($column.type)}$!{column.name};
        #end


        }