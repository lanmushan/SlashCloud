package com.lanmushan.framework.dto;




import com.lanmushan.framework.exception.OperateException;
import com.lanmushan.framework.util.CommonUtil;

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
    private Map<String,Object> map=new HashMap<>();//筛选相关参数
    private List<QueryParam> parList=null;//前端传参
    private Map<String,Object> otherMap=new  HashMap();//其他参数
    public QueryInfo() {

    }
    public String getOrders(){
        if (field.equals(""))
        {
            return null;
        }
        this.field=CommonUtil.camelToUnderline(field,'_');
        return field+" "+sort;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
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

    @Override
    public String toString() {
        return "QueryInfo{" +
                "pageSize=" + pageSize +
                ", currentPage=" + currentPage +
                ", sort='" + sort + '\'' +
                ", field='" + field + '\'' +
                ", map=" + map +
                ", parList=" + parList +
                '}';
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Map<String, Object> getMap() {
        toParamList();
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public List<QueryParam> getParList() {
        this.toParamList();
        return parList;
    }

    public void setParList(List<QueryParam> parList) {
        this.parList = parList;
    }
    private void  toParamList()
    {
        QueryParam queryParam=null;
        if(parList==null)
        {
            parList=new ArrayList<QueryParam>();
        }
        for (String key:map.keySet()) {
            queryParam=new QueryParam();
            queryParam.setKey(this.getFieldName(key));
            String op=this.getOperate(key);
            queryParam.setOperate(op);
            if(map.get(key)!="" && map.get(key)!=null){
                queryParam.setValue(this.getValue(queryParam.getKey(),op,map.get(key)));
                parList.add(queryParam);
            }
        }
        this.map.clear();
    }

    /**
     * 获取字段名
     * @param key
     * @return
     */
    private String getFieldName(String key)
    {
        if(key.indexOf("_")!=-1)
        {
            throw new OperateException("参数错误");
        }
        Integer index=key.indexOf("-");
        if(index<0)
        {
            return CommonUtil.camelToUnderline(key,'_');
        }else{
            return CommonUtil.camelToUnderline(key.substring(0,index),'_');
        }
    }
    private String getOperate(String key)
    {
        Integer index=key.indexOf("-");
        if(index<0)
        {
            return "=";
        }
        String operate=key.substring(index+1,key.length());
        operate=operate.toLowerCase();
        switch (operate)
        {
            case "like":{return "like";}
            case "in":{return "in";}
            case "eq":{return "=";}
            case "neq":{return "!=";}
            case "gt":{return ">";}
            case "lt":{return "<";}
            case "gte":{return ">=";}
            case "lte":{return "<=";}
            default:{return "=";}
        }
    }
    public Object getValue(String operate,Object value)
    {
        if(operate.equals("like"))
        {
            return value+"%";
        }
        return value;
    }
    public Object getValue(String key,String operate,Object value)
    {
        if(operate.equals("like")||key.equals("search_key"))
        {
            return value+"%";
        }
        return value;
    }
    public void defaultPageSize(){
        if(this.pageSize==null){
            this.pageSize=10;
        }
    }

    public Map<String, Object> getOtherMap() {
        return otherMap;
    }

    public void setOtherMap(Map<String, Object> otherMap) {
        this.otherMap = otherMap;
    }
}

/**
 * 查询参数转换
 * @author Administrator
 */
class QueryParam {
    private String key;
    private Object value;
    private String operate;

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
}
