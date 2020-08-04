package site.lanmushan.framework.controller;


import com.alibaba.fastjson.JSONObject;
import site.lanmushan.framework.constant.HTTPCode;
import site.lanmushan.framework.dto.Message;
import site.lanmushan.framework.dto.QueryInfo;
import site.lanmushan.framework.utils.OperateException;
import site.lanmushan.framework.annotations.RequestQueryInfo;
import site.lanmushan.framework.configure.ApplicationUtil;

import site.lanmushan.framework.util.ReflectionUtil;
import site.lanmushan.framework.util.StringCommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import sun.reflect.generics.repository.ClassRepository;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
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
public class QueryController extends BaseController {
    @GetMapping(value = "/{entityName}/{methodName}")
    public Message queryList(@PathVariable("entityName") String entityName, @PathVariable("methodName") String methodName, @RequestQueryInfo QueryInfo queryInfo, HttpServletRequest request) {
        log.info(JSONObject.toJSONString(queryInfo));
        Message msg = new Message();
        try {
            entityName = StringCommonUtil.toLowerCaseFirstOne(entityName);
            Object queryService = ApplicationUtil.getBean(entityName + "Service");
            Method method = queryService.getClass().getMethod(methodName, queryInfo.getClass());
            Type t = method.getAnnotatedReturnType().getType();
            //列表查询
            if ("java.util.List".equals(t.getTypeName())) {
                startPage();
                List list = (List) method.invoke(queryService, queryInfo);
                msg.setRows(list);
                return msg;
            } else {
                //单个查询
                Object obj = method.invoke(queryService, queryInfo);
                if (obj != null) {
                    msg.success("查询成功");
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

    private Type getGenerics(Object object) {
        Type interfacesTypes = object.getClass().getGenericInterfaces()[0];
        interfacesTypes.getClass().getTypeParameters();
        ClassRepository classRepository = (ClassRepository) ReflectionUtil.getFieldValue(interfacesTypes, "genericInfo");
        ParameterizedType parameterizedType = (ParameterizedType) classRepository.getSuperInterfaces()[0];
        Type type = parameterizedType.getActualTypeArguments()[0];
        return type;
    }
}
