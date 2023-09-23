package com.itheima.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @ClassName Demo
 * @Description
 * @Author 传智播客
 * @Date 2019/7/6 20:53
 * @Version 1.0
 **/
public class Demo {

    public static void main(String[] args) throws IOException {
        read();
        System.out.println("**************");
        read2();
        System.out.println("**************");
        write();

    }

    private static void read() throws IOException {
        //创建工作簿
        XSSFWorkbook workbook = new XSSFWorkbook("F:\\hello.xlsx");
        //获取工作表 既可以根据工作表的顺序获取,也可以根据工作表的名称获取
        XSSFSheet sheet=workbook.getSheetAt(0);
        //遍历工作表获得行对象
        for (Row row : sheet) {
            //遍历行对象获取单元格对象
            for (Cell cell : row) {
                //获得单元格中的值
                String value=cell.getStringCellValue();
                System.out.println(value);
            }
        }
        workbook.close();
    }

    private static void read2() throws IOException {
        //创建工作簿
        XSSFWorkbook workbook = new XSSFWorkbook("F:\\hello.xlsx");
        //获取工作表 既可以根据工作表的顺序获取,也可以根据工作表的名称获取
        XSSFSheet sheet=workbook.getSheetAt(0);
        //获取当前工作表的最后一行,行号从0开始
        int lastrownum=sheet.getLastRowNum();
        for (int i = 0; i <=lastrownum; i++) {
            //根据行号获取行对象
            XSSFRow row = sheet.getRow(i);
            short lastcellnum = row.getLastCellNum();
            for (short j=0;j<lastcellnum;j++){
                String value = row.getCell(j).getStringCellValue();
                System.out.println(value);
            }
        }
        workbook.close();
    }

    public static void write() throws IOException {
        //在内存中创建一个Excel文件
        XSSFWorkbook workbook = new XSSFWorkbook();
        //创建工作表,指定工作表名称
        XSSFSheet sheet = workbook.createSheet("传智播客");
        //创建行,0表示第一行
        XSSFRow row = sheet.createRow(0);
        //创建单元格,0表示第一个单元格
        row.createCell(0).setCellValue("编号");
        row.createCell(1).setCellValue("名称");
        row.createCell(2).setCellValue("年龄");
        XSSFRow row1 = sheet.createRow(1);
        row1.createCell(0).setCellValue("1");
        row1.createCell(1).setCellValue("小明");
        row1.createCell(2).setCellValue("10");
        XSSFRow row2 = sheet.createRow(2);
        row2.createCell(0).setCellValue("2");
        row2.createCell(1).setCellValue("小王");
        row2.createCell(2).setCellValue("20");
        //通过输出流将workbook对象下载到磁盘
        FileOutputStream out = new FileOutputStream("F:\\itcast.xlsx");
        workbook.write(out);
        out.flush();
        out.close();
        workbook.close();
    }

}
