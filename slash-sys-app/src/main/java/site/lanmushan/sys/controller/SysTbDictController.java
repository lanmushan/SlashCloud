package site.lanmushan.sys.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.lanmushan.framework.dto.Message;
import site.lanmushan.sys.api.bo.BoSysTbDict;
import site.lanmushan.sys.api.entity.SysTbDict;
import site.lanmushan.sys.mapper.SysTbDictMapper;
import site.lanmushan.sys.api.service.SysTbDictService;

import javax.validation.Valid;

/**
 * (SysTbDict)表控制层
 *
 * @author dy
 * @since 2020-06-14 21:15:04
 */
@RestController
@RequestMapping("/sysTbDict")
@Slf4j
public class SysTbDictController {
    @Autowired
    private SysTbDictMapper sysTbDictMapper;
    @Autowired
    private SysTbDictService sysTbDictService;

    @GetMapping("/selectById")
    public Message selectById(@RequestParam("id") Long id) {
        Message msg = new Message();
        SysTbDict sysTbDict = sysTbDictMapper.selectByPrimaryKey(id);
        msg.setRow(sysTbDict);
        return msg;
    }

    @PostMapping("/add")
    public Message add(@RequestBody @Valid BoSysTbDict obj) {
        Message msg = new Message();
        sysTbDictService.insertService(obj);
        msg.setRow(obj).success("添加成功");
        return msg;
    }

    /**
     * @param obj
     * @return
     */
    @PostMapping("/update")
    public Message update(@RequestBody @Valid BoSysTbDict obj) {
        Message msg = new Message();
        sysTbDictService.updateService(obj);
        msg.setRow(obj).success("更新成功");
        return msg;
    }

    /**
     * @param obj
     * @return
     */
    @PostMapping("/delete")
    public Message delete(BoSysTbDict obj) {
        Message msg = new Message();
        sysTbDictService.deleteServiceByIds(obj.getIds());
        msg.success("删除成功");
        return msg;
    }

}