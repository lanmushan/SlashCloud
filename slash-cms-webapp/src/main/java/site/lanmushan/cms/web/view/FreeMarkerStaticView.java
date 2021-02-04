package site.lanmushan.cms.web.view;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;
import freemarker.template.SimpleHash;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreeMarkerStaticView extends FreeMarkerView {
    @Override
    protected void doRender(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
//        // Expose model to JSP tags (as request attributes).
//        exposeModelAsRequestAttributes(model, request);
//        // Expose all standard FreeMarker hash models.
//        SimpleHash fmModel = buildTemplateModel(model, request, response);
//        // Grab the locale-specific version of the template.
//        Locale locale = RequestContextUtils.getLocale(request);
//        String path = (String) request.getAttribute(StaticConstant.STATIC_MARKING);
//        if (StaticManger.isIsDebug()) {
//            System.out.println("需要静态化的页面路径:" + path);
//        }
//        if (path == null || path.equals("")) {
//            processTemplate(getTemplate(locale), fmModel, response);
//        } else {
//            /*静态化页面*/
//            createFile(getTemplate(locale), fmModel, request, response, path);
//        }
    }

    public void createFile(Template template, SimpleHash model, HttpServletRequest request,
                           HttpServletResponse response, String relativePath) throws IOException, TemplateException, ServletException {

//        // 站点根目录的绝对路径
//        String basePath = request.getServletContext().getRealPath("/");//获取项目路径
//        File file = new File(basePath + File.separatorChar + StaticManger.getPath() + request.getRequestURI());
//        if (!file.exists()) {
//            file.mkdirs();
//            System.out.println("创建文件夹");
//        }
//        File file1 = new File(basePath + File.separatorChar + StaticManger.getPath() + request.getRequestURI() + File.separatorChar + relativePath);
//        if (!file1.exists()) {
//            file1.createNewFile();
//        }
//        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file1), "UTF-8"));
//        // 处理模版
//        template.process(model, out);
//        out.flush();
//        out.close();
//        /* 将请求转发到生成的htm文件 */
//        request.getRequestDispatcher("/" + StaticManger.getPath() + request.getRequestURI() + relativePath).forward(request, response);
    }


}
