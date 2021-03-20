package site.lanmushan.sys.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import site.lanmushan.sys.api.vo.AppInfo;
import site.lanmushan.sys.api.service.RegistryCentreService;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * 单机
 *
 * @author Administrator
 */
@Service
@ConditionalOnProperty(value = "slash.cloud", havingValue = "false", matchIfMissing = true)
@Slf4j
public class RegistryCentreServiceImpl implements RegistryCentreService {
    @Value("${spring.application.name}")
    private String currentServiceName;
    @Value("${server.port}")
    private String serverPort;

    @Override
    public List<AppInfo> selectAllAppService() {
        log.info("获取所有的服务名称");
        List<AppInfo> resultList = new ArrayList<>(1);
        AppInfo appInfo = new AppInfo();

        appInfo.setAppName(currentServiceName);
        appInfo.setAppHost(getIpAddress());
        appInfo.setAppPort(serverPort);
        resultList.add(appInfo);
        return resultList;
    }

    private String getIpAddress(){
        try {
            InetAddress addr = InetAddress.getLocalHost();
         return    addr.getHostAddress();
        }catch (Exception e){
          log.error(e.getMessage(),e);
        }
        return "";
    }
}
