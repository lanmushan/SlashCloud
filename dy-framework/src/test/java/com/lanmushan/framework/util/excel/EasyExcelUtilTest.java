package com.lanmushan.framework.util.excel;

import com.alibaba.excel.EasyExcel;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author dy
 * @Date 2020/6/13 17:34
 * @Version 1.0
 */
public class EasyExcelUtilTest {

    @Test
    public void writeExcelWithStringList() throws IOException {
        writeExcel();
    }

    private static void writeExcel() throws IOException {
        List<DemoData> demoDataList = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            DemoData demoData = new DemoData();
            demoData.setAddress("address" + i);
            demoData.setAge(i + "");
            demoData.setEmail("email" + i);
            demoData.setHeigh("heigh" + i);
            demoData.setLast("last" + i);
            demoData.setName("name" + i);
            demoData.setSax("sax" + i);
            demoDataList.add(demoData);
        }

        long beginTime = System.currentTimeMillis();
        String fileName="D:\\temp\\aa1.xlsx";
        EasyExcel.write(fileName, DemoData.class).sheet("模板").doWrite((demoDataList));
    }

}