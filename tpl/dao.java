##定义初始变量
        #set($tableName=$tool.append($tableInfo.name,"Mapper"))
        ##设置回调
        $!callback.setFileName($tool.append($tableName,".java"))
        $!callback.setSavePath($tool.append($tableInfo.savePath,"/mapper"))

        ##拿到主键
        #if(!$tableInfo.pkColumn.isEmpty())
        #set($pk=$tableInfo.pkColumn.get(0))
        #end

        #if($tableInfo.savePackageName)package $!{tableInfo.savePackageName}.#{end}mapper;
import site.lanmushan.framework.mapper.QueryMapper;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

import $!{tableInfo.savePackageName}.entity.$!{tableInfo.name};
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * $!{tableInfo.comment}($!{tableInfo.name})表数据库访问层
 *
 * @author $!author
 * @since $!time.currTime()
 */
@org.apache.ibatis.annotations.Mapper
public interface $!{tableName}extends QueryMapper<$!{tableInfo.name}>,IdListMapper<$!{tableInfo.name},Long>,InsertListMapper<$!{tableInfo.name}>{


        }