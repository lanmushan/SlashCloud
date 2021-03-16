package site.lanmushan.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import site.lanmushan.auth.api.service.DataBaseManageService;
import site.lanmushan.framework.dto.DHashMap;
import site.lanmushan.framework.query.mapper.DataScopeMapper;

import java.util.List;

/**
 * @author Administrator
 */
@ConditionalOnProperty(value = "slash.cloud", havingValue = "false", matchIfMissing = true)
@Service
public class DataBaseManageServiceImpl implements DataBaseManageService {
    @Autowired
    DataScopeMapper dataScopeMapper;
    @Value("${spring.application.name}")
    private String currentAppName;

    @Override
    public List<DHashMap> selectAllTableList(String appName) {
        List<DHashMap> list = dataScopeMapper.selectAllTable();
        list.forEach(it -> {
            it.put("appName", currentAppName);
        });
        return list;
    }

    @Override
    public List<DHashMap> selectTableAllTableFieldList(String appName,String tableName) {
        return dataScopeMapper.selectFieldByTableName(tableName);
    }
}
