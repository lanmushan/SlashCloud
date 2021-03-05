package site.lanmushan.framework.redis.subscribe;

import site.lanmushan.framework.redis.GlobalInstructionEntity;

/**
 * @author Administrator
 */
public interface GlobalInstructionHandler {
    /**
     * xxx
     * @param instruction
     */
    public void doInstructionExecute(GlobalInstructionEntity instruction);
}
