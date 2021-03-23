package site.lanmushan.auth.service.cloudimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import site.lanmushan.auth.api.service.DataBaseManageService;
import site.lanmushan.framework.constant.annotation.ConditionalCloud;
import site.lanmushan.framework.constant.CurrentAppConstant;
import site.lanmushan.framework.dto.DHashMap;
import site.lanmushan.framework.query.mapper.DataScopeMapper;

import java.util.List;

/**
 * @author Administrator
 */
@Service
@ConditionalCloud
public class DataBaseManageServiceCloudImpl implements DataBaseManageService {
    @Autowired
    DataScopeMapper dataScopeMapper;
    @Autowired
    RestTemplate restTemplate;

    @Override
    public List<DHashMap> selectAllTableList(String appName) {
        if (CurrentAppConstant.appName.equals(appName)) {
            List<DHashMap> list = dataScopeMapper.selectAllTable();
            list.forEach(it -> {
                it.put("appName", appName);
            });
            return list;
        } else {
            //远程调用
        }
        return null;
    }

    @Override
    public List<DHashMap> selectTableAllTableFieldList(String appName, String tableName) {
        return null;
    }
}
