package resources;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class Base {

    WebDriver driver;

    public Base(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver initializeDriver() throws IOException {

        Properties prop = new Properties();

        String propPath = System.getProperty("user.dir") + "\\src\\main\\java\\resources\\data.properties";
        FileInputStream fis = new FileInputStream(propPath);
        prop.load(fis);
        String browserName = prop.getProperty("browser");

        if(browserName.equals("chrome")){

            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();

        } else if (browserName.equals("firefox")) {

            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browserName.equals("ie")) {

            WebDriverManager.iedriver().setup();
            driver = new InternetExplorerDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        return driver;
    }

    public WebElement findElement (By locator){

        return driver.findElement(locator);
    }

    public List<WebElement> findElements (By locator) {
        return driver.findElements(locator);
    }

    public String getText(WebElement element){
        return element.getText();
    }

    public String getText(By locator) {
        return driver.findElement(locator).getText();
    }

    public void type(String inputText, By locator) {
        driver.findElement(locator).sendKeys(inputText);
    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }

    public boolean isDisplayed(By locator){
        try {
            return driver.findElement(locator).isDisplayed();
        }catch (org.openqa.selenium.NoSuchElementException e) {
                return false;
        }
    }
    public void visit(String url){
        driver.get(url);
    }
}
