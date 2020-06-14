##定义初始变量
        #set($tableName = $tool.append($tableInfo.name, "Service"))
        ##设置回调
        $!callback.setFileName($tool.append($tableName, ".java"))
        $!callback.setSavePath($tool.append($tableInfo.savePath, "/service"))

        ##拿到主键
        #if(!$tableInfo.pkColumn.isEmpty())
        #set($pk = $tableInfo.pkColumn.get(0))
        #end

        #if($tableInfo.savePackageName)package $!{tableInfo.savePackageName}.#{end}service;

import $!{tableInfo.savePackageName}.bo.Bo$!{tableInfo.name};
import java.util.List;
import com.lanmushan.framework.service.BaseService;
import com.lanmushan.sysservice.entity.$!{tableInfo.name};
/**
 * $!{tableInfo.comment}($!{tableInfo.name})表服务接口
 *
 * @author $!author
 * @since $!time.currTime()
 */
public interface $!{tableName} extends BaseService<$!{tableInfo.name}> {


        /**
         * 新增数据
         *
         * @param $!tool.firstLowerCase($!{tableInfo.name}) 实例对象
         * @return 实例对象
         */
        void insertService(Bo$!{tableInfo.name} $!tool.firstLowerCase($!{tableInfo.name}));

        /**
         * 修改数据
         *
         * @param $!tool.firstLowerCase($!{tableInfo.name}) 实例对象
         * @return 实例对象
         */
        void updateService(Bo$!{tableInfo.name} $!tool.firstLowerCase($!{tableInfo.name}));

        /**
         * 批量新增
         * @param  bo$!{tableInfo.name}List 实例对象
         * @return
         */
        void insertServiceList(List<Bo$!{tableInfo.name}> bo$!{tableInfo.name}List);

        /**
         * 批量删除
         * @param ids
         * @return
         */
        void deleteServiceByIds(List<Long> ids);


        }