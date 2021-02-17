package Selenium.TestNG;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

public class SoftAssertion {

    WebDriver driver;
    SoftAssert softAssert;
    Actions actions;


    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        softAssert = new SoftAssert();


    }

    /*
    1. Navigate to google.com
    2. Using soft assert verify the title of home page of google.com
    3. Search for "java oca certification"
    4. Using soft assert verify the url of search results
    5. Using soft assert verify the number of results is greater than 100 000

     */

    @Test

    // #1 variant

    public void test() throws InterruptedException {
        driver.get("https://google.com");
        String actualTitle = driver.getTitle();
        String expectedTitle = "Google";
        softAssert.assertEquals(actualTitle, expectedTitle);
        WebElement searchBar = driver.findElement(By.name("q"));
        searchBar.sendKeys("java oca certification" + Keys.ENTER);

        Thread.sleep(2000);

        softAssert.assertTrue(driver.getCurrentUrl().contains("google"));
        WebElement results = driver.findElement(By.id("result-stats"));
        String resultsText = results.getText();
        System.out.println(resultsText);
        softAssert.assertAll();

        // About 680,000 results (0.42 seconds)
        String[] array = resultsText.split(" ");
        // 680000
        String resultNum = array[1].replace(",", "");
        int endResult = Integer.parseInt(resultNum);
        softAssert.assertTrue(endResult > 100000);
        softAssert.assertAll("Good job Tester");


    }


    // #2 variant
    /*
    @Test
    public void test1() {
        driver.get("https://google.com");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(driver.getTitle().contains("Google"));
        WebElement searchField = driver.findElement(By.name("q"));
        searchField.sendKeys("java oca certification" + Keys.ENTER);
        softAssert.assertTrue(driver.getCurrentUrl().contains("google"));
        String resultString = driver.findElement(By.id("result-stats")).getText();
        resultString = resultString.substring(resultString.indexOf(" "), resultString.indexOf(" ", resultString.indexOf(" ") + 1));
        resultString = resultString.trim();
        System.out.println(resultString);
        if (resultString.contains(",")) {
            resultString = resultString.replace(",", "");
        }

        int resultInt = Integer.parseInt(resultString);
        softAssert.assertTrue(resultInt > 100000);
        softAssert.assertAll();

    }

     */




        @AfterMethod
    public void tearDown(){
        driver.quit();
    }

}

