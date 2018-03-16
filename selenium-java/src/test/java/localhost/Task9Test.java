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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Task9Test {

    private WebDriver driver;
    private WebDriverWait wait;


   @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);

        driver.navigate().to("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(ExpectedConditions.titleContains("My Store"));

       driver.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");
       wait.until(ExpectedConditions.titleContains("Countries"));
    }

    @Test
    public void task9aTest() {
       ArrayList<String> obtainedList = new ArrayList<>();
       List<WebElement> countries = driver.findElements(By.xpath("//tr[@class='row']//td[5]"));
        for (WebElement we:countries) {
            obtainedList.add(we.getAttribute("innerText"));
        }
        System.out.println("obtainedList" + obtainedList);

        ArrayList<String> sortedList = new ArrayList<>();
        for (String s:obtainedList) {
            sortedList.add(s);
        }

        Collections.sort(sortedList);
        System.out.println("sortedList" + sortedList);
        Assert.assertTrue(sortedList.equals(obtainedList));
   }

   @Test
   public void task9bTest() {
       List<WebElement> zones = driver.findElements(By.xpath("//tr[@class='row']//td[6]"));
       for (int i=1; i <= zones.size(); i++) {
           zones = driver.findElements(By.xpath("//tr[@class='row']//td[6]"));
           String number = zones.get(i-1).getText();
           System.out.println(number);

           if (!number.contains("0")) {
               System.out.println(i);
               driver.findElement(By.xpath("//tr[@class='row'][38]//a[@title='Edit']")).click();
               wait.until(ExpectedConditions.titleContains("Edit Country"));

               ArrayList<String> obtainedList = new ArrayList<>();
               List<WebElement> states = driver.findElements(By.xpath("//table[@id='table-zones']//td[3]"));
               for (WebElement el : states) {
                   if (el.getText().length() != 0) {
                       obtainedList.add(el.getText());
                   }
               }
               System.out.println("obtainedList" + obtainedList);

               ArrayList<String> sortedList = new ArrayList<>();
               for (String s : obtainedList) {
                   sortedList.add(s);
               }

               Collections.sort(sortedList);
               System.out.println("sortedList" + sortedList);
               Assert.assertTrue(sortedList.equals(obtainedList));


           }
       }
   }

   @Test
   public void task9_2Test() {
       driver.navigate().to(" http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
       wait.until(ExpectedConditions.titleContains("Geo Zones"));
       List<WebElement> geo_zones = driver.findElements(By.cssSelector("tr.row a[title='Edit']"));
       System.out.println(geo_zones.size());
       int i=1;
       while (i <= geo_zones.size()){
           geo_zones = driver.findElements(By.cssSelector("tr.row a[title='Edit']"));
           geo_zones.get(i-1).click();
           wait.until(ExpectedConditions.titleContains("Edit Geo Zone"));

           ArrayList<String> obtainedList = new ArrayList<>();
           List<WebElement> states = driver.findElements(By.xpath("//select[contains(@name, '[zone_code]')]//option[@selected='selected']"));
           for (WebElement state : states) {
               obtainedList.add(state.getText());
           }
           System.out.println("obtainedList" + obtainedList);

           ArrayList<String> sortedList = new ArrayList<>();
           for (String s : obtainedList) {
               sortedList.add(s);
           }

           Collections.sort(sortedList);
           System.out.println("sortedList" + sortedList);
           Assert.assertTrue(sortedList.equals(obtainedList));
           driver.navigate().to(" http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
           i++;
       }

   }

    @After
    public void stop() {
       driver.quit();
       driver = null;
    }

}
