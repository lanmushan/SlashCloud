package site.lanmushan.auth.service.impl;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import site.lanmushan.auth.api.service.DataBaseManageService;
import site.lanmushan.framework.dto.DHashMap;

import java.util.List;

/**
 * @author Administrator
 */
@Service
@ConditionalOnProperty(value = "slash.cloud",havingValue = "true",matchIfMissing = false)
public class DataBaseManageServiceCloudImpl implements DataBaseManageService {

    @Override
    public List<DHashMap> selectAllTableList(String appName) {
        return null;
    }

    @Override
    public List<DHashMap> selectTableAllTableFieldList(String appName,String tableName) {
        return null;
    }
}
