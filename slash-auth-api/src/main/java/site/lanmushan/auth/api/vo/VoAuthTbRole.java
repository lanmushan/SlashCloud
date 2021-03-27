package site.lanmushan.auth.api.vo;

import lombok.Data;
import site.lanmushan.auth.api.entity.AuthTbRole;

import java.util.List;

/**
 * @author Administrator
 */
@Data
public class VoAuthTbRole extends AuthTbRole {
    private List<Long> menuIdList;
}
