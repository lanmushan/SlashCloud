package site.lanmushan.cms.web.datasource.handler;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.lanmushan.cms.api.entity.CmsTbCategory;
import site.lanmushan.cms.api.entity.CmsTbContent;
import site.lanmushan.cms.mapper.CmsTbContentMapper;
import site.lanmushan.cms.api.service.CmsTbCategoryService;
import site.lanmushan.cms.web.datasource.DataSourceHandler;
import site.lanmushan.framework.dto.QueryInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
@Service(value = "InnerDataHandler")
public class InnerDataHandler implements DataSourceHandler {
    @Autowired
    CmsTbCategoryService categoryService;
    @Autowired
    CmsTbContentMapper cmsTbContentMapper;

    @Override
    public Object doHandle(String dataSourceName, String content, Map<String, Object> params) {
        if ("navList".equals(dataSourceName)) {
            List<CmsTbCategory> list = categoryService.selectTreeList(new QueryInfo());
            return list.get(0).getChildren();
        }
        if ("hotList".equals(dataSourceName)) {
            List<CmsTbCategory> list = categoryService.selectTreeList(new QueryInfo());
            List resultList = list.get(0).getChildren();
            List<JSONObject> resultJsonList = new ArrayList<>();
            for (int i = 0; i < resultList.size(); i++) {
                CmsTbCategory cmsTbCategory = (CmsTbCategory) resultList.get(i);
                JSONObject json = (JSONObject) JSONObject.toJSON(cmsTbCategory);
                cmsTbCategory.getCategoryCode();
                CmsTbContent query = new CmsTbContent();
                query.setFkCategoryCode(cmsTbCategory.getCategoryCode());
                query.setContentDisplay(0);
                PageHelper.orderBy("content_hit desc");
                PageHelper.startPage(1, 2);
                List<CmsTbContent> contentList = cmsTbContentMapper.select(query);
                json.put("articleList", contentList);
                resultJsonList.add(json);
            }
            return resultJsonList;
        }
        return null;
    }
}
