package com.bulkupload.api.bulkuploadapi.helper;

import com.bulkupload.api.bulkuploadapi.dto.EmpDto;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EmployeeHelper {
    //that file is excel type or not
     public static boolean checkExcelFormat(MultipartFile file)
     {
         String contentType=file.getContentType();

         if(contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
         {
             return true;
         }
         else
         {
             return false;
         }
     }

    public static List<EmpDto> convertExcelToListOfProduct(InputStream is)
    {
        List<EmpDto> lists=new ArrayList<EmpDto>();
        try
        {
            XSSFWorkbook workbook= new XSSFWorkbook(is);
            XSSFSheet sheet = workbook.getSheet("Sheet1");
            int rowNo=0;
            Iterator<Row> iterator=sheet.iterator();
            while(iterator.hasNext())
            {
                Row row= iterator.next();
                if(rowNo==0)
                {
                    rowNo++;
                    continue;
                }
                Iterator<Cell> cells =row.iterator();
                int cid=0;

                EmpDto emp=new EmpDto();
                while(cells.hasNext())
                {
                   Cell cell=cells.next();

                   switch(cid)
                   {
                       case 0:
                           emp.setEmpName(cell.getStringCellValue());
                           break;
                       case 1:
                           emp.setEmpCode(cell.getStringCellValue());
                           break;
                       case 2:
                           emp.setEmpPhone((int)cell.getNumericCellValue());
                           break;

                       case 3:
                           emp.setEmpEmail(cell.getStringCellValue());
                           break;
                       case 4:
                           emp.setEmpAddress(cell.getStringCellValue());
                           break;
                       case 5:
                           emp.setDepartmentName(cell.getStringCellValue());
                           break;
                       case 6:
                           emp.setDesignation(cell.getStringCellValue());
                           break;
                       default:
                           break;
                   }
                   cid++;
                }
                lists.add(emp);

            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return lists;
    }
}
