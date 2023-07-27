import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.util.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.openqa.selenium.Keys.ENTER;

public class test_WebDriver {
    private WebDriver driver = null;
    private Logger logger = LogManager.getLogger(test_WebDriver.class);

    @BeforeAll
    public static void webDriverSutup(){
        WebDriverManager.chromedriver().setup();


    }

    @AfterEach
    public void closeAll(){
        if (driver != null){
            driver.close();
            driver.quit();

        }
    }
    @Test

    public void openDuckOtus() {
        final String URL_DUCK = "https://duckduckgo.com/";
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        driver = new ChromeDriver(options);

        By enterOTUS = By.id("searchbox_input");
        By firstSearch = By.xpath("//a[@rel='noopener' and @data-testid='result-title-a']");

        driver.get(URL_DUCK);
        String pageSourse = driver.getPageSource();

        driver.findElement(enterOTUS).sendKeys("ОТУС", ENTER);

        WebElement element = driver.findElement(firstSearch);
        String text = element.getText();
        Assertions.assertEquals("Онлайн‑курсы для профессионалов, дистанционное обучение современным ...", text);
    }

    @Test
    public void photoModal() {
        final String URL_PHOTO = "https://demo.w3layouts.com/demos_new/template_demo/03-10-2020/photoflash-liberty" +
                "-demo_Free/685659620/web/index.html?_ga=2.181802926.889871791.1632394818-2083132868.1632394818";

        ChromeOptions options = new ChromeOptions();
        options.addArguments("kiosk");//
        driver = new ChromeDriver(options);

        driver.get(URL_PHOTO);
        By photo = By.cssSelector(".portfolio-item2.content");
        driver.findElement(photo).click();
        WebElement element = driver.findElement(photo);


//        if(element.isDisplayed()) {
//            System.out.println("Photo is open -  " +element.isDisplayed());
//        }
//        else {
//            System.out.println("Photo is not open - " +element.isDisplayed());
//        }
        Assertions.assertTrue(element.isDisplayed());

    }
    @Test

    public void otusCookie() {


        final String LOG = "vadir56921@iturchia.com";
        final String PASS = "!QAZ3edc";
        final String URL_OTUS = "https://otus.ru";

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);

        driver.get(URL_OTUS);
        //driver.manage().deleteAllCookies();

        driver.findElement(By.cssSelector(".sc-mrx253-0")).click();
        driver.findElement(By.cssSelector("[name='email']")).sendKeys(LOG);
        driver.findElement(By.cssSelector("[type='password']")).sendKeys(PASS, ENTER);
        logger.info(driver.manage().getCookies());
    }
}
