package site.lanmushan.framework.authorization;

import lombok.Data;

import java.util.List;

@Data
public class Authority {
    private String url;
    private int order;
    private List<String> roleCodes;

}