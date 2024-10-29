package ru.roman.moduletests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("http://practice.automationtesting.in/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Закрываем оверлей, если он присутствует
        closeOverlayIfPresent(wait);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    protected void closeOverlayIfPresent(WebDriverWait wait) {
        try {
            // Ожидаем появления оверлея
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[3]/div[2]/div[1]/div[2]/div[2]/button[1]/p")));

            // Кликаем по кнопке закрытия оверлея
            driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[1]/div[2]/div[2]/button[1]/p")).click();

            // Ждем, пока оверлей исчезнет
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("/html/body/div[3]/div[2]/div[1]/div[2]/div[2]/button[1]/p")));
        } catch (TimeoutException e) {
            // Если оверлей не появился в течение ожидания, продолжаем тест
        }
    }
}
