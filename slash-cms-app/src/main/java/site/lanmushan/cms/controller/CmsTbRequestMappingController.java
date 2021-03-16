package site.lanmushan.cms.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.*;
import site.lanmushan.cms.api.bo.BoCmsTbRequestMapping;
import site.lanmushan.cms.api.entity.CmsTbRequestMapping;
import site.lanmushan.cms.mapper.CmsTbRequestMappingMapper;
import site.lanmushan.cms.api.service.CmsTbRequestMappingService;
import site.lanmushan.framework.constant.GlobalInstructionConstant;
import site.lanmushan.framework.dto.Message;
import site.lanmushan.framework.redis.GlobalInstructionEntity;
import site.lanmushan.framework.redis.publish.GlobalInstructionPublish;

import javax.validation.Valid;

/**
 * (CmsTbRequestMapping)表控制层
 *
 * @author dy
 * @since 2021-02-07 20:46:54
 */
@RestController
@RequestMapping("/cmsTbRequestMapping")
@Slf4j
public class CmsTbRequestMappingController {
    @Autowired
    private CmsTbRequestMappingMapper cmsTbRequestMappingMapper;
    @Autowired
    private CmsTbRequestMappingService cmsTbRequestMappingService;
    @Autowired
    ThreadPoolTaskExecutor threadPoolTaskExecutor;
    @Autowired
    GlobalInstructionPublish globalInstructionPublish;

    @GetMapping("/selectById")
    public Message selectById(@RequestParam("id") Long id) {
        Message msg = new Message();
        CmsTbRequestMapping cmsTbRequestMapping = cmsTbRequestMappingMapper.selectByPrimaryKey(id);
        msg.setRow(cmsTbRequestMapping);
        return msg;
    }

    @PostMapping("/add")
    public Message add(@RequestBody @Valid BoCmsTbRequestMapping obj) {
        Message msg = new Message();
        cmsTbRequestMappingService.insertService(obj);
        msg.setRow(obj).success("添加成功");
        doUpdatePublishCmd();
        return msg;
    }

    /**
     * @param obj
     * @return
     */
    @PostMapping("/update")
    public Message update(@RequestBody @Valid BoCmsTbRequestMapping obj) {
        Message msg = new Message();
        cmsTbRequestMappingService.updateService(obj);
        doUpdatePublishCmd();
        msg.setRow(obj).success("更新成功");
        return msg;
    }

    /**
     * @param ids
     * @return
     */
    @PostMapping("/delete")
    public Message delete(@RequestBody BoCmsTbRequestMapping obj) {
        Message msg = new Message();
        cmsTbRequestMappingService.deleteServiceByIds(obj.getIds());
        doUpdatePublishCmd();
        msg.success("删除成功");
        return msg;
    }

    /**
     * 处理业务，简单做一下延迟
     */
    private void doUpdatePublishCmd() {
        threadPoolTaskExecutor.execute(() -> {
            try {
                Thread.sleep(1000);
                GlobalInstructionEntity globalInstructionEntity = new GlobalInstructionEntity();
                globalInstructionEntity.setCmd(GlobalInstructionConstant.UPDATE_REQUEST_MAPPING);
                globalInstructionPublish.publish(globalInstructionEntity);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}