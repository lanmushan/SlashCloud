package site.lanmushan.sys.api.service;

import site.lanmushan.sys.api.vo.AppInfo;

import java.util.List;

/**
 * 注册中心相关服务，最好是分离出去
 * @author Administrator
 */
public interface RegistryCentreService {

    List<AppInfo> selectAllAppService();
}
