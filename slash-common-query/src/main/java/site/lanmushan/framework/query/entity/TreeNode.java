package site.lanmushan.framework.query.entity;

import lombok.Data;

import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author dy
 * @Date 2020/7/3 20:39
 * @Version 1.0
 */
@Data
public abstract class TreeNode<T extends TreeNode> {
    @Transient
    private List<T> children = new ArrayList<T>();

    /**
     * @return
     */
    public boolean isRoot() {
        return false;
    }

    public boolean isChildren(TreeNode treeNode) {
        return false;
    }

}
