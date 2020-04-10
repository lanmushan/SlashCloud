package com.lanmushan.framework.controller;


import com.lanmushan.framework.configure.ApplicationUtil;
import com.lanmushan.framework.dto.Message;
import com.lanmushan.framework.dto.QueryInfo;
import com.lanmushan.framework.exception.OperateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author Administrator
 */
@RequestMapping("/Admin")
@RestController
public class QueryController extends BaseController {
    private Logger log = LoggerFactory.getLogger(getClass());
    @GetMapping( value = "/{entityName}/{methodName}" )
    public Message queryList(QueryInfo queryInfo, @PathVariable("entityName") String entityName,@PathVariable("methodName") String methodName, HttpServletRequest request) {
        Message msg=new Message();
        try {
             Object  queryService=null;
             if(entityName.lastIndexOf("Service")>0||entityName.lastIndexOf("service")>0)
             {
                 queryService=ApplicationUtil.getBean(entityName);
             }else {
                 queryService=ApplicationUtil.getBean(entityName+"Mapper");
             }
             Method method=  queryService.getClass().getMethod(methodName,queryInfo.getClass());
             Type t = method.getAnnotatedReturnType().getType();
             //列表查询
            if("java.util.List".equals(t.getTypeName()))
            {
                if(queryInfo.getPageSize()!=null)
                {
                     startPage();
                }
                List list= (List) method.invoke(queryService,queryInfo);
                msg.setRows(list);
                return msg;
            }else{
                //单个查询
                Object obj=method.invoke(queryService,queryInfo);
                if(obj!=null)
                {
                    msg.setRow(obj);
                    msg.success("查询成功");
                }else{
                    msg.error("没有相关数据");
                }
                return msg;
            }
        }catch (OperateException e){
            msg.error(e.getMessage());
            log.error("请求地址{}{}",request.getRequestURI(),e);
        }
        catch (Exception e){
            log.error("{}",e);
            msg.error("查询失败"+e.getLocalizedMessage());
        }
        return msg;
    }
}
