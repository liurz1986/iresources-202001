package org.com.liurz.iresources.servcie.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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

    private static final String XLS = "xls";
    private static final String XLSK = "xlsx";//定义全局的常量值

    /**
     * 将数据填充到excel中
     *
     * @param workbook
     * @param datas          数据格式cell0表示第一列，cell1表示第二列，以此类推
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
    public void setHeader(Workbook workbook, int sheetNameIndex, Map<String, Object> headers) {
        Sheet sheet = workbook.createSheet(sheetNameIndex + "");
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
        // 设置水平对齐的样式为居中对齐；(低版本的话：style.setAlignment(CellStyle.ALIGN_CENTER);)
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        //style.setAlignment(HorizontalAlignment.CENTER);
        //style.setVerticalAlignment(VerticalAlignment.CENTER);
        Row row = sheet.createRow(0);  // 第一行
        int cellNum = headers.size();
        for (int i = 0; i < cellNum; i++) {
            Object objv = headers.get("cell" + i);
            Cell cell = row.createCell(i);
            if (objv instanceof String) {
                cell.setCellValue(String.valueOf(objv));
                sheet.setColumnWidth(i, String.valueOf(objv).length() * 256 + 34);
            }
            if (objv instanceof Double) {
                cell.setCellValue((double) objv);
            }
            sheet.autoSizeColumn((short) i); //设置为自动列宽
        }
    }


    public Map<String, Object> importExcel(@RequestParam("file") MultipartFile file) throws IOException, Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<Map<String,Object>> parseDatas = new ArrayList<Map<String,Object>>();
            map = parseExcel(file,parseDatas);
        } catch (Exception e) {
            map.put("status", -1);
            map.put("data", "导入异常");
        }
        return map;

    }

    /**
     * 解析导入excle 文件
     *
     * @param file
     * @param parseDatas
     * @return
     * @throws Exception
     */
    public Map<String, Object> parseExcel(MultipartFile file,List<Map<String,Object>> parseDatas) throws Exception {

        Map<String, Object> rsultMap = new HashMap<String, Object>();
        Workbook workbook = null;
        String fileName = file.getOriginalFilename();
        if (fileName.endsWith(XLS)) {
            //2003
            try {
                workbook = new HSSFWorkbook(file.getInputStream());
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (fileName.endsWith(XLSK)) {
            try {
                //2007
                workbook = new XSSFWorkbook(file.getInputStream());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            throw new Exception("文件不是Excel文件");
        }
        Sheet sheet = workbook.getSheet("Sheet1");
        int rows = sheet.getLastRowNum();//指定行数。一共多少+
        int rows1 = sheet.getPhysicalNumberOfRows();
        if (rows == 0) {
            throw new Exception("请填写行数");
        }

        for (int i = 1; i < rows + 1; i++) {
            //读取左上端单元格
            Row row = sheet.getRow(i);
            //行不为空
            if (row != null) {
                //读取cell
                Map<String,Object> tblFixChange = new HashMap<String,Object>();
                //手机号
                String phone = getCellValue(row.getCell(0));
                tblFixChange.put("phone",phone);
                //车牌号
                String catNumber = getCellValue(row.getCell(1));
                tblFixChange.put("catNumber",catNumber);
                //组的id
                String groupId = getCellValue(row.getCell(2));
                tblFixChange.put("groupId",groupId);
                parseDatas.add(tblFixChange); //把实数据放入集合里
            }
        }
        try {
            rsultMap.put("status", 1);
            rsultMap.put("data", "导入数据成功");
        } catch (Exception e) {
            rsultMap.put("status", -1);
            rsultMap.put("data", "导入数据异常");
        }
        return rsultMap;
    }

    //获取Cell内容
    public  String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        String result = "";
        if (cell instanceof HSSFCell) {
            if (cell != null) {
                // 单元格类型：Numeric:0,String:1,Formula:2,Blank:3,Boolean:4,Error:5
                int cellType = ((HSSFCell) cell).getCellType();
                switch (cellType) {
                    case HSSFCell.CELL_TYPE_STRING:
                        result = ((HSSFCell) cell).getStringCellValue();
                        break;
                    case HSSFCell.CELL_TYPE_NUMERIC:
                        DecimalFormat df = new DecimalFormat("###.####");
                        result = df.format(((HSSFCell) cell).getNumericCellValue());
                        break;
                    case HSSFCell.CELL_TYPE_FORMULA:
                        result = ((HSSFCell) cell).getNumericCellValue()+"";
                        break;
                    case HSSFCell.CELL_TYPE_BOOLEAN:
                        result = ((HSSFCell) cell).getBooleanCellValue()+"";
                        break;
                    case HSSFCell.CELL_TYPE_BLANK:
                        result = "";
                        break;
                    case HSSFCell.CELL_TYPE_ERROR:
                        result = "";
                        break;
                    default:
                        result = "";
                        break;
                }
            }
        } else if (cell instanceof XSSFCell) {
            if (cell != null) {
                // 单元格类型：Numeric:0,String:1,Formula:2,Blank:3,Boolean:4,Error:5
                int cellType = ((XSSFCell) cell).getCellType();
                switch (cellType) {
                    case XSSFCell.CELL_TYPE_STRING:
                        result = ((XSSFCell) cell).getRichStringCellValue().getString();
                        break;
                    case XSSFCell.CELL_TYPE_NUMERIC:
                        DecimalFormat df = new DecimalFormat("###.####");
                        result = df.format(((XSSFCell) cell).getNumericCellValue());
                        break;
                    case XSSFCell.CELL_TYPE_FORMULA:
                        result = ((XSSFCell) cell).getNumericCellValue()+"";
                        break;
                    case XSSFCell.CELL_TYPE_BOOLEAN:
                        result = ((XSSFCell) cell).getBooleanCellValue()+"";
                        break;
                    case XSSFCell.CELL_TYPE_BLANK:
                        result = "";
                        break;
                    case XSSFCell.CELL_TYPE_ERROR:
                        result = "";
                        break;
                    default:
                        result = "";
                        break;
                }
            }
        }
        return result;
    }
}
