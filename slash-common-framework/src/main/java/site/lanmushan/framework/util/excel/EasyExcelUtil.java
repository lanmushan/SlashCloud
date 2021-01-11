package site.lanmushan.framework.util.excel;


import com.alibaba.excel.EasyExcel;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EasyExcelUtil {
    public static void export(Class<?> clazz, List list,HttpServletResponse response) throws IOException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        String sheetName=sdf.format(new Date());

        export(clazz,list,sheetName,response);
    }
    public static void export(Class<?> clazz, List list,String sheetName,HttpServletResponse response) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode(sheetName, "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), clazz).sheet(sheetName).doWrite(list);
    }
}