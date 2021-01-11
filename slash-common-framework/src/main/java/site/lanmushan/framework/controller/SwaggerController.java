package site.lanmushan.framework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author cayden
 * @date 2020/8/13 22:24
 */
@Controller
@RequestMapping("/authService")
public class SwaggerController {

    @RequestMapping(value = "/getSwagger")
    public String getSwagger(){
        return "redirect:/doc.html";
    }
}
