package site.lanmushan.cms.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.lanmushan.cms.api.bo.BoCmsTbRequestMappingDatasource;
import site.lanmushan.cms.api.entity.CmsTbRequestMappingDatasource;
import site.lanmushan.cms.mapper.CmsTbRequestMappingDatasourceMapper;
import site.lanmushan.cms.service.CmsTbRequestMappingDatasourceService;
import site.lanmushan.framework.dto.Message;

import javax.validation.Valid;

/**
 * (CmsTbRequestMappingDatasource)表控制层
 *
 * @author dy
 * @since 2021-02-08 20:38:17
 */
@RestController
@RequestMapping("/cmsTbRequestMappingDatasource")
@Slf4j
public class CmsTbRequestMappingDatasourceController {
    @Autowired
    private CmsTbRequestMappingDatasourceMapper cmsTbRequestMappingDatasourceMapper;
    @Autowired
    private CmsTbRequestMappingDatasourceService cmsTbRequestMappingDatasourceService;

    @GetMapping("/selectById")
    public Message selectById(@RequestParam("id") Long id) {
        Message msg = new Message();
        CmsTbRequestMappingDatasource cmsTbRequestMappingDatasource = cmsTbRequestMappingDatasourceMapper.selectByPrimaryKey(id);
        msg.setRow(cmsTbRequestMappingDatasource);
        return msg;
    }

    @PostMapping("/add")
    public Message add(@RequestBody @Valid BoCmsTbRequestMappingDatasource obj) {
        Message msg = new Message();
        cmsTbRequestMappingDatasourceService.insertService(obj);
        msg.setRow(obj).success("添加成功");
        return msg;
    }

    /**
     * @param obj
     * @return
     */
    @PostMapping("/update")
    public Message update(@RequestBody @Valid BoCmsTbRequestMappingDatasource obj) {
        Message msg = new Message();
        cmsTbRequestMappingDatasourceService.updateService(obj);
        msg.setRow(obj).success("更新成功");
        return msg;
    }

    /**
     * @param ids
     * @return
     */
    @PostMapping("/delete")
    public Message delete(@RequestBody BoCmsTbRequestMappingDatasource obj) {
        Message msg = new Message();
        cmsTbRequestMappingDatasourceService.deleteServiceByIds(obj.getIds());
        msg.success("删除成功");
        return msg;
    }

}