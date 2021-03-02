package site.lanmushan.cms.web.datasource;


import java.util.List;
import java.util.Map;

public interface DataSourceHandlerService {

    /**
     * 加载栏目
     *
     * @return
     */
    public List loadContentCategory();

    /**
     * 获取数据
     *
     * @param dataSourceName
     * @param params
     * @return
     */
    public Object getDataSource(String dataSourceName, Map<String, Object> params);

}
