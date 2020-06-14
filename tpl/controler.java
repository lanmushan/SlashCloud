##定义初始变量
        #set($tableName = $tool.append($tableInfo.name, "Controller"))
        ##设置回调
        $!callback.setFileName($tool.append($tableName, ".java"))
        $!callback.setSavePath($tool.append($tableInfo.savePath, "/controller"))
        ##拿到主键
        #if(!$tableInfo.pkColumn.isEmpty())
        #set($pk = $tableInfo.pkColumn.get(0))
        #end

        #if($tableInfo.savePackageName)package $!{tableInfo.savePackageName}.#{end}controller;

import $!{tableInfo.savePackageName}.entity.$!{tableInfo.name};
import $!{tableInfo.savePackageName}.service.$!{tableInfo.name}Service;
import org.springframework.web.bind.annotation.*;
import com.lanmushan.framework.dto.Message;
import com.lanmushan.framework.dto.QueryInfo;
import $!{tableInfo.savePackageName}.bo.Bo$!{tableInfo.name};
import $!{tableInfo.savePackageName}.mapper.$!{tableInfo.name}Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
/**
 * $!{tableInfo.comment}($!{tableInfo.name})表控制层
 *
 * @author $!author
 * @since $!time.currTime()
 */
@RestController
@RequestMapping("/$!tool.firstLowerCase($tableInfo.name)")
@Slf4j
public class $!{tableName} {
@Autowired
private $!{tableInfo.name}Mapper $!tool.firstLowerCase($!{tableInfo.name})Mapper;
@Autowired
private $!{tableInfo.name}Service $!tool.firstLowerCase($!{tableInfo.name})Service;

@GetMapping("/selectById")
public Message selectById(@RequestParam("id") Long id) {
        Message msg = new Message();
        $!{tableInfo.name} $!tool.firstLowerCase($!{tableInfo.name})= $!{tool.firstLowerCase($!{tableInfo.name})}Mapper.selectByPrimaryKey(id);
        msg.setRow($!tool.firstLowerCase($!{tableInfo.name}));
        return msg;
        }

@PostMapping("/add")
public Message add(@RequestBody @Valid Bo$!{tableInfo.name} obj) {
        Message msg = new Message();
        $!{tool.firstLowerCase($!{tableInfo.name})}Service.insertService(obj);
        msg.setRow(obj).success("添加成功");
        return msg;
        }
/**
 * @param obj
 * @return
 */
@PostMapping("/update")
public Message update(@RequestBody @Valid Bo$!{tableInfo.name} obj) {
        Message msg = new Message();
        $!{tool.firstLowerCase($!{tableInfo.name})}Service.updateService(obj);
        msg.setRow(obj).success("更新成功");
        return msg;
        }
/**
 * @param ids
 * @return
 */
@DeleteMapping("/delete")
public Message delete(@RequestParam List<Long> ids) {
        Message msg = new Message();
        $!{tool.firstLowerCase($!{tableInfo.name})}Service.deleteServiceByIds(ids);
        msg.success("删除成功");
        return msg;
        }

        }