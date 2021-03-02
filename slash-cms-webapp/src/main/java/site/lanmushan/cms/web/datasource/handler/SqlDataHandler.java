package site.lanmushan.cms.web.datasource.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.mysql.cj.xdevapi.JsonArray;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.lanmushan.cms.mapper.CmsTbDatasourceMapper;
import site.lanmushan.cms.web.datasource.DataSourceHandler;
import site.lanmushan.groovyscript.GroovyScriptUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
@Service(value = "SqlDataHandler")
@Slf4j
public class SqlDataHandler implements DataSourceHandler {
    @Autowired
    CmsTbDatasourceMapper cmsTbDatasourceMapper;

    @Override
    public Object doHandle(String dataSourceName, String content, Map<String, Object> params) {
        String sql = GroovyScriptUtil.transform(content, params);
        log.info("执行sql:{}", sql);
        if (params != null && params.containsKey("currentPage") && params.containsKey("pageSize")) {
            PageHelper.startPage(Integer.parseInt(params.get("currentPage").toString()), Integer.parseInt(params.get("pageSize").toString()));
        }
        List<HashMap> list = cmsTbDatasourceMapper.executeSql(sql);
        return list;
    }
}
