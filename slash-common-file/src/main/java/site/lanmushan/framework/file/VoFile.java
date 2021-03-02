package site.lanmushan.framework.file;

import lombok.Data;

@Data
public class VoFile {
    private String fileName;
    private String filePath;
    private Long fileSize;
    private String fileType;
    private String fileUrl;
}
