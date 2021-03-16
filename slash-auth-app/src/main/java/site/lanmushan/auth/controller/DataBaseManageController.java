package site.lanmushan.auth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.lanmushan.auth.api.service.DataBaseManageService;
import site.lanmushan.framework.dto.DHashMap;
import site.lanmushan.framework.dto.Message;
import site.lanmushan.framework.query.controller.BaseController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Administrator
 */
@RestController
@RequestMapping(value = "/authDataBaseManage")
@Slf4j
public class DataBaseManageController extends BaseController {
    @Autowired
    DataBaseManageService dataBaseManageService;

    /**
     * 查询数据库所有数据表
     * @param appName 应用名称
     * @param searchKey 搜索关键字
     * @return
     */
    @GetMapping("/selectAllTableList")
    public Message selectAllTableList(String appName, String searchKey) {
        Message msg=Message.getInstance();
        if(searchKey==null)
        {
            searchKey="";
        }
        List<DHashMap>  tableList= dataBaseManageService.selectAllTableList(appName);
        //这里性能低一点儿没关系了，功能要正常
        final String fsearchKey=searchKey;
        tableList=tableList.stream().filter((it)->{
            System.out.println(it.get("tableName"));
             return   it.get("tableName").toString().startsWith(fsearchKey);
        }).collect(Collectors.toList());
        msg.setTotal(tableList.size());
        tableList=tableList.stream().skip((getCurrentPage()-1)*getPageSize()).limit(getPageSize()).collect(Collectors.toList());
        tableList.forEach(it->{
           List<DHashMap> fieldList= dataBaseManageService.selectTableAllTableFieldList(appName,it.get("tableName").toString());
           it.put("fieldList",fieldList);
        });
        msg.setCurrentPage(getCurrentPage());
        msg.setPageSize(getPageSize());
        msg.setRows(tableList);
        return msg;
    }
}
