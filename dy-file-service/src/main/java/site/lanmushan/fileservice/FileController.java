package site.lanmushan.fileservice;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import site.lanmushan.framework.dto.Message;
import site.lanmushan.framework.file.LocalResourceUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * @author Administrator
 */
@Controller
@RequestMapping("/apis/file")
public class FileController {
    private Logger log = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @RequestMapping("/uploadImg")
    public Message uploadFile(@RequestParam("uploadFormFile") MultipartFile uploadFormFile, HttpServletRequest request) throws IOException {
        Message msg = new Message();
        try {
            log.info("开始图片上传");
            String strName[] = {".png", ".jpg", ".jpge", ".ico"};
            File path = new File("");
            File upload = new File(path.getCanonicalPath(), LocalResourceUtils.getLocalResourceFilePath() + "/");
            if (!upload.exists()) {
                upload.mkdirs();
            }
            String filePath = upload.getAbsolutePath();
            String fileName = "/";
            String upName = null;
            if (!uploadFormFile.isEmpty()) {
                upName = uploadFormFile.getOriginalFilename();
                if (upName.indexOf(".") > -1) {
                    upName = upName.substring(upName.lastIndexOf('.'));
                } else {

                }
                fileName += UUID.randomUUID() + upName;
                byte[] bytes = uploadFormFile.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath + fileName)));
                stream.write(bytes);
                stream.close();
                msg.success("图片上传成功");
                msg.setRow("/" + LocalResourceUtils.getLocalResourceFilePath() + fileName);
                log.info("图片上传成功");
                return msg;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            msg.error("图片上传失败！");
        }
        msg.error("文件上传失败！");
        return msg;
    }
}
