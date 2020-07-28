package site.lanmushan.framework.entity;

import lombok.Data;

import javax.persistence.Transient;
import java.util.List;

/**
 * @Author dy
 * @Date 2020/7/3 20:39
 * @Version 1.0
 */
@Data
public class TreeNode<T extends TreeNode> {
    @Transient
    private List<T> children;

    /**
     * @return
     */
    public boolean isRoot() {
        return false;
    }

    public boolean isChildren(TreeNode treeNode) {
        return true;
    }

}
