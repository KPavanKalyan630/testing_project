package Main;

import DriverSetUp.DriverSetup;
import Locators.Locators;
import ScreenShorts.Screenshots;
import excel.Excelvalues;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import static ScreenShorts.Screenshots.*;

public class Home {

    // ===== Extent single-file setup =====
    private static ExtentReports extent;
    private static String reportPath;

    private static ExtentReports initExtent() {
        if (extent == null) {
            String ts = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            reportPath = Paths.get("reports", "ExtentReport_" + ts + ".html").toString();

            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
            spark.config().setDocumentTitle("Automation Execution Report");
            spark.config().setReportName("OrangeHRM – Add/Update Employee Flow");
            spark.config().setTheme(Theme.STANDARD);

            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Java", System.getProperty("java.version"));
            extent.setSystemInfo("Browser", "Edge");
        }
        return extent;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        ExtentReports extent = initExtent();
        ExtentTest test = extent.createTest("E2E – Add Employee & Update Personal Details");
        WebDriver driver = null;

        try {
            // Launch
            test.log(Status.INFO, "Launching Edge WebDriver");
            driver = DriverSetup.getWebDriver("Edge");
            test.pass("Edge WebDriver launched");

            // Data
            String fileName = "src/test/jedgava/excel/login.xlsx";
            String[] data = Excelvalues.readExcelData(fileName);
            test.info("Read data from Excel: " + fileName);

            // Locators
            Locators lc = new Locators(driver);
            test.info("Initialized page locators");

            // Login
            test.info("Logging into OrangeHRM");
            Locators.Username1().sendKeys(data[0]);
            Locators.password().sendKeys(data[1]);
            String loginShot = takeScreenshot(driver, "login");
            Locators.login();

            test.pass("Login successful",
                    loginShot != null ? MediaEntityBuilder.createScreenCaptureFromPath(loginShot).build() : null);

            // Navigate to PIM and Add Employee
            test.info("Navigating to PIM");
            Locators.pim_click();

            test.info("Opening Add Employee and entering details");
            Locators.Add_Employee();

            // Removed pimShot screenshot block here

            Locators.Frist_name().sendKeys(data[2]);
            Locators.Middle_Name().sendKeys(data[3]);
            Locators.Last_name().sendKeys(data[4]);
            Locators.Create_id().click();
            Locators.UserName().sendKeys(data[5]);
            Locators.Password().sendKeys(data[6]);
            Locators.ConformPassword().sendKeys(data[6]);
            Locators.save_deatils();

            String detailsShot = takeScreenshot(driver, "employee_first_details");
            test.pass("Employee basic details saved",
                    detailsShot != null ? MediaEntityBuilder.createScreenCaptureFromPath(detailsShot).build() : null);

            // Search employee
            test.info("Searching employee by first name: " + data[2]);
            Locators.Employee_details().click();
            Locators.search_name().sendKeys(data[2]);
            Locators.search_button().click();
            String searchShot = takeScreenshot(driver, "search_result");
            test.pass("Search successful",
                    searchShot != null ? MediaEntityBuilder.createScreenCaptureFromPath(searchShot).build() : null);

            // Edit personal details
            test.info("Editing personal details");
            Locators.Edit_button().click();
            Locators.Nationality();
            Locators.Marital_status(data[10]);
            Locators.Date().sendKeys("1999-02-09");
            Locators.Gender(data[8]).click();

            String secondDetailsShot = takeScreenshot(driver, "employee_second_details");
            Locators.save_button();
            test.pass("Personal details updated",
                    secondDetailsShot != null ? MediaEntityBuilder.createScreenCaptureFromPath(secondDetailsShot).build() : null);

            // Logout
            Locators.LogOut();
            test.pass("Logged out successfully");

        } catch (Exception e) {
            String failShot = (driver != null) ? takeScreenshot(driver, "failure") : null;
            if (failShot != null) {
                test.fail("Test failed: " + e.getMessage(),
                        MediaEntityBuilder.createScreenCaptureFromPath(failShot).build());
            } else {
                test.fail("Test failed: " + e.getMessage());
            }
            throw e; // optional: rethrow to fail CI build
        } finally {
            try {
                DriverSetup.driverQuit();
            } catch (Exception ignored) {}

            extent.flush(); // write HTML report
            System.out.println("Extent report: " + reportPath);
        }
    }
}
