package site.lanmushan.resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import site.lanmushan.framework.dto.Message;
import site.lanmushan.framework.file.LocalResourceService;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Administrator
 */
@SpringBootApplication
public class SlashResourceAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(SlashResourceAppApplication.class, args);
    }

}
