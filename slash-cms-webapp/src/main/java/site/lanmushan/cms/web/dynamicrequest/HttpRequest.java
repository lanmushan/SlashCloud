package site.lanmushan.cms.web.dynamicrequest;

import lombok.Data;

@Data
public class HttpRequest {
    private String requestUrl;
    private String requestMethod;
}
