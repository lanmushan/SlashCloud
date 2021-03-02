package site.lanmushan.cms.web.dynamicrequest;

import java.util.List;

/**
 * @author Administrator
 */
public interface DynamicRequestMappingRegisterComponent {
    /**
     * 动态注册一个
     *
     * @param request
     */
    void registerHttpRequest(HttpRequest request);

    /**
     * 动态集合
     *
     * @param requestList
     */
    void registerHttpRequestList(List<HttpRequest> requestList);

    void removeAll();
}
