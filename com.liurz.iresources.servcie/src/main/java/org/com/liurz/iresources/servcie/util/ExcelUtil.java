package org.com.liurz.iresources.servcie.util;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

import java.util.List;
import java.util.Map;

/**
 * @author liurz
 * @version V1.0
 * @Package org.com.liurz.iresources.servcie.util
 * @date 2020/8/25 21:19
 * @Copyright © 2020-2028
 */
public class ExcelUtil {

    /**
     * 将数据填充到excel中
     *
     * @param workbook
     * @param datas  数据格式cell0表示第一列，cell1表示第二列，以此类推
     * @param sheetNameIndex
     */
    public void dataToExcel(Workbook workbook, int sheetNameIndex, List<Map<String, Object>> datas) {
        Sheet sheet = workbook.getSheetAt(sheetNameIndex);
        int index = 1;
        int cellNum = datas.get(0).size();
        for (Map<String, Object> data : datas) {  // 便利数据
            Row row = sheet.getRow(index);  // 传建行
            for (int i = 0; i < cellNum; i++) {
                row.getCell(i).setCellValue(String.valueOf(data.get("cell" + i)));
            }
            index++;
        }
    }

    /**
     * 设置表头
     *
     * @param workbook
     * @param sheetNameIndex
     * @param headers
     */
    public void setHeader(Workbook workbook, int sheetNameIndex, Map<String, Object> headers){
        Sheet sheet = workbook.createSheet(sheetNameIndex+"");
        // 设置格式
        // 设置字体
        Font font = workbook.createFont();
        // 设置字体大小
        font.setFontHeightInPoints((short) 12);
        // 设置样式
        CellStyle style = workbook.createCellStyle();
        // 在样式中应用设置的字体
        style.setFont(font);
        // 设置自动换行
        style.setWrapText(true);
        // 设置水平对齐的样式为居中对齐；(低版本的话：hssfCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);)
        // style.setVerticalAlignment(CellStyle.VERTICAL_CENTER)
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        Row row = sheet.createRow(0);  // 第一行
        int cellNum = headers.size();
        for (int i = 0; i < cellNum; i++) {
            Object objv = headers.get("cell" + i);
            Cell cell = row.createCell(i);
            if(objv instanceof  String){
                cell.setCellValue(String.valueOf(objv));
                sheet.setColumnWidth(i,String.valueOf(objv).length()*256+34);
            }
            if(objv instanceof  Double){
                cell.setCellValue((double)objv);
            }
            sheet.autoSizeColumn((short)i); //设置为自动列宽
        }
    }
}
