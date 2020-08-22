##定义初始变量
        #set($tableName = $tool.append($tableInfo.name, "ServiceImpl"))
        ##设置回调
        $!callback.setFileName($tool.append($tableName, ".java"))
        $!callback.setSavePath($tool.append($tableInfo.savePath, "/service/impl"))

        ##拿到主键
        #if(!$tableInfo.pkColumn.isEmpty())
        #set($pk = $tableInfo.pkColumn.get(0))
        #end

        #if($tableInfo.savePackageName)package $!{tableInfo.savePackageName}.#{end}service.impl;


!{tableInfo.savePackageName}.mapper.$!{tableInfo.name}Mapper;
import $!{tableInfo.savePackageName}.service.$!{tableInfo.name}Service;
import site.lanmushan.framework.file.HTTPCode;
import site.lanmushan.exception.OperateException;
import site.lanmushan.sysservice.bo.Bo$!{tableInfo.name};
import site.lanmushan.sysservice.entity.$!{tableInfo.name};
import site.lanmushan.framework.dto.QueryInfo;
import site.lanmushan.framework.util.date.DateUtil;
import site.lanmushan.framework.util.uuid.MyUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * $!{tableInfo.comment}($!{tableInfo.name})表服务实现类
 *
 * @author $!author
 * @since $!time.currTime()
 */
@Service("$!tool.firstLowerCase($!{tableInfo.name})Service")
public class $!{tableName} implements $!{tableInfo.name}Service {
@Autowired
private $!{tableInfo.name}Mapper $!tool.firstLowerCase($!{tableInfo.name})Mapper;
@Override
public List selectList(QueryInfo queryInfo) {
        return  $!{tool.firstLowerCase($!{tableInfo.name})}Mapper.selectList(queryInfo);
        }
@Override
public void insertService(Bo$!{tableInfo.name} bo$!{tableInfo.name}) {
        Date now= DateUtil.now();
        bo$!{tableInfo.name}.setCreateTime(now);
        bo$!{tableInfo.name}.setUpdateTime(now);
        $!{tool.firstLowerCase($!{tableInfo.name})}Mapper.insertSelective(bo$!{tableInfo.name});
        }
@Override
public  void insertServiceList(List<Bo$!{tableInfo.name}> bo$!{tableInfo.name}List) {
        Date now= DateUtil.now();
        bo$!{tableInfo.name}List.forEach(it->{
        it.setCreateTime(now);
        it.setUpdateTime(now);
        it.setId(MyUUID.getInstance().nextId());
        });
        $!{tool.firstLowerCase($!{tableInfo.name})}Mapper.insertList(bo$!{tableInfo.name}List);
        }
@Override
public void updateService(Bo$!{tableInfo.name} bo$!{tableInfo.name}) {
        bo$!{tableInfo.name}.setUpdateTime(DateUtil.now());
        int reuslt= $!{tool.firstLowerCase($!{tableInfo.name})}Mapper.updateByPrimaryKeySelective(bo$!{tableInfo.name});
        if(reuslt!=1)
        {
        throw new OperateException("新增失败", HTTPCode.Fail);
        }
        }
@Override
public void deleteServiceByIds(List<Long> ids) {
        $!{tool.firstLowerCase($!{tableInfo.name})}Mapper.deleteByIdList(ids);
        }
        }