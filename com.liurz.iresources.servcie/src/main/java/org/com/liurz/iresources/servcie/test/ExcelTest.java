package org.com.liurz.iresources.servcie.test;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.com.liurz.iresources.servcie.util.ExcelUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liurz
 * @version V1.0
 * @Package org.com.liurz.iresources.servcie.test
 * @date 2020/8/25 22:12
 * @Copyright © 2020-2028
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class ExcelTest {

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

    public void parseExcel(){
        File file = new File("D:\\user\\EXCELTEST.xls");
        InputStream input = null;
        try {
            input = new FileInputStream(file);
            Workbook  workbook = new XSSFWorkbook(input);
            Sheet sheet=workbook.getSheetAt(0);
            int rows = sheet.getLastRowNum();
            int rows1 = sheet.getPhysicalNumberOfRows();
            System.out.println("rows:"+rows+"rows1:"+rows1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch(Exception e){
            e.getStackTrace();
        }


        //获得第一个工作表对象(ecxel中sheet的编号从0开始,0,1,2,3,....)

    }
}
