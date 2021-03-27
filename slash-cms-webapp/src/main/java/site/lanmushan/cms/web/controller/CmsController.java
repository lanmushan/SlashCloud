package site.lanmushan.cms.web.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerMapping;
import site.lanmushan.cms.api.entity.CmsTbRequestMapping;
import site.lanmushan.cms.api.entity.CmsTbRequestMappingDatasource;
import site.lanmushan.cms.mapper.CmsTbRequestMappingDatasourceMapper;
import site.lanmushan.cms.mapper.CmsTbRequestMappingMapper;
import site.lanmushan.cms.web.datasource.DataSourceHandlerService;
import site.lanmushan.cms.web.view.FreeMarkerStaticView;
import site.lanmushan.framework.query.util.ServletUtil;
import site.lanmushan.groovyscript.GroovyScriptUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
@Slf4j
@Controller
public class CmsController {
    @Autowired
    RedisTemplate<String, String> redisTemplate;
    @Autowired
    DataSourceHandlerService dataSourceHandlerService;
    @Autowired
    CmsTbRequestMappingMapper cmsTbRequestMappingMapper;
    @Autowired
    CmsTbRequestMappingDatasourceMapper cmsTbRequestMappingDatasourceMapper;
    public static String request_forward = "forward";
    public static String request_redirect = "redirect";
    public static String request_view = "view";

    @RequestMapping("/content/updateContentCount")
    @ResponseBody
    public String updateContentCount(@RequestParam("id") Long id) {
        return "success";
    }

    @RequestMapping("/select/{dataSourceCode}")
    @ResponseBody
    @CrossOrigin
    public Object selectDataSource(@PathVariable("dataSourceCode") String dataSourceCode, HttpServletRequest request) {
        Map<String, Object> parm = ServletUtil.getDataFromRequest(request);
        Object obj = dataSourceHandlerService.getDataSource(dataSourceCode, parm);
        return obj;
    }

    @RequestMapping("/error/404")
    public String error404() {
        return "404.ftl";
    }

    @RequestMapping("/error/500")
    public String error500() {
        return "404.ftl";
    }

    /**
     * 全局请求处理器
     *
     * @param modelMap
     * @return
     */
    public String httpServletRequestHandle(ModelMap modelMap, HttpServletRequest request) {
        log.info("请求地址:{}", request.getRequestURL().toString());
        String requestMapping = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        String requestMethod = request.getMethod();
        CmsTbRequestMapping queryCmsTbRequestMapping = new CmsTbRequestMapping();
        queryCmsTbRequestMapping.setRequestUrl(requestMapping);
        queryCmsTbRequestMapping.setRequestMethod(requestMethod);
        CmsTbRequestMapping cmsTbRequestMapping = cmsTbRequestMappingMapper.selectOne(queryCmsTbRequestMapping);
        if (null == cmsTbRequestMapping) {
            return "404.ftl";
        }
        if (request_forward.equals(cmsTbRequestMapping.getMappingType())) {
            return "forward:" + cmsTbRequestMapping.getTargetPage();
        }
        if (request_redirect.equals(cmsTbRequestMapping.getMappingType())) {
            return "redirect:" + cmsTbRequestMapping.getTargetPage();
        }
        if (request_view.equals(cmsTbRequestMapping.getMappingType())) {
            Map<String, Object> requestBody = getRequestBody(request);
            CmsTbRequestMappingDatasource cmsTbRequestMappingDatasource = new CmsTbRequestMappingDatasource();
            cmsTbRequestMappingDatasource.setFkRequestUrl(requestMapping);
            List<CmsTbRequestMappingDatasource> cmsTbRequestMappingDatasourceList = cmsTbRequestMappingDatasourceMapper.select(cmsTbRequestMappingDatasource);
            Map<String, Object> datasourceParamsMap = new HashMap<>();
            datasourceParamsMap.put("requestBody", requestBody);
            for (int i = 0; i < cmsTbRequestMappingDatasourceList.size(); i++) {
                CmsTbRequestMappingDatasource it = cmsTbRequestMappingDatasourceList.get(i);
                log.info("转换数据映射:{} 内容:{}", it.getFkDatasourceCode(), it.getFkDatasourceParamsMapping());
                Map<String, Object> parm = datasourceParamsMap;
                if (it.getFkDatasourceParamsMapping() != null && "".equals(it.getFkDatasourceParamsMapping().trim())) {
                    String jsonString = GroovyScriptUtil.transformUseClass(it.getFkDatasourceParamsMapping(), null, datasourceParamsMap);
                    parm = JSONObject.parseObject(jsonString, HashMap.class);
                }/**/
                Object obj = dataSourceHandlerService.getDataSource(it.getFkDatasourceCode(), parm);
                if (obj == null && it.getAllowNull() != null && it.getAllowNull() == 0) {
                    return "404.ftl";
                }
                modelMap.put(it.getFkDatasourceCode(), obj);
            }
            modelMap.put("requestBody", requestBody);
            request.setAttribute(FreeMarkerStaticView.PAGE_RE_WRITE_TIME, cmsTbRequestMapping.getPageReWrite());
            modelMap.put("basePath", ServletUtil.getBasePath(request));
            return cmsTbRequestMapping.getRequestTpl();
        }
        return "404.ftl";
    }

    private Map<String, Object> getRequestBody(HttpServletRequest request) {
        Map pathVariableMap = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Map<String, Object> requestBody = ServletUtil.getDataFromRequest(request);
        requestBody.putAll(pathVariableMap);
        return requestBody;
    }
}
