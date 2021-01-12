package site.lanmushan.query.controller;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import site.lanmushan.framework.constant.HTTPCode;
import site.lanmushan.framework.dto.Message;
import site.lanmushan.framework.dto.QueryInfo;
import site.lanmushan.framework.exception.OperateException;
import site.lanmushan.query.annotations.RequestQueryInfo;
import site.lanmushan.query.service.BaseService;
import site.lanmushan.query.util.ApplicationUtil;
import site.lanmushan.query.util.StringCommonUtil;

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
@Order(value = -99999)
public class QueryController extends BaseController {
    @GetMapping(value = "/{entityName}/{methodName}")
    public Message selectList(@PathVariable("entityName") String entityName, @PathVariable("methodName") String methodName, @RequestQueryInfo QueryInfo queryInfo, HttpServletRequest request) {
        log.info(JSONObject.toJSONString(queryInfo));
        Message msg = new Message();
        try {
            entityName = StringCommonUtil.toLowerCaseFirstOne(entityName);
            Object queryService = ApplicationUtil.getBean(entityName + "Service", BaseService.class);
            Method method = queryService.getClass().getMethod(methodName, queryInfo.getClass());
            Type t = method.getReturnType();
            //列表查询
            System.out.println("类型："+t.getTypeName());
            if ("java.util.List".equals(t.getTypeName())) {
                startPage();
                List list = (List) method.invoke(queryService, queryInfo);
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
