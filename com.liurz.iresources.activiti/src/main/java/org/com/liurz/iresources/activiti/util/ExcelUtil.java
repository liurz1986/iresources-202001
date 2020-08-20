package org.com.liurz.iresources.activiti.util;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExcelUtil {
	private static Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

	public static Workbook exportData(Map<String, Object> excelDatas) {
		// 生成xlsx的Excel
		XSSFWorkbook workbook = new XSSFWorkbook();

		if (null == excelDatas || excelDatas.size() <= 0) {
			throw new RuntimeException("没有数据,不生成excel数据");
		}
		if (null == excelDatas.get("head")) {
			throw new RuntimeException("表头数据不存在,不生成excel数据");
		}
		String sheetName = excelDatas.get("sheetName") == null ? "sheet1" : String.valueOf(excelDatas.get("sheetName"));
		XSSFSheet sheet = workbook.createSheet(sheetName);
		// 表头
		List<String> heads = (List<String>) excelDatas.get("head");
		createHead(sheet, heads);
		// 具体内容
		List<List<String>> listDatas = (List<List<String>>) excelDatas.get("data");
		if (null == listDatas) {
			logger.info("data is null");
			return workbook;
		}
		createDataToRow(sheet, listDatas, heads.size());
		// 设置列宽度--自动适应列宽
		for (int i = 0; i < heads.size(); i++) {
			sheet.autoSizeColumn(i);
			sheet.setColumnWidth(i, sheet.getColumnWidth(i));
		}

		return workbook;

	}

	private static void createDataToRow(XSSFSheet sheet, List<List<String>> listDatas, int colmuns) {
		if (listDatas.size() <= 0) {
			return;
		}
		for (int i = 0; i < listDatas.size(); i++) {
			createRowData(colmuns, sheet.createRow(i + 1), listDatas.get(i));
		}

	}

	private static void createRowData(int colmuns, Row row, List<String> rowData) {
		for (int i = 0; i < colmuns; i++) {
			row.createCell(i).setCellValue(rowData.get(i));
		}
	}

	private static void createHead(Sheet sheet, List<String> heads) {

		try {

			XSSFRow row = (XSSFRow) sheet.createRow(0);

			for (int i = 0; i < heads.size(); i++) {
				XSSFCell cell = row.createCell(i);
				cell.setCellValue(heads.get(i));
			}
		} catch (Exception e) {
			throw new RuntimeException("获取表头数据异常", e);
		}

	}
}
