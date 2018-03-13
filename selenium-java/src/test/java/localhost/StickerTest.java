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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class StickerTest {

    private WebDriver driver;
    private WebDriverWait wait;

    public boolean isElementPresent(By locator){
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex){
            return false;
        }
    }

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void stickerTest() {
        driver.get("http://localhost/litecart/en/");
        wait.until(ExpectedConditions.titleContains("Online Store"));

        List<WebElement> products = driver.findElements(By.cssSelector("div.content li"));
        for (int i = 1; i <= products.size(); i++) {
            products = driver.findElements(By.cssSelector("div.content li"));
            products.get(i-1).getText();
            System.out.println(products.get(i-1).getText());
            Assert.assertTrue(isElementPresent(By.xpath(".//div[starts-with(@class, 'sticker')]")));
        }

    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
