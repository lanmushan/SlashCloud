package site.lanmushan.cms.web.datasource;

import java.util.Map;

/**
 * @author Administrator
 */
public interface DataSourceHandler {
    /**
     * 数据源调用处理
     *
     * @param dataSourceName
     * @param params
     * @return
     */
    Object doHandle(String dataSourceName, String content, Map<String, Object> params);
}
