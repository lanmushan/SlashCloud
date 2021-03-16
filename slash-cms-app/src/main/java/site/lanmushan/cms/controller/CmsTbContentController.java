package site.lanmushan.cms.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import site.lanmushan.cms.api.bo.BoCmsTbContent;
import site.lanmushan.cms.api.entity.CmsTbContent;
import site.lanmushan.cms.mapper.CmsTbContentMapper;
import site.lanmushan.cms.api.service.CmsTbContentService;
import site.lanmushan.framework.constant.GlobalInstructionConstant;
import site.lanmushan.framework.dto.Message;
import site.lanmushan.framework.redis.GlobalInstructionEntity;
import site.lanmushan.framework.redis.publish.GlobalInstructionPublish;

import javax.validation.Valid;

/**
 * 文章(CmsTbContent)表控制层
 *
 * @author dy
 * @since 2021-01-30 15:00:39
 */
@RestController
@RequestMapping("/cmsTbContent")
@Slf4j
public class CmsTbContentController {
    @Autowired
    private CmsTbContentMapper cmsTbContentMapper;
    @Autowired
    private CmsTbContentService cmsTbContentService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private GlobalInstructionPublish globalInstructionPublish;

    @GetMapping("/selectById")
    public Message selectById(@RequestParam("id") Long id) {
        Message msg = new Message();
        CmsTbContent cmsTbContent = cmsTbContentMapper.selectByPrimaryKey(id);
        msg.setRow(cmsTbContent);
        return msg;
    }

    @PostMapping("/add")
    public Message add(@RequestBody @Valid BoCmsTbContent obj) {
        Message msg = new Message();
        cmsTbContentService.insertService(obj);
        msg.setRow(obj).success("添加成功");
        return msg;
    }

    /**
     * @param obj
     * @return
     */
    @PostMapping("/update")
    public Message update(@RequestBody @Valid BoCmsTbContent obj) {
        Message msg = new Message();
        cmsTbContentService.updateService(obj);
        msg.setRow(obj).success("更新成功");
        return msg;
    }

    /**
     * @param ids
     * @return
     */
    @PostMapping("/delete")
    public Message delete(@RequestBody BoCmsTbContent obj) {
        Message msg = new Message();
        cmsTbContentService.deleteServiceByIds(obj.getIds());
        msg.success("删除成功");
        return msg;
    }

    @RequestMapping("/clearCache")
    public Message clearCache() {
        GlobalInstructionEntity globalInstructionEntity = new GlobalInstructionEntity();
        globalInstructionEntity.setCmd(GlobalInstructionConstant.CLEAR_STATICS_PAGE);
        globalInstructionPublish.publish(globalInstructionEntity);
        return Message.getInstance().success("清除缓存成功");
    }
}