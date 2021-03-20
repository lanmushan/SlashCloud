package site.lanmushan.framework.dto;

import site.lanmushan.framework.constant.HTTPCode;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 通用数据传输通道，数据传输容器
 *
 * @author dy
 */
public class Message<T> implements Serializable {
    private int code = HTTPCode.E205.code;
    private String msg = HTTPCode.E205.msg;
    private long duration;
    private long time;
    private Integer currentPage;
    private Integer pageSize;
    private int isFirstPage;
    private int isLastPage;
    private int previusPage;
    private int nextPage;
    /**
     * 总页数 该属性用于bootstrap-table分页
     */
    private int totalPage;
    /**
     * 总记录数，该属性用于bootstrap-table分页
     */
    private int total;
    private List<Integer> pageList;

    private List<Error> errors;
    private Object row;
    private Collection<T> rows;

    public List<Error> getErrors() {
        return errors;
    }


    public Message() {
        this.time = System.currentTimeMillis();
    }

    /**
     * 通用字段校验错误情况返回
     */
    public static class Error {
        private String errMsg;
        private String errName;

        public Error() {
        }

        @Override
        public String toString() {
            return "Error{" +
                    "errMsg='" + errMsg + '\'' +
                    ", errName='" + errName + '\'' +
                    '}';
        }

        public String getErrMsg() {
            return errMsg;
        }

        public void setErrMsg(String errMsg) {
            this.errMsg = errMsg;
        }

        public String getErrName() {
            return errName;
        }

        public void setErrName(String errName) {
            this.errName = errName;
        }
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getRow() {
        return row;
    }

    public Message setRow(Object row) {
        this.row = row;
        this.setHttpCode(HTTPCode.E200);
        return this;
    }

    public void setHttpCode(HTTPCode httpCode) {
        this.code = httpCode.code;
        this.msg = httpCode.msg;
    }

    public Collection<T> getRows() {
        return rows;
    }

    public Message setRows(List rows) {
        if (null != rows && rows.size() > 0) {
            this.rows = rows;
            this.setHttpCode(HTTPCode.E200);
            this.success("查询成功");
        } else {
            this.setHttpCode(HTTPCode.E204);
        }

        return this;
    }

    public Message error(String msg) {
        this.setHttpCode(HTTPCode.E205);
        this.msg = msg;
        return this;
    }

    public Message error(HTTPCode httpCode, String msg) {
        this.setHttpCode(httpCode);
        this.msg = msg;
        return this;
    }

    public Message success(String msg) {
        this.setHttpCode(HTTPCode.E200);
        this.msg = msg;
        return this;
    }

    public void addError(String attrName, String attrMsg) {
        if (null == errors) {
            errors = new ArrayList<>();
        }
        Error error = new Error();
        error.setErrMsg(attrMsg);
        error.setErrName(attrName);
        this.errors.add(error);
    }

    public Message setTotal(int total) {
        this.total = total;
        if (currentPage == null) {
            return this;
        }
        if (currentPage == totalPage) {
            isLastPage = 1;
        }
        if (pageSize != 0) {
            this.setPage(total % pageSize == 0 ? total / pageSize : (total / pageSize) + 1);

        }
        return this;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public List<Integer> getPageList() {
        return pageList;
    }

    /**
     * 计算页码
     */
    private void calcPageList() {
        pageList = new ArrayList();
        int temp = totalPage - currentPage - 2;
        int start = currentPage - 2 + (temp < 0 ? temp : 0);
        int n = 5 - ((start < 0 ? start : 0) - 1);
        for (int i = 0; i < n; i++) {
            if (start >= 1 && start <= totalPage) {
                pageList.add(start);
            }
            start++;
        }
        if (this.currentPage < totalPage) {
            this.nextPage = currentPage + 1;
        } else {
            this.nextPage = currentPage;
            isLastPage = 1;
        }
        if (this.currentPage >= 2) {
            this.previusPage = currentPage - 1;
        } else {
            this.previusPage = currentPage;
            isFirstPage = 1;
        }
    }

    public Message setPage(Integer page) {
        this.totalPage = page;
        return this;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public String getMsg() {
        return msg;
    }

    private void setMsg(String msg) {
        this.msg = msg;
    }


    public static Message getInstance(String msg) {
        return new Message();
    }

    public static Message getInstance() {

        return new Message();
    }

    public long getTime() {
        return time;
    }
}



