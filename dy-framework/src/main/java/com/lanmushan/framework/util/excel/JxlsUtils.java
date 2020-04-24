package com.lanmushan.framework.util.excel;

import org.jxls.common.Context;
import org.jxls.expression.JexlExpressionEvaluator;
import org.jxls.reader.ReaderBuilder;
import org.jxls.reader.XLSReadStatus;
import org.jxls.reader.XLSReader;
import org.jxls.transform.Transformer;
import org.jxls.util.JxlsHelper;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * excel导入导出工具类
 *
 * @author Administrator
 */
public class JxlsUtils {

    private static final String TEMPLATE_PATH = "resource/exportTpl";
    private static final String INPORT_COFNIG_PATH = "resource/importConfig";
    private static String rootFileName;
    private Map<String, Integer> countMap = new HashMap<>();

    static {
        File file = new File("");
        rootFileName = file.getAbsolutePath() + "/" + TEMPLATE_PATH;
    }

    public static void exportExcel(InputStream is, OutputStream os, Map<String, Object> model) throws IOException {
        Context context = new Context();
        if (model != null) {
            for (String key : model.keySet()) {
                context.putVar(key, model.get(key));
            }
        }
        JxlsHelper jxlsHelper = JxlsHelper.getInstance();
        Transformer transformer = jxlsHelper.createTransformer(is, os);
        JexlExpressionEvaluator evaluator = (JexlExpressionEvaluator) transformer.getTransformationConfig().getExpressionEvaluator();
        Map<String, Object> funcs = new HashMap<String, Object>();
        //添加自定义功能
        funcs.put("utils", new JxlsUtils());
        evaluator.getJexlEngine().setFunctions(funcs);
        jxlsHelper.processTemplate(context, transformer);
    }

    public static void exportExcel(File xls, File out, Map<String, Object> model) throws FileNotFoundException, IOException {
        exportExcel(new FileInputStream(xls), new FileOutputStream(out), model);
    }

    public static void exportExcel(String templateName, OutputStream os, Map<String, Object> model) throws FileNotFoundException, IOException {
        File template = getTemplate(templateName);
        if (template != null) {
            exportExcel(new FileInputStream(template), os, model);
        }
    }

    /**
     * excel导入方法
     * @param tplConfigName
     * @param map
     * @param dataStream
     * @return
     */
    public static boolean inportExcel(String tplConfigName, Map map, InputStream dataStream) {
        try {
            String fileName = new File("").getAbsolutePath() + File.separatorChar + INPORT_COFNIG_PATH + File.separatorChar + tplConfigName;
            //配置文件
            FileInputStream xmlFin = new FileInputStream(new File(fileName));
            InputStream inputXML = new BufferedInputStream(xmlFin);
            XLSReader mainReader = ReaderBuilder.buildFromXML(inputXML);
            XLSReadStatus readStatus = mainReader.read(dataStream, map);
            return readStatus.isStatusOK();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 获取excel模版文件
     * @param name
     * @return
     */
    public static File getTemplate(String name) {
        File template = new File(rootFileName, name);
        if (template.exists()) {
            return template;
        }
        return null;
    }

    /**
     * 日期格式化函数
     * @param date
     * @param fmt
     * @return
     */
    public String dateFmt(Date date, String fmt) {
        if (date == null) {
            return "";
        }
        try {
            SimpleDateFormat dateFmt = new SimpleDateFormat(fmt);
            return dateFmt.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 统计函数
     * @param var
     * @return
     */
    public Integer count(String var) {
        if (var == null){
            return null;
        }
        if (countMap.containsKey(var)) {
            Integer t = countMap.get(var);
            t += 1;
            countMap.replace(var, t);
            return t;
        } else {
            countMap.put(var, 1);
        }
        return 1;
    }

    // if判断
    public Object ifelse(boolean b, Object o1, Object o2) {
        return b ? o1 : o2;
    }

//    public static void main(String[] args) throws ParseException, IOException {
//        List<Employee> employees = generateSampleEmployeeData();
//        OutputStream os = new FileOutputStream("D://test.xlsx");
//        Map<String, Object> model = new HashMap<String, Object>();
//        model.put("employees", employees);
//        model.put("nowdate", new Date());
//        JxlsUtils.exportExcel("object_collection_template1.xlsx", os, model);
//        os.close();
//    }
//
//    public static List<Employee> generateSampleEmployeeData() throws ParseException {
//        List<Employee> employees = new ArrayList<Employee>();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MMM-dd", Locale.US);
//        employees.add(new Employee("Elsa", dateFormat.parse("1970-Jul-10"), 1500, 0.15));
//        employees.add(new Employee("Oleg", dateFormat.parse("1973-Apr-30"), 2300, 0.25));
//        employees.add(new Employee("Neil", dateFormat.parse("1975-Oct-05"), 2500, 0.00));
//        employees.add(new Employee("Maria", dateFormat.parse("1978-Jan-07"), 1700, 0.15));
//        employees.add(new Employee("John", dateFormat.parse("1969-May-30"), 2800, 0.20));
//        return employees;
//    }
}
