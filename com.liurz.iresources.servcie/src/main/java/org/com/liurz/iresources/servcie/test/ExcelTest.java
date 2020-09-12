package org.com.liurz.iresources.servcie.test;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.com.liurz.iresources.servcie.util.ExcelUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liurz
 * @version V1.0
 * @Package org.com.liurz.iresources.servcie.test
 * @date 2020/8/25 22:12
 * @Copyright © 2020-2028
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ExcelTest {
    @Test
    public void down(){
        Map<String,Object> headers = new HashMap<String,Object>();
        headers.put("cell0","序号");
        headers.put("cell1","行号");
        headers.put("cell2","项目编码");
        headers.put("cell3","项目名称");
        headers.put("cell4","备注");
        Workbook workbook = new XSSFWorkbook();
        ExcelUtil excelUtil = new ExcelUtil();
        excelUtil.setHeader(workbook,0,headers);

        try {
            FileOutputStream fileOut = new FileOutputStream("D:/user/EXCELTEST.xls");
            workbook.write(fileOut);
            fileOut.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

}
