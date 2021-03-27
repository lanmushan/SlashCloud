package site.lanmushan.auth.api.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import site.lanmushan.auth.api.entity.AuthTbResource;
import site.lanmushan.auth.api.entity.AuthTbRole;
import site.lanmushan.framework.query.entity.BaseEntity;
import site.lanmushan.framework.query.entity.TreeNode;
import site.lanmushan.framework.uuid.SeqGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 菜单表(AuthTbResource)实体类
 *
 * @author dy
 * @since 2020-06-25 13:03:43
 */
@Table(name = "auth_tb_resource")
@Data
public class VoAuthTbResource extends AuthTbResource implements Serializable {
    private List<AuthTbRole> authTbRoleList;
}