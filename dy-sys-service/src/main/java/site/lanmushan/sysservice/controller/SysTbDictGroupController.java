package site.lanmushan.sysservice.controller;

import site.lanmushan.sysservice.entity.SysTbDictGroup;
import site.lanmushan.sysservice.service.SysTbDictGroupService;
import org.springframework.web.bind.annotation.*;
import site.lanmushan.framework.dto.Message;
import site.lanmushan.sysservice.bo.BoSysTbDictGroup;
import site.lanmushan.sysservice.mapper.SysTbDictGroupMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import java.util.List;
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
        SysTbDictGroup sysTbDictGroup= sysTbDictGroupMapper.selectByPrimaryKey(id);
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
    @PostMapping("/delete")
    public Message delete(@RequestParam List<Long> ids) {
        Message msg = new Message();
        sysTbDictGroupService.deleteServiceByIds(ids);
        msg.success("删除成功");
        return msg;
    }

}