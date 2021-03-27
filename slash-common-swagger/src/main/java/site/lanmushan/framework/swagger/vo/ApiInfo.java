package site.lanmushan.framework.swagger.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author cayden
 * @date 2020/7/6 0:11
 */
@Data
public class ApiInfo {
    @ApiModelProperty("api组")
    private String apiGroupName;
    @ApiModelProperty("api名称")
    private String apiName;

    @ApiModelProperty("api链接")
    private String apiUrl;
}
