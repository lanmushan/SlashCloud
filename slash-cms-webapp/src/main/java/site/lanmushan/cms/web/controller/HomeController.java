package site.lanmushan.cms.web.controller;

import io.swagger.models.Model;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@Slf4j
public class HomeController {
    /**
     * 全局处理器
     *
     * @return
     */
    public String index(Model model) {
        log.info("为啥不行");
        return "index.html";
    }
    @RequestMapping("/classes/{classId}.html")
    public String home(@PathVariable String classId) {
        System.out.println("xxxxxxxxxxxxx");
        return "index.htm";
    }


}
