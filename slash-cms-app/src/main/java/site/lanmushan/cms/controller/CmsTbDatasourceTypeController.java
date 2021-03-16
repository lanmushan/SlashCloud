package site.lanmushan.cms.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.lanmushan.cms.api.bo.BoCmsTbDatasourceType;
import site.lanmushan.cms.api.entity.CmsTbDatasourceType;
import site.lanmushan.cms.mapper.CmsTbDatasourceTypeMapper;
import site.lanmushan.cms.api.service.CmsTbDatasourceTypeService;
import site.lanmushan.framework.dto.Message;

import javax.validation.Valid;

/**
 * (CmsTbDatasourceType)表控制层
 *
 * @author dy
 * @since 2021-01-30 15:00:39
 */
@RestController
@RequestMapping("/cmsTbDatasourceType")
@Slf4j
public class CmsTbDatasourceTypeController {
    @Autowired
    private CmsTbDatasourceTypeMapper cmsTbDatasourceTypeMapper;
    @Autowired
    private CmsTbDatasourceTypeService cmsTbDatasourceTypeService;

    @GetMapping("/selectById")
    public Message selectById(@RequestParam("id") Long id) {
        Message msg = new Message();
        CmsTbDatasourceType cmsTbDatasourceType = cmsTbDatasourceTypeMapper.selectByPrimaryKey(id);
        msg.setRow(cmsTbDatasourceType);
        return msg;
    }

    @PostMapping("/add")
    public Message add(@RequestBody @Valid BoCmsTbDatasourceType obj) {
        Message msg = new Message();
        cmsTbDatasourceTypeService.insertService(obj);
        msg.setRow(obj).success("添加成功");
        return msg;
    }

    /**
     * @param obj
     * @return
     */
    @PostMapping("/update")
    public Message update(@RequestBody @Valid BoCmsTbDatasourceType obj) {
        Message msg = new Message();
        cmsTbDatasourceTypeService.updateService(obj);
        msg.setRow(obj).success("更新成功");
        return msg;
    }

    /**
     * @param ids
     * @return
     */
    @PostMapping("/delete")
    public Message delete(@RequestBody BoCmsTbDatasourceType obj) {
        Message msg = new Message();
        cmsTbDatasourceTypeService.deleteServiceByIds(obj.getIds());
        msg.success("删除成功");
        return msg;
    }

}