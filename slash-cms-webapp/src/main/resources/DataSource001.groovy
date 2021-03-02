import org.springframework.beans.factory.annotation.Autowired
import site.lanmushan.cms.api.entity.CmsTbContent
import site.lanmushan.cms.mapper.CmsTbContentMapper

class DataSource001 {
    @Autowired
    CmsTbContentMapper cmsTbContentMapper;

    def Object doHandle(String dataSourceName, String content, Map<String, Object> params) {
        List<CmsTbContent> list = cmsTbContentMapper.selectAll();
        System.out.println("执行数据源脚本" + list.size());
        return list;
    }
}
