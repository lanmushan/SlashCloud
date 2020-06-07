package com.lanmushan.framework.dto;




import com.lanmushan.framework.util.StringCommonUtil;

import java.io.Serializable;
import java.util.*;

/**
 * 通用查询参数封装
 * @author Administrator
 */
public class QueryInfo implements Serializable {
    private Integer pageSize;
    private Integer currentPage=1;
    private String sort="desc";//
    private String field ="";
    private String searchKey;
    private List<QueryParam> parList=new ArrayList<>(0);//前端传参
    public QueryInfo() {

    }
    public String getOrders(){
        if (field.equals(""))
        {
            return null;
        }
        this.field= StringCommonUtil.camelToUnderline(field,'_');
        return field+" "+sort;
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

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
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


    /**
     * 查询参数转换
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

     }

}

