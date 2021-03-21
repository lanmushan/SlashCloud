package site.lanmushan.sys.service.cloudimpl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import site.lanmushan.framework.http.api.HttpClientService;

import site.lanmushan.sys.api.vo.AppInfo;
import site.lanmushan.sys.api.service.RegistryCentreService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
@Service
@ConditionalOnProperty(value = "slash.cloud",havingValue = "true",matchIfMissing = false)
public class NocosRegistryCentreServiceImpl implements RegistryCentreService {
    @Value("${spring.cloud.nacos.discovery.server-addr}")
    private String nacosServerAddr;

    @Value("${spring.application.name}")
    private String currentServiceName;
    @Autowired
    HttpClientService httpClientService;

    @Override
    public List<AppInfo> selectAllAppService() {
        String url = "http://" + nacosServerAddr + "/nacos/v1/ns/catalog/services";
        JSONObject queryJson = new JSONObject();
        queryJson.put("hasIpCount", Boolean.TRUE);
        queryJson.put("withInstances", Boolean.FALSE);
        queryJson.put("pageNo", 1);
        queryJson.put("pageSize", 500);
        JSONObject resultJson = (JSONObject) httpClientService.doGet(url, queryJson, null, JSONObject.class);
        JSONArray jsonArray=resultJson.getJSONArray("serviceList");
        List<AppInfo> list=new ArrayList<>(jsonArray.size());
        for (Object obj:jsonArray) {
                JSONObject json= (JSONObject) obj;
                AppInfo appInfo=new AppInfo();
                appInfo.setAppName(json.getString("name"));
                list.add(appInfo);
        }
        return list;
    }
}
