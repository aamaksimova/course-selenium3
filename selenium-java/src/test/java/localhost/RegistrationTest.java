package localhost;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver =new ChromeDriver();
        wait = new WebDriverWait(driver, 10);

        driver.navigate().to("http://localhost/litecart/en/");
    }

    @Test
    public void registrationTest() {
        driver.findElement(By.cssSelector("form[name='login_form'] a")).click();
        wait.until(ExpectedConditions.titleContains("Create Account"));
        
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
