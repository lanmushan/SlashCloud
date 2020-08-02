package site.lanmushan.authservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import site.lanmushan.authservice.rpcservice.SysTbDictGroup;
import site.lanmushan.authservice.rpcservice.SysTbDictService;
import site.lanmushan.framework.annotations.RequestQueryInfo;
import site.lanmushan.framework.dto.Message;
import site.lanmushan.framework.dto.QueryInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author dy
 * @Date 2020/6/21 16:31
 * @Version 1.0
 */
@RestController
@Slf4j
public class TestController {
   @RequestMapping("test")
    public Message test(HttpSession session){
       String test = (String) session.getAttribute("test");
       Message msg= Message.getInstance().setRow(test);
       session.setAttribute("test",Math.random()+"");
       return msg;
   }

//
//    @GetMapping("/")
//    public Message index(@RequestQueryInfo QueryInfo queryInfo) {
//        log.info(queryInfo.toString());
//        List list = sysTbDictService.selectList(queryInfo);
//        Message msg = new Message();
//        msg.setRows(list);
//        return msg;
//
//    }
//
//    @GetMapping("test")
//    public Message test(@RequestQueryInfo QueryInfo queryInfo) {
//        log.info(queryInfo.toString());
//        List list = sysTbDictService.selectList(queryInfo);
//        Message msg = new Message();
//        msg.setRows(list);
//        return msg;
//
//    }
//
//    @GetMapping("test2")
//    public Message test(@RequestParam Long id) {
//
//        SysTbDictGroup sysTbDictGroup = sysTbDictService.selectById(id);
//        Message msg = new Message();
//        msg.setRow(sysTbDictGroup);
//        return msg;
//
//    }
}
