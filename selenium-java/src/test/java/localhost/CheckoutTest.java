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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class CheckoutTest {

    private WebDriver driver;
    private WebDriverWait wait;

    boolean isElementPresent(WebDriver driver, By locator) {
        try {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            return driver.findElements(locator).size() > 0;
        } finally {
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        }
    }

    boolean isElementNotPresent(WebDriver driver, By locator) {
        try {
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            return driver.findElements(locator).size() == 0;
        } finally {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
    }

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void checkoutTest() {
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
       // for (int i=1; i<4; i++) {
            driver.navigate().to("http://localhost/litecart/en/");
            driver.findElement(By.cssSelector("li.product")).click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[name='add_cart_product']")));
            driver.findElement(By.cssSelector("button[name='add_cart_product']")).click();
            WebElement el = driver.findElement(By.cssSelector("span.quantity"));
            String q = el.getText();
            System.out.println(q);
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("div.cart[style='cursor: wait']"))));
        System.out.println(q);

      //  }
       // Assert.assertTrue();

    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
