package site.lanmushan.framework.redis.subscribe;

import site.lanmushan.framework.redis.GlobalInstructionEntity;

/**
 * @author Administrator
 */
public interface GlobalInstructionHander {
    /**
     * @param instruction
     */
    public void doInstructionExecute(GlobalInstructionEntity instruction);
}
