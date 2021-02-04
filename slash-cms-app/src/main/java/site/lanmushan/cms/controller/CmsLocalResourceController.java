package site.lanmushan.cms.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import site.lanmushan.framework.dto.Message;
import site.lanmushan.framework.file.LocalResourceService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author Administrator
 */
@RestController
@RequestMapping("/cmsLocalResource")
@Slf4j
public class CmsLocalResourceController {
    @Autowired
    LocalResourceService localResourceService;

    @RequestMapping("/selectList")
    public Message selectList(String path) {
        return Message.getInstance().setRows(localResourceService.getFiles(path));
    }

    @RequestMapping("/readContent")
    public Message readContent(String path) {
        String result = new String(localResourceService.readFile(path), StandardCharsets.UTF_8);
        return Message.getInstance().setRow(result);
    }

    @ResponseBody
    @RequestMapping("/uploadFile")
    public Message uploadFile(@RequestParam("uploadFormFile") MultipartFile uploadFormFile, String uploadPath) throws IOException {

        return Message.getInstance();
    }
}
