package site.lanmushan.resource.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import site.lanmushan.framework.dto.Message;
import site.lanmushan.framework.file.LocalResourceService;
import site.lanmushan.framework.util.ServletUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author Administrator
 */
@Slf4j
@RestController
@RequestMapping("/resource")
public class ResourceController {
    @Autowired
    LocalResourceService localResourceService;
    @ResponseBody
    @RequestMapping("/uploadFile")
    @CrossOrigin
    public Message uploadFile(@RequestParam("uploadFormFile") MultipartFile uploadFormFile, String uploadPath, HttpServletRequest request) throws IOException {
        Message msg = new Message();
        try {
            String filePathName = localResourceService.getRootPath();
            if (uploadPath != null) {
                filePathName = filePathName.concat(new File(uploadPath).getPath());
            }
            File dir = new File(filePathName);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            String fileName = uploadFormFile.getOriginalFilename();
            log.info("开始文件上传 路径:{} 文件名:{}", filePathName, fileName);
            String fileFullName = filePathName + File.separator + fileName;
            File file = new File(fileFullName);
            if (file.exists()) {
                fileFullName = filePathName + File.separator + System.currentTimeMillis() + fileName;
                file = new File(fileFullName);
            }
            if (!uploadFormFile.isEmpty()) {
                file.createNewFile();
                byte[] bytes = uploadFormFile.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file));
                stream.write(bytes);
                stream.close();
                msg.success("文件上传成功");
                msg.setRow(ServletUtil.getBasePath(request)+this.convertResponsePath(fileFullName));
                log.info("访问URL:{}", msg.getRow());
                log.info("文件上传成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            msg.error("文件上传失败！");

        }
        return msg;
    }

    private String convertResponsePath(String fileFullName) {
        String resultUri = "/";
        log.info("webroot:{}", localResourceService.getWebAppPath());
        if (fileFullName.startsWith(localResourceService.getTemplatesPath())) {
            return resultUri;
        } else if (fileFullName.startsWith(localResourceService.getWebAppPath())) {
            resultUri = fileFullName.replace(localResourceService.getWebAppPath(), "");
            resultUri = resultUri.replaceAll("\\\\", "/");
        }
        return resultUri;
    }


    @GetMapping("/selectList")
    public Message selectList(String path) {
        return Message.getInstance().setRows(localResourceService.getRootFiles(path));
    }

    @RequestMapping("/readContent")
    public Message readContent(String path) {
        String result = new String(localResourceService.readFile(path), StandardCharsets.UTF_8);
        return Message.getInstance().setRow(result);
    }
    @RequestMapping("/selectTemplates")
    public Message selectTemplates() {
        return Message.getInstance().setRows(localResourceService.getTemplatesFiles());
    }


    public static void main(String[] args) {
        String uri = "\\images\\test.jpg";
        File file = new File(uri);
        System.out.println(file.getPath());
    }
}
