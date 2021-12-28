package com.example.smallTools.excel;

import com.alibaba.excel.EasyExcel;
import com.example.smallTools.excel.model.DataDemo;

import java.util.Collections;

public class WriteExcelTest {

    public static void main(String[] args) {
        String fileName = "/Users/woodyiiiiii/Documents/Java/project/small-tools/src/main/java/com/example/smallTools/excel/test.xlsx";

        EasyExcel.write(fileName, DataDemo.class).sheet("模板").doWrite(Collections.singletonList(new DataDemo("123", 20)));
    }

}
