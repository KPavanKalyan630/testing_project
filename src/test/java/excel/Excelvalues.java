package excel;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class Excelvalues {
    public static FileInputStream file;

    public static XSSFWorkbook wbook;
    public static XSSFSheet wsheet;
    public static XSSFRow row;
    public static XSSFCell cell;
    public static String[] arr;

    public static String[] readExcelData(String fileName) throws IOException
        {
            String[] arr;
            try {
                String path = System.getProperty("user.dir") + "\\src\\test\\java\\excel\\login.xlsx";
                file = new FileInputStream(path);
                wbook = new XSSFWorkbook(file);
                wsheet = wbook.getSheetAt(0);
                int rows = wsheet.getLastRowNum() + 1;
                int columns = wsheet.getRow(0).getLastCellNum();
                arr=new String[columns];
                row = wsheet.getRow(1);
                DataFormatter df=new DataFormatter();
                for (int j = 0; j < columns; j++) {
                    cell = row.getCell(j);
                    arr[j] = df.formatCellValue(cell);
                }
                //System.out.println(Arrays.toString(arr));
                wbook.close();
                file.close();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return arr;
        }


    }


