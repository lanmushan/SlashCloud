package site.lanmushan.framework.query.controller;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import site.lanmushan.framework.constant.HTTPCode;
import site.lanmushan.framework.datasope.annotation.EnabledDataScope;
import site.lanmushan.framework.dto.Message;
import site.lanmushan.framework.dto.QueryInfo;
import site.lanmushan.framework.exception.OperateException;
import site.lanmushan.framework.query.annotations.RequestQueryInfo;
import site.lanmushan.framework.query.util.StringCommonUtil;
import site.lanmushan.framework.util.ApplicationUtil;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;

/**
 * 通用查询接口
 * （不安全）需要结合权限控制进行使用
 *
 * @author dy
 */
@RestController
@Slf4j
@Order(-9999)
@ConditionalOnProperty(prefix = "slash", name = "query", havingValue = "true")
public class QueryController extends BaseController {
    @EnabledDataScope
    @RequestMapping(value = "/{entityName}/{methodName:[^\\.]\\w*$}", method = RequestMethod.GET)
    public Message selectList(@PathVariable("entityName") String entityName, @PathVariable("methodName") String methodName, @RequestQueryInfo QueryInfo queryInfo, HttpServletRequest request) {
        log.info("URI {} DATA:{}", request.getRequestURI(), JSONObject.toJSONString(queryInfo));
        Message msg = new Message();
        try {
            entityName = StringCommonUtil.toLowerCaseFirstOne(entityName);
            String serviceName = entityName + "Service";
            Object queryService = ApplicationUtil.getBean(serviceName);
            if (queryService == null) {
                msg.error("服务不存在");
                return msg;
            }
            Method method = queryService.getClass().getMethod(methodName, queryInfo.getClass());
            if (method == null) {
                msg.error("服务不存在");
                return msg;
            }
            Type t = method.getReturnType();
            //列表查询
            if ("java.util.List".equals(t.getTypeName())) {
                startPage();
               Object data= method.invoke(queryService, queryInfo);
                List list = (List)data;
                PageInfo pageInfo = new PageInfo(list);
                msg.setCurrentPage(pageInfo.getPageNum());
                msg.setPageSize(pageInfo.getPageSize());
                msg.setTotal((int) pageInfo.getTotal());
                msg.setRows(list);
                return msg;
            } else {
                //单个查询
                Object obj = method.invoke(queryService, queryInfo);
                if (obj != null) {
                    msg.success("查询成功").setRow(obj);
                } else {
                    msg.setHttpCode(HTTPCode.E204);
                }
                return msg;
            }
        } catch (OperateException e) {
            msg.setHttpCode(HTTPCode.C400);
            log.error("查询失败:{};Exception:", request.getRequestURI(), e);
        } catch (Exception e) {
            msg.setHttpCode(HTTPCode.C404);
            msg.error("查询失败" + e.getLocalizedMessage());
            log.error("查询失败:{};Exception:", request.getRequestURI(), e);
        }
        return msg;
    }
//
//    @GetMapping(value = "/{entityName}/export")
//    public void export(@PathVariable("entityName") String entityName, @RequestQueryInfo QueryInfo queryInfo, HttpServletRequest request, HttpServletResponse response) {
//        try {
//            String methodName="selectList";
//            entityName = StringCommonUtil.toLowerCaseFirstOne(entityName);
//            Object queryService = null;
//            if (entityName.lastIndexOf("Service") > 0 || entityName.lastIndexOf("service") > 0) {
//                queryService = ApplicationUtil.getBean(entityName);
//            } else {
//                queryService = ApplicationUtil.getBean(entityName + "Mapper");
//            }
//            Method method = queryService.getClass().getMethod(methodName, queryInfo.getClass());
//            Type t = method.getAnnotatedReturnType().getType();
//            //列表查询
//            if ("java.util.List".equals(t.getTypeName())) {
//                if (queryInfo.getPageSize() != null) {
//                    startPage();
//                }
//                List list = (List) method.invoke(queryService, queryInfo);
//                Type type = getGenerics(queryService);
//                Class<?> entityClass = Class.forName(type.getTypeName());
//                List sysDictList = DHashMap.toEntityList(list, entityClass);
//                sysDictList.forEach(it -> {
//                    log.info(it.toString());
//                });
//                EasyExcelUtil.export(entityClass, sysDictList, response);
//            }
//        } catch (OperateException e) {
//            log.error("查询失败:{};Exception:", request.getRequestURI(), e);
//        } catch (Exception e) {
//            log.error("查询失败:{};Exception:", request.getRequestURI(), e);
//        }
//
//    }


}
