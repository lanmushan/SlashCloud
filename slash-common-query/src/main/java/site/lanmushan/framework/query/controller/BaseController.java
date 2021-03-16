package site.lanmushan.framework.query.controller;

import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import site.lanmushan.framework.query.util.StringCommonUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * 通用Controller,所有Controller需要继承该基类
 *
 * @author dy
 */
public class BaseController {
    /**
     * 开始分页
     */
    protected void startPage() {
        startPage(getCurrentPage(), getPageSize());
    }

    /**
     * 获取当前页面
     *
     * @return
     */
    protected int getCurrentPage() {
        Integer currentPage = 1;
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String currentPageStr = request.getParameter("currentPage");
        if (StringUtils.isNotBlank(currentPageStr)) {
            currentPage = Integer.parseInt(currentPageStr);
        }
        return currentPage;
    }

    /**
     * 获取分页大小
     *
     * @param defaultPageSize
     * @return
     */
    protected int getPageSize(int defaultPageSize) {
        Integer pageSize = defaultPageSize;
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String pageSizeStr = request.getParameter("pageSize");
        if (StringUtils.isNotBlank(pageSizeStr)) {
            pageSize = Integer.parseInt(pageSizeStr);
        }
        return pageSize;
    }

    protected int getPageSize() {
        Integer pageSize = 10000;
        return getPageSize(pageSize);
    }

    /**
     * 开始分页
     *
     * @param currentPage 当前页
     * @param pageSize    分页大小
     */
    public void startPage(Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        PageHelper.orderBy(getOrders());
    }

    /**
     * 获取排序
     *
     * @return
     */
    public String getOrders() {
        String fixed = null;
        String sort = "desc";
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        fixed = request.getParameter("fixed");
        if (StringUtils.isEmpty(fixed)) {
            return null;
        }
        String sortStr = request.getParameter("sort");
        if (!StringUtils.isEmpty(sortStr)) {
            sort = sortStr;
        }
        fixed = StringCommonUtil.camelToUnderline(fixed, '_');
        return fixed + " " + sort;
    }
}
