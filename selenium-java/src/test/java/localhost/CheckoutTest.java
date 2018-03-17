package localhost;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CheckoutTest {

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
    public void checkoutTest() {
        driver.navigate().to("http://localhost/litecart/en/");
        for (int i=1; i<4; i++) {
            driver.findElement(By.cssSelector("li.product")).click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[name='add_cart_product']")));
            if (isElementPresent(By.cssSelector("select[name='options[Size]']"))) {
                driver.findElement(By.cssSelector("select[name='options[Size]']")).sendKeys("Small");
            }
            driver.findElement(By.cssSelector("button[name='add_cart_product']")).click();
            WebElement el = driver.findElement(By.cssSelector("span.quantity"));
            wait.until(ExpectedConditions.textToBePresentInElement(el, "" + i + ""));
        }
        Assert.assertTrue(driver.findElement(By.cssSelector("span.quantity")).getText().contains("3"));

        driver.findElement(By.cssSelector("#cart a.link")).click();
        wait.until(ExpectedConditions.titleContains("Checkout"));

        do {
            if (isElementPresent(By.xpath("//li[1]/a[@class='inact act']"))) {
            driver.findElement(By.xpath("//li[1]/a[@class='inact act']")).click();
        }
        driver.findElement(By.cssSelector("p button[name='remove_cart_item']")).click();
        List<WebElement> pr = driver.findElements(By.cssSelector("table.dataTable td.item"));
        wait.until(ExpectedConditions.stalenessOf(pr.get(1)));
        driver.navigate().refresh(); } while (isElementPresent(By.xpath("//li[@class='shortcut']")));

        if (isElementPresent(By.cssSelector("table.dataTable td.item"))) {
            WebElement prod = driver.findElement(By.cssSelector("table.dataTable td.item"));
            driver.findElement(By.cssSelector("p button[name='remove_cart_item']")).click();
            wait.until(ExpectedConditions.stalenessOf(prod));
        }
        Assert.assertTrue(driver.findElement(By.cssSelector("div#content em")).getText().contains("no items"));
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
