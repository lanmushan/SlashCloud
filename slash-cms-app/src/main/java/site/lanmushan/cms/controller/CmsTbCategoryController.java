package site.lanmushan.cms.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.lanmushan.cms.api.bo.BoCmsTbCategory;
import site.lanmushan.cms.api.entity.CmsTbCategory;
import site.lanmushan.cms.api.service.CmsTbCategoryService;
import site.lanmushan.cms.mapper.CmsTbCategoryMapper;
import site.lanmushan.framework.dto.Message;
import site.lanmushan.framework.dto.QueryInfo;
import site.lanmushan.framework.query.annotations.RequestQueryInfo;
import site.lanmushan.framework.query.util.TreeUtil;

import javax.validation.Valid;
import java.util.List;

/**
 * (CmsTbCategory)表控制层
 *
 * @author dy
 * @since 2021-01-30 15:00:39
 */
@RestController
@RequestMapping("/cmsTbCategory")
@Slf4j
public class CmsTbCategoryController {
    @Autowired
    private CmsTbCategoryMapper cmsTbCategoryMapper;
    @Autowired
    private CmsTbCategoryService cmsTbCategoryService;

    @GetMapping("/selectById")
    public Message selectById(@RequestParam("id") Long id) {
        Message msg = new Message();
        CmsTbCategory cmsTbCategory = cmsTbCategoryMapper.selectByPrimaryKey(id);
        msg.setRow(cmsTbCategory);
        return msg;
    }

    @PostMapping("/add")
    public Message add(@RequestBody @Valid BoCmsTbCategory obj) {
        Message msg = new Message();
        cmsTbCategoryService.insertService(obj);
        msg.setRow(obj).success("添加成功");
        return msg;
    }

    /**
     * @param obj
     * @return
     */
    @PostMapping("/update")
    public Message update(@RequestBody @Valid BoCmsTbCategory obj) {
        Message msg = new Message();
        cmsTbCategoryService.updateService(obj);
        msg.setRow(obj).success("更新成功");
        return msg;
    }

    /**
     * @param ids
     * @return
     */
    @PostMapping("/delete")
    public Message delete(@RequestBody BoCmsTbCategory obj) {
        Message msg = new Message();
        cmsTbCategoryService.deleteServiceByIds(obj.getIds());
        msg.success("删除成功");
        return msg;
    }

    @GetMapping("/selectTreeList")
    public Message selectTreeList(@RequestQueryInfo QueryInfo queryInfo) {
        List typeList = cmsTbCategoryMapper.selectList(queryInfo);
        return Message.getInstance().setRows(TreeUtil.listToTree(typeList));
    }
}