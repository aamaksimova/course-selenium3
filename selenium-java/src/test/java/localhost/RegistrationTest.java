package localhost;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.text.DateFormat;

public class RegistrationTest {

    private WebDriver driver;
    private WebDriverWait wait;


    public static String randomEmail() {
        DateFormat date = new SimpleDateFormat("ddMMyyyyHHmm");
        Date today = Calendar.getInstance().getTime();
        String todayDate = date.format(today);
        String email = "user" + todayDate + "@gmail.com";
        return email;
    }

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
        driver =new FirefoxDriver();
        wait = new WebDriverWait(driver, 10);

        driver.navigate().to("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(ExpectedConditions.titleIs("My Store"));

        driver.navigate().to("http://localhost/litecart/admin/?app=settings&doc=security");
        Assert.assertTrue(isElementPresent(By.tagName("h1")));
        driver.findElement(By.xpath("//table[@class='dataTable']//tr[@class='row'][6]//a")).click();
        driver.findElement(By.cssSelector("input[name='value'][value='0']")).click();
        driver.findElement(By.cssSelector("button[name='save']")).click();
        Assert.assertTrue(isElementPresent(By.cssSelector("div.notice")));

        driver.navigate().to("http://localhost/litecart/en/");
    }

    @Test
    public void registrationTest() {
        driver.findElement(By.cssSelector("form[name='login_form'] a")).click();
        wait.until(ExpectedConditions.titleContains("Create Account"));
        driver.findElement(By.name("firstname")).sendKeys("first_name");
        driver.findElement(By.name("lastname")).sendKeys("last_name");
        driver.findElement(By.name("address1")).sendKeys("600 Guerrero ST.");
        driver.findElement(By.name("postcode")).sendKeys("94110");
        driver.findElement(By.name("city")).sendKeys("San Francisco");
        driver.findElement(By.cssSelector("span.select2")).click();
        driver.findElement(By.cssSelector("input.select2-search__field")).sendKeys("United States" + Keys.ENTER);
        driver.findElement(By.cssSelector("select[name='zone_code']")).sendKeys("California");
        driver.findElement(By.name("email")).sendKeys(randomEmail());
        driver.findElement(By.name("phone")).sendKeys(Keys.END + "+1123456789");
        driver.findElement(By.name("password")).sendKeys("user");
        driver.findElement(By.name("confirmed_password")).sendKeys("user");
        driver.findElement(By.cssSelector("button[name='create_account']")).click();
        Assert.assertTrue(isElementPresent(By.cssSelector("div.notice.success")));

        driver.findElement(By.xpath("//li//a[text()='Logout']")).click();
        Assert.assertTrue(isElementPresent(By.cssSelector("div.notice.success")));

        driver.findElement(By.name("email")).sendKeys(randomEmail());
        driver.findElement(By.name("password")).sendKeys("user");
        driver.findElement(By.cssSelector("button[name='login']")).click();
        Assert.assertTrue(isElementPresent(By.cssSelector("div.notice.success")));

        driver.findElement(By.xpath("//li//a[text()='Logout']")).click();
        Assert.assertTrue(isElementPresent(By.cssSelector("div.notice.success")));

    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
