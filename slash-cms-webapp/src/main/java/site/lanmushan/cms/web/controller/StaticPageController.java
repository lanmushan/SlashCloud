package site.lanmushan.cms.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import site.lanmushan.cms.web.service.DataSourceHandlerService;

/**
 * @author Administrator
 */
@Slf4j
@Controller
public class StaticPageController {
    @Autowired
    DataSourceHandlerService dataSourceHandlerService;
    /**
     * 请求
     *
     * @param modelMap
     * @return
     */
    @RequestMapping("/")
    public String handle(ModelMap modelMap) {
        modelMap.addAttribute("navList", dataSourceHandlerService.loadContentCategory());
        modelMap.addAttribute("title", "sdafasdfdasfassdafasd");

        log.info("进入处理器");
        return "index.ftl";
    }
}
