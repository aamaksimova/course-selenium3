package localhost;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.logging.Level;

public class Task17Test {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {

        DesiredCapabilities cap = DesiredCapabilities.chrome();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
        cap.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        driver = new ChromeDriver(cap);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void task17Test() {
        driver.navigate().to("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(ExpectedConditions.titleContains("My Store"));

        driver.navigate().to("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");

        for (int i=4; i <= 8; i++) {
            driver.findElement(By.xpath("//tr[@class='row'][" + i + "]//a[@title='Edit']")).click();
            wait.until(ExpectedConditions.titleContains("Edit Product"));
            for (LogEntry l : driver.manage().logs().get("browser").getAll()) {
                System.out.println(l);
                Assert.assertTrue(l.toString().isEmpty());
            }
            driver.navigate().to("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        }


    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
