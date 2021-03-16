package site.lanmushan.sys.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.lanmushan.framework.dto.Message;
import site.lanmushan.framework.query.controller.BaseController;
import site.lanmushan.sys.api.service.RegistryCentreService;

/**
 * @author Administrator
 */
@RequestMapping("/sysRegistryCentre")
@RestController
@Slf4j
public class RegistryCentreController extends BaseController {
    @Autowired
    RegistryCentreService registryCentreService;

    @GetMapping("/selectAllAppService")
    public Message selectAllAppService() {
        return Message.getInstance().setRows(registryCentreService.selectAllAppService());
    }
}
