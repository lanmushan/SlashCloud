package site.lanmushan.sys.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.lanmushan.framework.dto.Message;
import site.lanmushan.sys.api.bo.BoSysTbDictGroup;
import site.lanmushan.sys.api.entity.SysTbDictGroup;
import site.lanmushan.sys.mapper.SysTbDictGroupMapper;
import site.lanmushan.sys.service.SysTbDictGroupService;

import javax.validation.Valid;

/**
 * (SysTbDictGroup)表控制层
 *
 * @author dy
 * @since 2020-06-14 21:15:06
 */
@RestController
@RequestMapping("/sysTbDictGroup")
@Slf4j
public class SysTbDictGroupController {
    @Autowired
    private SysTbDictGroupMapper sysTbDictGroupMapper;
    @Autowired
    private SysTbDictGroupService sysTbDictGroupService;

    @GetMapping("/selectById")
    public Message selectById(@RequestParam("id") Long id) {
        Message msg = new Message();
        SysTbDictGroup sysTbDictGroup = sysTbDictGroupMapper.selectByPrimaryKey(id);
        msg.setRow(sysTbDictGroup);
        return msg;
    }

    @PostMapping("/add")
    public Message add(@RequestBody @Valid BoSysTbDictGroup obj) {
        Message msg = new Message();
        sysTbDictGroupService.insertService(obj);
        msg.setRow(obj).success("添加成功");
        return msg;
    }

    /**
     * @param obj
     * @return
     */
    @PostMapping("/update")
    public Message update(@RequestBody @Valid BoSysTbDictGroup obj) {
        Message msg = new Message();
        sysTbDictGroupService.updateService(obj);
        msg.setRow(obj).success("更新成功");
        return msg;
    }
    /**
     * @param ids
     * @return
     */
    /**
     * @param obj
     * @return
     */
    @PostMapping("/delete")
    public Message delete(@RequestBody BoSysTbDictGroup obj) {
        Message msg = new Message();
        sysTbDictGroupService.deleteServiceByIds(obj.getIds());
        msg.success("删除成功");
        return msg;
    }

}