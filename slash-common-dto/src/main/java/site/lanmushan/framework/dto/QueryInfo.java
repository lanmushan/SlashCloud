package site.lanmushan.framework.dto;


import site.lanmushan.framework.util.utils.StringCommonUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 通用查询参数封装
 *
 * @author Administrator
 */
public class QueryInfo implements Serializable {
    /**
     * feign 特殊处理时的标记
     */
    private String queryInfoMark = infoMark.mark.value;
    private Integer pageSize;
    private Integer currentPage = 1;
    private String sort = "desc";
    private String fixed = "";
    private String searchKey;

    private Map<String, Object> map = new HashMap<String, Object>(0);
    //前端传参
    private List<QueryParam> parList = new ArrayList<>(0);

    public QueryInfo() {

    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public String getOrders() {
        if (fixed.equals("")) {
            return null;
        }
        this.fixed = StringCommonUtil.camelToUnderline(fixed, '_');
        return fixed + " " + sort;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getFiexd() {
        return fixed;
    }

    public void setFiexd(String field) {
        this.fixed = field;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public List<QueryParam> getParList() {
        return parList;
    }

    public void setParList(List<QueryParam> parList) {
        this.parList = parList;
    }

    public String getQueryInfoMark() {
        return queryInfoMark;
    }

    public void setQueryInfoMark(String queryInfoMark) {
        this.queryInfoMark = queryInfoMark;
    }

    /**
     * 查询参数转换
     *
     * @author Administrator
     */
    public static class QueryParam {
        private String key;
        private Object value;
        private String operate;
        private int index;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public String getOperate() {
            return operate;
        }

        public void setOperate(String operate) {
            this.operate = operate;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public QueryParam() {
        }
    }

    public enum infoMark {
        /**
         * 标记
         */
        mark("queryInfoMark", "queryInfoMark");
        public String key;
        public String value;

        infoMark(String key, String value) {
            this.key = key;
            this.value = value;

        }
    }
}

