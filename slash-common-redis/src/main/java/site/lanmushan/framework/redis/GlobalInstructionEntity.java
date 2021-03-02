package site.lanmushan.framework.redis;

import lombok.Data;

/**
 * @author Administrator
 */
@Data
public class GlobalInstructionEntity {
    private String cmd;
    private String type;
    private String data;
}
