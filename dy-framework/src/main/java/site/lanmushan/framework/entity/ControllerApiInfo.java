package site.lanmushan.framework.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author cayden
 * @date 2020/7/6 0:11
 */
@Data
public class ControllerApiInfo {

    @ApiModelProperty("api名称")
    private String apiName;

    @ApiModelProperty("api链接")
    private String apiUrl;
}
