package localhost;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.List;
import java.util.Set;

public class Task10Test {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver =new ChromeDriver();
        wait = new WebDriverWait(driver, 10);

        driver.navigate().to("http://localhost/litecart/en/");
        wait.until(ExpectedConditions.titleContains("Online Store"));
    }

    @Test
    public void task10aTest() {
        WebElement block = driver.findElement(By.cssSelector("#box-campaigns a.link"));
        WebElement product = block.findElement(By.cssSelector("div.name"));
        String productTitle = product.getText();
        System.out.println(productTitle);
        product.click();
        wait.until(ExpectedConditions.titleContains("Yellow Duck"));
        WebElement name = driver.findElement(By.cssSelector("h1.title"));
        String detailedTitle = name.getAttribute("textContent");
        System.out.println(detailedTitle);
        Assert.assertTrue(productTitle.equals(detailedTitle));
    }

    @Test
    public void task10bTest() {
        WebElement block = driver.findElement(By.cssSelector("#box-campaigns a.link"));
        WebElement oldprice = block.findElement(By.cssSelector("div.price-wrapper s.regular-price"));
        String pprice = oldprice.getText();
        System.out.println("oldprice" + pprice);

        WebElement price = block.findElement(By.cssSelector("div.price-wrapper strong.campaign-price"));
        String nprice = price.getText();
        System.out.println("currentprice" + nprice);

        driver.findElement(By.cssSelector("#box-campaigns a.link")).click();
        wait.until(ExpectedConditions.titleContains("Yellow Duck"));

        WebElement detailedOldPrice = driver.findElement(By.cssSelector("div.price-wrapper s.regular-price"));
        String detailedPPrice = detailedOldPrice.getText();
        System.out.println("oldprice" + detailedPPrice);

        WebElement detailedPrice = driver.findElement(By.cssSelector("div.price-wrapper strong.campaign-price"));
        String detailedNPrice = detailedPrice.getText();
        System.out.println("currentprice" + detailedNPrice);

        Assert.assertTrue(pprice.equals(detailedPPrice));
        Assert.assertTrue(nprice.equals(detailedNPrice));
    }

    @Test
    public void task10vTest() {
        WebElement block = driver.findElement(By.cssSelector("#box-campaigns a.link"));
        WebElement oldprice = block.findElement(By.cssSelector("div.price-wrapper s.regular-price"));
        String opcolor = oldprice.getCssValue("color");
        System.out.println(opcolor);
        Assert.assertTrue(opcolor.contains("119, 119, 119"));
        String opfont = oldprice.getCssValue("text-decoration");
        System.out.println(opfont);
        Assert.assertTrue(opfont.contains("line-through"));

        driver.findElement(By.cssSelector("#box-campaigns a.link")).click();
        wait.until(ExpectedConditions.titleContains("Yellow Duck"));

        WebElement detailedOldPrice = driver.findElement(By.cssSelector("div.price-wrapper s.regular-price"));
        String detailedopcolor = detailedOldPrice.getCssValue("color");
        System.out.println(detailedopcolor);
        Assert.assertTrue(detailedopcolor.contains("102, 102, 102"));
        String detailedopfont = detailedOldPrice.getCssValue("text-decoration");
        System.out.println(detailedopfont);
        Assert.assertTrue(detailedopfont.contains("line-through"));

    }

    @Test
    public void task10gTest() {
        WebElement block = driver.findElement(By.cssSelector("#box-campaigns a.link"));
        WebElement price = block.findElement(By.cssSelector("div.price-wrapper strong.campaign-price"));
        String pcolor = price.getCssValue("color");
        System.out.println(pcolor);
        Assert.assertTrue(pcolor.contains("204, 0, 0"));
        String pfont = price.getAttribute("tagName");
        System.out.println(pfont);
        Assert.assertTrue(pfont.contains("STRONG"));

        driver.findElement(By.cssSelector("#box-campaigns a.link")).click();
        wait.until(ExpectedConditions.titleContains("Yellow Duck"));

        WebElement detailedprice = driver.findElement(By.cssSelector("div.price-wrapper strong.campaign-price"));
        String detailedpcolor = detailedprice.getCssValue("color");
        System.out.println(detailedpcolor);
        Assert.assertTrue(detailedpcolor.contains("204, 0, 0"));
        String detailedpfont = detailedprice.getAttribute("tagName");
        System.out.println(detailedpfont);
        Assert.assertTrue(detailedpfont.contains("STRONG"));
    }

    @Test
    public void task10dTest() {
        WebElement block = driver.findElement(By.cssSelector("#box-campaigns a.link"));
        WebElement oldprice = block.findElement(By.cssSelector("div.price-wrapper s.regular-price"));
        Dimension opSize = oldprice.getSize();
        System.out.println(opSize);
        WebElement price = block.findElement(By.cssSelector("div.price-wrapper strong.campaign-price"));
        Dimension pSize = price.getSize();
        System.out.println(pSize);
        Assert.assertTrue(opSize.height < pSize.height);
        Assert.assertTrue(opSize.width < pSize.width);

        driver.findElement(By.cssSelector("#box-campaigns a.link")).click();
        wait.until(ExpectedConditions.titleContains("Yellow Duck"));

        WebElement detailedOldPrice = driver.findElement(By.cssSelector("div.price-wrapper s.regular-price"));
        Dimension detailedosize = detailedOldPrice.getSize();
        System.out.println(detailedosize);
        WebElement detailedprice = driver.findElement(By.cssSelector("div.price-wrapper strong.campaign-price"));
        Dimension detailedpsize = detailedprice.getSize();
        System.out.println(detailedpsize);
        Assert.assertTrue(detailedosize.height < detailedpsize.height);
        Assert.assertTrue(detailedosize.width < detailedpsize.width);

    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
