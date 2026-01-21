package Locators;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
public class Locators {
    private static WebDriver driver;
    private static WebDriverWait wait;
    private static Actions act;

    public Locators(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        act=new Actions(driver);
    }
    private static FluentWait<WebDriver> getWait(WebDriver driver) {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class)
                .ignoring(org.openqa.selenium.StaleElementReferenceException.class);
    }

    public static WebElement  Username1(){
    WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='username']")));
    return username;
}
    public static WebElement password(){
        WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='password']")));
        return password;
    }

    public static void login(){
        WebElement loginBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Login']")));
        loginBtn.click();
    }

    public static void pim_click(){
        WebElement PIM = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='app']//ul[@class='oxd-main-menu']//li//span[normalize-space()='PIM']")));
        PIM.click();
    }

    public static void Add_Employee(){
        WebElement Add_Employee = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//nav//ul/li[normalize-space()='Add Employee']")));
        Add_Employee.click();
    }

    public static WebElement Frist_name(){
        WebElement first_name = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='firstName']")));
        return first_name;
    }

    public static WebElement Middle_Name(){
        WebElement middle_name = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='middleName']")));
        return middle_name;
    }

    public static WebElement Last_name(){
        WebElement last_name = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='lastName']")));
        return last_name;
    }

    public static WebElement Create_id(){
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.oxd-form-loader")));
        WebElement click=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Create Login Details']//following::input[@type='checkbox']/parent::label/span")));
        return click;
    }

    public static WebElement UserName(){
        WebElement Username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='orangehrm-employee-form']//label[normalize-space()='Username']/parent::div/following-sibling::div/input")));
        return Username;
    }

    public static WebElement Password(){
        WebElement Password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='orangehrm-employee-form']//label[normalize-space()='Password']/parent::div/following-sibling::div/input")));
        return Password;
    }
    public static WebElement ConformPassword(){
        WebElement ConfirmPassword = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='orangehrm-employee-form']//label[normalize-space()='Confirm Password']/parent::div/following-sibling::div/input")));
        return ConfirmPassword;
    }
    public static void save_deatils(){
        WebElement save_btn = getWait(driver).until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Save']"))));
        act.moveToElement(save_btn).click().perform();

    }

    public static WebElement Employee_details(){
        WebElement Employee_list = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//nav//ul/li[normalize-space()='Employee List']/a")));
        return Employee_list;
    }
    public static WebElement search_name(){
        WebElement search_name = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@class='oxd-form']//label[normalize-space()='Employee Name']//following::input[@placeholder='Type for hints...'][1]")));
       return search_name;
    }
    public static WebElement search_button(){
        WebElement button=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Search']")));
        return button;
    }

    public static WebElement Edit_button(){
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.oxd-form-loader")));
        WebElement edit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='oxd-table-cell-actions']/button[1]")));
        return edit;
    }
    public static void Nationality() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.oxd-form-loader")));
        WebElement nationalityDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//label[text()='Nationality']/ancestor::div[contains(@class,'oxd-input-group')]//div[contains(@class,'oxd-select-text-input')]")
        ));
        nationalityDropdown.click();
        WebElement Indian = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@role='listbox']/div[@role='option']/span[normalize-space()='Indian']")));
        act.moveToElement(Indian).click().perform();
    }

    public static void Marital_status(String status) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Wait for loader overlay to disappear
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.oxd-form-loader"))); // Click dropdown
         WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable( By.xpath("//label[text()='Marital Status']/ancestor::div[contains(@class,'oxd-input-group')]//div[contains(@class,'oxd-select-text-input')]")));
        dropdown.click();
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable( By.xpath("//div[@role='listbox']//span[normalize-space()='" + status + "']")));
        option.click(); }
    public static WebElement Date(){
        WebElement date=driver.findElement(By.xpath("(//div[@class='oxd-date-input'])[2]//child::input"));
        return date;
    }

    public static WebElement Gender(String gender){
        WebElement g;
        if(gender.equalsIgnoreCase("male")) {
            g= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='oxd-radio-wrapper']//input)[1]/following-sibling::span")));
        }else{
            g= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='oxd-radio-wrapper']//input)[2]/following-sibling::span")));
        }
        return g;
    }
    public static void save_button(){
        WebElement save = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[normalize-space()='Save'])[1]")));
        act.moveToElement(save).click().perform();

    }
    public static void LogOut(){
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("((//header[@class='oxd-topbar']//ul)[1]//li)[1]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("((//header[@class='oxd-topbar']//ul)[1]//li)[5]"))).click();
    }
}
