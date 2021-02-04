package site.lanmushan.cms.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.lanmushan.cms.api.entity.CmsTbCategory;
import site.lanmushan.cms.service.CmsTbCategoryService;
import site.lanmushan.cms.web.service.DataSourceHandlerService;
import site.lanmushan.framework.dto.QueryInfo;

import java.util.List;
@Service
public class DataSourceHandlerServiceImpl implements DataSourceHandlerService {
    @Autowired
    CmsTbCategoryService cmsTbCategoryService;

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
}
