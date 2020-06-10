package com.lanmushan.framework.controller;


import com.lanmushan.framework.annotations.RequestQueryInfo;
import com.lanmushan.framework.configure.ApplicationUtil;
import com.lanmushan.framework.constant.HTTPCode;
import com.lanmushan.framework.dto.Message;
import com.lanmushan.framework.dto.QueryInfo;
import com.lanmushan.framework.exception.OperateException;
import com.lanmushan.framework.util.StringCommonUtil;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;

/**
 * 通用查询接口
 * （不安全）需要结合权限控制进行使用
 * @author dy
 */
@RestController
@Slf4j
public class QueryController extends BaseController {
    @GetMapping( value = "/{entityName}/{methodName}" )
    public Message queryList(@RequestQueryInfo QueryInfo queryInfo, @PathVariable("entityName") String entityName, @PathVariable("methodName") String methodName, HttpServletRequest request) {
        log.info("请求UID:"+request.getHeader("requestSeq"));
        Message msg=new Message();
        try {
            entityName= StringCommonUtil.toLowerCaseFirstOne(entityName);
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
                    msg.success("查询成功");
                }else{
                    msg.setHttpCode(HTTPCode.OK204);
                }
                return msg;
            }
        }catch (OperateException e){
            msg.setHttpCode(HTTPCode.PramError);
            log.error("查询失败:{};Exception:",request.getRequestURI(),e);
        }
        catch (Exception e){
            msg.setHttpCode(HTTPCode.InnerError);
            msg.error("查询失败"+e.getLocalizedMessage());
            log.error("查询失败:{};Exception:",request.getRequestURI(),e);
        }
        return msg;
    }
}
