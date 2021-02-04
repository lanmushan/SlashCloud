package site.lanmushan.cms.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.lanmushan.cms.api.bo.BoCmsTbDatasource;
import site.lanmushan.cms.api.entity.CmsTbDatasource;
import site.lanmushan.cms.mapper.CmsTbDatasourceMapper;
import site.lanmushan.cms.service.CmsTbDatasourceService;
import site.lanmushan.framework.dto.Message;

import javax.validation.Valid;

/**
 * (CmsTbDatasource)表控制层
 *
 * @author dy
 * @since 2021-01-30 15:00:39
 */
@RestController
@RequestMapping("/cmsTbDatasource")
@Slf4j
public class CmsTbDatasourceController {
    @Autowired
    private CmsTbDatasourceMapper cmsTbDatasourceMapper;
    @Autowired
    private CmsTbDatasourceService cmsTbDatasourceService;

    @GetMapping("/selectById")
    public Message selectById(@RequestParam("id") Long id) {
        Message msg = new Message();
        CmsTbDatasource cmsTbDatasource = cmsTbDatasourceMapper.selectByPrimaryKey(id);
        msg.setRow(cmsTbDatasource);
        return msg;
    }

    @PostMapping("/add")
    public Message add(@RequestBody @Valid BoCmsTbDatasource obj) {
        Message msg = new Message();
        cmsTbDatasourceService.insertService(obj);
        msg.setRow(obj).success("添加成功");
        return msg;
    }

    /**
     * @param obj
     * @return
     */
    @PostMapping("/update")
    public Message update(@RequestBody @Valid BoCmsTbDatasource obj) {
        Message msg = new Message();
        cmsTbDatasourceService.updateService(obj);
        msg.setRow(obj).success("更新成功");
        return msg;
    }

    /**
     * @param ids
     * @return
     */
    @DeleteMapping("/delete")
    public Message delete(@RequestBody BoCmsTbDatasource obj) {
        Message msg = new Message();
        cmsTbDatasourceService.deleteServiceByIds(obj.getIds());
        msg.success("删除成功");
        return msg;
    }

}