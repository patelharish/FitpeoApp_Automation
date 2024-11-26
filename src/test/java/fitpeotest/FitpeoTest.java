package fitpeotest;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FitpeoTest {
    WebDriver driver;

    // This method is executed before each test to set up the environment.
    @BeforeTest
    public void setUp() {
        // Initialize the ChromeDriver
        driver = new ChromeDriver();        
        // Navigate to the Fitpeo homepage
        driver.get("https://www.fitpeo.com/");
        // Maximize the browser window
        driver.manage().window().maximize();
        // Click on the 5th element in a series of elements to navigate i.e. Revenue Calculator Page
        driver.findElement(By.xpath("(//div[@class=\"satoshi MuiBox-root css-5ty6tm\"])[5]")).click();
    }

    // Test case to adjust the slider
    @Test(priority = 1)
    public void adjustSliderTest(){    
        // Set an implicit wait for elements to load
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));    

        // Create a JavascriptExecutor instance for executing JavaScript
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // Scroll down the page by 300 pixels
        js.executeScript("window.scrollBy(0,300)", "");

        // Locate the slider element to interact with
        WebElement slider = driver.findElement(By.xpath("(//span[@class='MuiSlider-thumb MuiSlider-thumbSizeMedium MuiSlider-thumbColorPrimary MuiSlider-thumb MuiSlider-thumbSizeMedium MuiSlider-thumbColorPrimary css-1sfugkh'])[1]"));
        
        // Create an Actions instance to perform actions like dragging
        Actions ac = new Actions(driver);
        // Drag and drop the slider by 94 pixels along the x-axis (horizontal movement)
        ac.dragAndDropBy(slider, 94, 0).perform();
    }

    // Test case to update the value in the text field
    @Test(priority = 2)
    public void updateTextFieldTest(){
        // Set an implicit wait for elements to load
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        // Create a JavascriptExecutor instance for executing JavaScript
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // Scroll down the page by 300 pixels
        js.executeScript("window.scrollBy(0,300)", "");
        
        // Locate the input field of type 'number'
        WebElement input = driver.findElement(By.xpath("//input[@type='number']"));
        // Select the existing text in the input field
        input.sendKeys(Keys.CONTROL + "a");
        // Update the text field with a new value
        input.sendKeys("560");
    }

    // Test case to select multiple CPT codes
    @Test(priority = 3)
    public void selectCPTCodeTest() throws InterruptedException {
        // Set an implicit wait for elements to load
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        
        // Create a JavascriptExecutor instance for executing JavaScript
        JavascriptExecutor js = (JavascriptExecutor) driver;        
        // Scroll down the page by 300 pixels
        js.executeScript("window.scrollBy(0,300)", "");
        
        // Locate the input field for entering the CPT code
        WebElement input = driver.findElement(By.xpath("//input[@type='number']"));
        // Select the existing text in the input field
        input.sendKeys(Keys.CONTROL + "a");
        // Enter a new CPT code value
        input.sendKeys("820");        
        
        // Scroll further down the page by 600 pixels
        js.executeScript("window.scrollBy(0,600)", "");
        
        // Select the checkboxes for multiple CPT codes
        // Select CPT-99091
        driver.findElement(By.xpath("(//input[@type='checkbox'])[1]")).click();
        // Select CPT-99453
        driver.findElement(By.xpath("(//input[@type='checkbox'])[2]")).click();
        // Select CPT-99454
        driver.findElement(By.xpath("(//input[@type='checkbox'])[3]")).click();
        // Select CPT-99474
        driver.findElement(By.xpath("(//input[@type='checkbox'])[8]")).click();
        
        // Verify that the "Total Recurring Reimbursement" value is correct
        String expectedRecurringReimbursement = "$110700";
        String actualRecurringReimbursement = driver.findElement(By.xpath("(//p[@class='MuiTypography-root MuiTypography-body1 inter css-12bch19'])[3]")).getText();
        
        // Assert that the expected and actual values match
        Assert.assertEquals(expectedRecurringReimbursement, actualRecurringReimbursement);
    }

    // This method is executed after all tests to clean up
    @AfterTest
    public void tearDown() {
        // Close the browser window
        driver.close();
        // Quit the WebDriver session
        driver.quit();
    }
}

