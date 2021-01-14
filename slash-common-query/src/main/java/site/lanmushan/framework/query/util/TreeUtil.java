package site.lanmushan.framework.query.util;

import site.lanmushan.framework.query.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author dy
 * @Date 2020/7/2 22:59
 * @Version 1.0
 */
public class TreeUtil {

    /**
     * 列表转换为树
     *
     * @param nodeList
     * @return
     */
    public static <T extends TreeNode<T>> List<T> listToTree(List<T> nodeList) {
        List<T> tree = new ArrayList<T>();
        for (T node : nodeList) {
            if (node.isRoot()) {
                tree.add(findChildren(node, nodeList));
            }
        }
        return tree;
    }

    /**
     * 递归查找子节点
     *
     * @param nodeList
     * @return
     */
    public static <T extends TreeNode<T>> T findChildren(T node, List<T> nodeList) {

        for (T it : nodeList) {
            if (node.isChildren(it)) {
                if (node.getChildren() == null) {
                    node.setChildren(new ArrayList<T>());
                }
                node.getChildren().add(findChildren(it, nodeList));
            }
        }
        return node;
    }


}
