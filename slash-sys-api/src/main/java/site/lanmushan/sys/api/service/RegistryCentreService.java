package site.lanmushan.sys.api.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import site.lanmushan.framework.cloud.ConditionalFeignSelfDisable;
import site.lanmushan.sys.api.vo.AppInfo;

import java.util.List;

/**
 * 注册中心相关服务，最好是分离出去
 * @author Administrator
 */
@FeignClient(value = "slash-sys-app")
@Component
@ConditionalFeignSelfDisable
public interface RegistryCentreService {
    /**
     * 查询所有服务列表
     * @return
     */
    @GetMapping("/sysRegistryCentre/selectAllAppService")
    List<AppInfo> selectAllAppService();
}
