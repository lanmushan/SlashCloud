package site.lanmushan.framework.datasope.bus;

import lombok.Data;

import java.util.List;

/**
 * @author Administrator
 */
public class DataScopeBus {
    static final ThreadLocal<DataScopeContext> threadLocal = new ThreadLocal<>();

    public static Boolean isEnable() {
        if (threadLocal.get() == null) {
            return false;
        }
        return threadLocal.get().isEnabled();
    }

    public static void enable() {
        getDataScopeContext().setEnabled(true);

    }

    public static Boolean isEnableTable(String tableName) {
        return getDataScopeContext().isEnableTable(tableName);
    }

    public static Boolean isExcludeTable(String tableName) {

        return getDataScopeContext().isExcludeTable(tableName);
    }

    public static void setDataScopeContext(List<String> enabledList, List<String> excludeList) {
        synchronized (threadLocal)
        {
            getDataScopeContext().setEnabledList(enabledList);
            getDataScopeContext().setExcludeList(excludeList);
        }
    }

    private static DataScopeContext getDataScopeContext() {
        if (threadLocal.get() == null) {
            synchronized (threadLocal) {
                if (threadLocal.get() == null) {
                    DataScopeContext dataScopeContext = new DataScopeContext();
                    threadLocal.set(dataScopeContext);
                }
            }
        }
        return threadLocal.get();

    }

    public static void clean() {
        threadLocal.remove();
    }
}

@Data
class DataScopeContext {
    private boolean enabled;
    private List<String> excludeList;
    private List<String> enabledList;

    /**
     * 如果没有指定要启用哪些数据表，那么就是所有数据表都启用
     *
     * @param tableName
     * @return
     */
    public Boolean isEnableTable(String tableName) {
        if (enabledList == null||enabledList.size()==0) {
            return true;
        }
        return enabledList.contains(tableName);
    }

    /**
     * 判断这个是否排除
     *
     * @param tableName
     * @return
     */
    public Boolean isExcludeTable(String tableName) {
        if (excludeList == null) {
            return false;
        }
        return enabledList.contains(tableName);
    }
}