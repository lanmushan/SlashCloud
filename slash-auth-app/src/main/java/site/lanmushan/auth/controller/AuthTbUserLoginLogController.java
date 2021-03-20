package site.lanmushan.auth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import site.lanmushan.auth.api.bo.BoAuthTbUserLoginLog;
import site.lanmushan.auth.api.entity.AuthTbUserLoginLog;
import site.lanmushan.auth.mapper.AuthTbUserLoginLogMapper;
import site.lanmushan.auth.api.service.AuthTbUserLoginLogService;
import site.lanmushan.framework.authorization.CurrentUserUtil;
import site.lanmushan.framework.dto.DHashMap;
import site.lanmushan.framework.dto.Message;
import site.lanmushan.framework.dto.QueryInfo;
import site.lanmushan.framework.query.annotations.RequestQueryInfo;
import site.lanmushan.framework.query.controller.BaseController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 用户登录记录(AuthTbUserLoginLog)表控制层
 *
 * @author dy
 * @since 2020-06-15 22:13:48
 */
@RestController
@RequestMapping("/authTbUserLoginLog")
@Slf4j
public class AuthTbUserLoginLogController extends BaseController {
    @Autowired
    private AuthTbUserLoginLogMapper authTbUserLoginLogMapper;
    @Autowired
    private AuthTbUserLoginLogService authTbUserLoginLogService;
    @Autowired
    RedisTemplate<String,String> redisTemplate;
    @GetMapping("/selectById")
    public Message selectById(@RequestParam("id") Long id) {
        Message msg = new Message();
        AuthTbUserLoginLog authTbUserLoginLog = authTbUserLoginLogMapper.selectByPrimaryKey(id);
        msg.setRow(authTbUserLoginLog);
        return msg;
    }

    @PostMapping("/add")
    public Message add(@RequestBody @Valid BoAuthTbUserLoginLog obj) {
        Message msg = new Message();
        authTbUserLoginLogService.insertService(obj);
        msg.setRow(obj).success("添加成功");
        return msg;
    }

    /**
     * @param obj
     * @return
     */
    @PostMapping("/update")
    public Message update(@RequestBody @Valid BoAuthTbUserLoginLog obj) {
        Message msg = new Message();
        authTbUserLoginLogService.updateService(obj);
        msg.setRow(obj).success("更新成功");
        return msg;
    }

    /**
     * @param ids
     * @return
     */
    @PostMapping("/delete")
    public Message delete(@RequestParam List<Long> ids) {
        Message msg = new Message();
        authTbUserLoginLogService.deleteServiceByIds(ids);
        msg.success("删除成功");
        return msg;
    }
    /**
     * 查询在线用户
     * FIXME 用户量多的使用scan命令
     * @param queryInfo
     * @return
     */
    @GetMapping("/selectOnlineUserList")
    public Message selectOnlineUserList( String searchKey){
        if(searchKey!=null)
        {
            searchKey=searchKey+"*";
        }else {
            searchKey="*";
        }
        Message msg=new Message();
        Set<String> valueSet = redisTemplate.keys(CurrentUserUtil.REDIS_ONLINE_USER_KEY_PREFIX + searchKey);
        if(valueSet!=null&&valueSet.size()>0)
        {
            msg.setTotal(valueSet.size());
            msg.setCurrentPage(getCurrentPage());
            msg.setPageSize(getPageSize());
            valueSet=valueSet.stream().skip((getCurrentPage()-1)*getPageSize()).limit(getPageSize()).collect(Collectors.toSet());
            List<DHashMap> resultList=new ArrayList<>(valueSet.size());
            valueSet.forEach(it->{
                DHashMap dHashMap= authTbUserLoginLogMapper.selectOnlineUserInfoByAccount(it.split("\\|")[1]);
                resultList.add(dHashMap);
            });
            msg.setRows(resultList);
        }else {
            msg.setRows(null);
        }

        return msg;
    }
}