package localhost;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ESRFirstTaskTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        System.setProperty("webdriver.firefox.bin", "/home/amaksimova/Firefox_ESR_45/firefox/firefox");
        DesiredCapabilities caps = DesiredCapabilities.firefox();
        caps.setCapability("marionette", false);
        driver = new FirefoxDriver(caps);
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
