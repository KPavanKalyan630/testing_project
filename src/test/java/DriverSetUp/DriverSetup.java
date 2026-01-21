package DriverSetUp;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverSetup {
    static WebDriver driver;
    static String baseUrl="https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
    static String browserName;
    public static WebDriver getWebDriver(String browser) {
        browserName=browser;
        if(browserName.equalsIgnoreCase("chrome")) {
            driver=new ChromeDriver();
        }
        else if(browserName.equalsIgnoreCase("edge")) {
            driver=new EdgeDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(baseUrl);
        return driver;
    }
    public static void driverQuit() {

        driver.quit();
    }

    public static void driverClose(){
        driver.close();
    }
}