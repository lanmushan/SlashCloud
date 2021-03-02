package site.lanmushan.cms.web.datasource.handler;

import org.springframework.stereotype.Service;
import site.lanmushan.cms.web.datasource.DataSourceHandler;

import java.util.Map;

/**
 * @author Administrator
 */
@Service(value = "StringDataHandler")
public class StringDataHandler implements DataSourceHandler {
    @Override
    public Object doHandle(String dataSourceName, String content, Map<String, Object> params) {
        return content;
    }
}
