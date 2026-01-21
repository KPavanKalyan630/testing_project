package ScreenShorts;

import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import java.io.File;
import java.io.IOException;

public class Screenshots {


    private static final String BASE_DIR = "C:\\Users\\2457405\\AppData\\Local\\Temp\\272b41e0-4132-49ac-a8b4-83994a3438bc_2355376_MiniProject_Deletingskillsset 1.zip.8bc\\Creating_new_Employe\\src\\test\\java\\ScreenShorts\\screenshots";


    public static String takeScreenshot(WebDriver driver, String fileName) {
        // Create screenshot file
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = BASE_DIR + fileName + ".png";
        File dest = new File(path);

        try {
            FileHandler.copy(src, dest);
            System.out.println("Screenshot saved at: " + dest.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }
}
