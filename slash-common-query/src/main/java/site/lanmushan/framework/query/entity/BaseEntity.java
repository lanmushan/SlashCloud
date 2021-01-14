package site.lanmushan.framework.query.entity;

import java.util.Map;

/**
 * @Author dy
 * @Date 2020/7/3 20:38
 * @Version 1.0
 */
public abstract class BaseEntity extends TreeNode<TreeNode> {
    protected Map<String,String> operateBtn;

    public Map<String, String> getOperateBtn() {
        return operateBtn;
    }
    public void setOperateBtn(Map<String, String> operateBtn) {
        this.operateBtn = operateBtn;
    }
}
