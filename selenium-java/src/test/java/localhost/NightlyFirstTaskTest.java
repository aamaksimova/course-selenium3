package localhost;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class NightlyFirstTaskTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        System.setProperty("webdriver.firefox.bin", "/home/amaksimova/FirefoxNightly/firefox/firefox");
        DesiredCapabilities caps = new DesiredCapabilities();
        driver = new FirefoxDriver();
        System.out.println(((HasCapabilities) driver).getCapabilities());
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void firstTaskTest() {
        driver.get("https://www.google.ru/");
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("webdriver");
        element.submit();
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
