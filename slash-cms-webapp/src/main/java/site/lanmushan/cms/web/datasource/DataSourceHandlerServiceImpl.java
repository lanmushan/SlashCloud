package site.lanmushan.cms.web.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import site.lanmushan.cms.api.entity.CmsTbCategory;
import site.lanmushan.cms.api.entity.CmsTbDatasource;
import site.lanmushan.cms.mapper.CmsTbDatasourceMapper;
import site.lanmushan.cms.api.service.CmsTbCategoryService;
import site.lanmushan.framework.dto.QueryInfo;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
@Service
public class DataSourceHandlerServiceImpl implements DataSourceHandlerService {
    @Autowired
    CmsTbCategoryService cmsTbCategoryService;
    @Autowired
    CmsTbDatasourceMapper cmsTbDatasourceMapper;
    @Autowired
    ApplicationContext applicationContext;

    /**
     * 加载栏目
     *
     * @return
     */
    @Override
    public List loadContentCategory() {
        List<CmsTbCategory> list = cmsTbCategoryService.selectTreeList(new QueryInfo());
        return list.get(0).getChildren();
    }

    @Override
    public Object getDataSource(String dataSourceName, Map<String, Object> params) {
        String dataSourceType = "Inner";
        CmsTbDatasource queryCmsTbDatasource = new CmsTbDatasource();
        queryCmsTbDatasource.setDatasourceCode(dataSourceName);
        CmsTbDatasource datasource = cmsTbDatasourceMapper.selectOne(queryCmsTbDatasource);
        if (datasource != null) {
            dataSourceType = datasource.getDatasourceSource();
        }
        String dataSourceContent = datasource.getDatasourceContent();
        DataSourceHandler dataSourceHandlerBean = applicationContext.getBean(dataSourceType + "DataHandler", DataSourceHandler.class);
        if (dataSourceHandlerBean == null) {
            throw new RuntimeException("未找到对应处理器" + dataSourceType);
        }
        return dataSourceHandlerBean.doHandle(dataSourceName, dataSourceContent, params);
    }

}
