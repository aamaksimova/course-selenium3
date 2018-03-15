package localhost;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.NoSuchElementException;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class AllAdminPagesTest {

    private WebDriver driver;
    private WebDriverWait wait;

    public boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException Ex) {
            return false;
        }
    }

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void allAdminPagesTest() {
        driver.get("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(ExpectedConditions.titleIs("My Store"));

        List<WebElement> items = driver.findElements(By.cssSelector("li#app-"));
        WebDriverWait wait = new WebDriverWait(driver, 10);

        for (int i = 1; i <= items.size(); i++) {
            items = driver.findElements(By.cssSelector("li#app-"));
            wait.until(ExpectedConditions.visibilityOf(items.get(i - 1)));
            items.get(i - 1).click();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            Assert.assertTrue(isElementPresent(By.tagName("h1")));
            System.out.println(i);
            System.out.println(isElementPresent(By.cssSelector("ul.docs")));

                if (isElementPresent(By.cssSelector("ul.docs"))) {
                    List<WebElement> menu = driver.findElements(By.cssSelector("[id ^=doc]"));
                    for (int m = 1; m <= menu.size(); m++) {
                        menu = driver.findElements(By.cssSelector("[id ^=doc]"));
                        wait.until(ExpectedConditions.visibilityOf(menu.get(m - 1)));
                        menu.get(m - 1).click();
                        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                        Assert.assertTrue(isElementPresent(By.tagName("h1")));
                        System.out.println(m);
                    }
                }
        }

    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}
