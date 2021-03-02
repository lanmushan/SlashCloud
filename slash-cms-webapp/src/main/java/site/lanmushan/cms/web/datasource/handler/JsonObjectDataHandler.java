package site.lanmushan.cms.web.datasource.handler;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import site.lanmushan.cms.web.datasource.DataSourceHandler;

import java.util.Map;

/**
 * @author Administrator
 */
@Service(value = "JsonObjectDataHandler")
public class JsonObjectDataHandler implements DataSourceHandler {
    @Override
    public Object doHandle(String dataSourceName, String content, Map<String, Object> params) {
        return JSONObject.parse(content);
    }
}
