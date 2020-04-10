package com.lanmushan.framework.view;


//import net.sf.jxls.transformer.XLSTransformer;
import com.lanmushan.framework.util.excel.JxlsUtils;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Excel导出解析器
 */
public class JXLSExcelView extends AbstractView {
    public static final String EXCEL_EXPORT_FILE_NAME = "ExcelExportFileName";
    public static final String EXCEL_TEMPLATE_FILE_NAME = "ExcelTemplateFileName";
    public static final String EXCEL_SHEET_NAMES = "ExcelSheetNames";
    /** The content type for an Excel response */
    private static final String CONTENT_TYPE = "application/vnd.ms-excel";
//    private XLSTransformerExt transformer;

    /**
     * Default Constructor.
     * Sets the content type of the view to "application/vnd.ms-excel".
     */
    public JXLSExcelView() { //transformer = new XLSTransformerExt();
        setContentType(CONTENT_TYPE);
    }

    @Override
    protected boolean generatesDownloadContent() {
        return true;
    }

    @SuppressWarnings("rawtypes")
    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response)  {
       try {

           String fileName = (String)model.get(EXCEL_EXPORT_FILE_NAME);
           String templateName = (String)model.get(EXCEL_TEMPLATE_FILE_NAME);
           List newSheetNames = (List)model.get(EXCEL_SHEET_NAMES);
           response.setHeader("content-disposition","attachment; filename="+new String(fileName.getBytes("gb2312"),"ISO8859-1"));
           ServletOutputStream out = response.getOutputStream();
           JxlsUtils.exportExcel(templateName, out, model);
           //  transformer.transformXLS(request.getSession().getServletContext(), srcFilePath, model, out);
           out.flush();
           out.close();
       }catch (Exception e)
       {
           e.printStackTrace();
       }
    }
    public static void main(String args[]){
        Integer a=200;
        Integer b=200;
       System.out.println(a>=b);
    }
}

//class XLSTransformerExt extends XLSTransformer {
//    @SuppressWarnings("rawtypes")
//    public void transformXLS(ServletContext servletContext, String srcFilePath, Map beanParams, OutputStream os) {
//        try {
//            ServletContextResource resource = new ServletContextResource(servletContext, srcFilePath);
//            File rootFile=new File("");
//            File srcFile=new File(rootFile.getAbsoluteFile(),srcFilePath);
//            System.out.println(srcFile.getAbsoluteFile());
//            Workbook workbook = transformXLS(new FileInputStream(srcFile), beanParams);
//            workbook.write(os);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
