package localhost;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class AddNewProductTest {

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

        driver.navigate().to("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(ExpectedConditions.titleIs("My Store"));
    }

    @Test
    public void addNewProductTest() {
        driver.findElement(By.xpath("//li[@id='app-']//span[text()='Catalog']")).click();
        Assert.assertTrue(isElementPresent(By.tagName("h1")));

        driver.findElement(By.xpath("//div//a[@class='button'][2]")).click();
        driver.findElement(By.xpath("//label//input[@name='status']")).click();

        driver.findElement(By.name("name[en]")).sendKeys("Smile");
        driver.findElement(By.name("code")).sendKeys("43434HB4");
        driver.findElement(By.cssSelector("input[data-name='Rubber Ducks']")).click();
        driver.findElement(By.cssSelector("input[name='product_groups[]']")).click();
        driver.findElement(By.name("quantity")).sendKeys("10");
        File file = new File("resources/smile.jpeg");
        String absolutePath = file.getAbsolutePath();
        driver.findElement(By.cssSelector("input[type='file']")).sendKeys(absolutePath);
        driver.findElement(By.name("date_valid_from")).sendKeys(Keys.HOME + "01032018");
        driver.findElement(By.name("date_valid_to")).sendKeys(Keys.HOME + "31032018");

        driver.findElement(By.cssSelector("li a[href='#tab-information']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("tab-information")));
        driver.findElement(By.name("manufacturer_id")).isEnabled();
        driver.findElement(By.name("keywords")).sendKeys("happiness");
        driver.findElement(By.name("short_description[en]")).sendKeys("test description");
        driver.findElement(By.cssSelector("div.trumbowyg-editor")).sendKeys("long description");
        driver.findElement(By.name("head_title[en]")).sendKeys("Smile");
        driver.findElement(By.name("meta_description[en]")).sendKeys("product, smile");

        driver.findElement(By.cssSelector("li a[href='#tab-prices']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("tab-prices")));
        driver.findElement(By.name("purchase_price")).sendKeys("21,00");
        WebElement currency = driver.findElement(By.name("purchase_price_currency_code"));
        currency.click();
        WebElement dollar = currency.findElement(By.cssSelector("option[value='USD']"));
        dollar.isSelected();
        driver.findElement(By.name("prices[USD]")).sendKeys("21");
        WebElement price = driver.findElement(By.name("prices[USD]"));
        String value1 = price.getAttribute("placeholder");
        System.out.println(value1);
        WebElement tax = driver.findElement(By.name("gross_prices[USD]"));
        String value2 = tax.getAttribute("value");
        System.out.println(value2);
        Assert.assertTrue(value1.equals(value2));

        driver.findElement(By.cssSelector("button[name='save']")).click();
        Assert.assertTrue(isElementPresent(By.cssSelector("div.notice.success")));

        Assert.assertTrue(isElementPresent(By.xpath("//td//a[text()='Smile']")));

    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
