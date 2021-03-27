package site.lanmushan.auth.api.bo;

import lombok.Data;
import site.lanmushan.auth.api.entity.AuthTbResource;

import javax.persistence.Transient;
import java.util.List;


/**
 * 菜单表(AuthTbResource)表服务接口
 *
 * @author dy
 * @since 2020-06-15 22:13:48
 */
@Data
public class BoAuthTbResource extends AuthTbResource {
    /**
     * 接收角色列表
     */
    @Transient
    List<String> roleCodeList;

}