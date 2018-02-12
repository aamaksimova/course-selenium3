package localhost;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class FirstTaskTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
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
