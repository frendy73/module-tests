package ru.roman.moduletests.register;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.roman.moduletests.BaseTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegistrationTest extends BaseTest {
    private static final String PASSWORD = "erfijeri34r34$";
    private static final String REGISTER_XPATH = "/html/body/div[1]/div[2]/div/div/div/div/div[1]/div/div[2]/form/p[3]/input[3]";

    @Test
    public void testSuccessfulRegistration() throws InterruptedException {
        // Переходим на страницу регистрации
        driver.findElement(By.linkText("My Account")).click();

        // Вводим данные для регистрации
        driver.findElement(By.id("reg_email")).sendKeys("newuser" + System.currentTimeMillis() + "@example.com");
        driver.findElement(By.id("reg_password")).sendKeys(PASSWORD);

        WebElement registerButton = driver.findElement(By.xpath(REGISTER_XPATH));

        // Нужно протыкать кнопку не один раз. Первый раз - валидациы пароль, второй раз - регистрация. Мы просто отправляем sendKeys, нет иммитации живого ввода
        Thread.sleep(1000L);
        registerButton.click();
        Thread.sleep(1000L);
        registerButton.click();

        // Проверяем, что регистрация была успешной
        boolean isRegistrationSuccessful = driver.findElement(By.linkText("Logout")).isDisplayed();
        assertTrue(isRegistrationSuccessful, "Регистрация не была успешной.");
    }

    @Test
    public void testDuplicateRegistration() throws InterruptedException {
        driver.get("http://practice.automationtesting.in/");

        // Переходим на страницу регистрации
        driver.findElement(By.linkText("My Account")).click();

        // Вводим данные для регистрации
        driver.findElement(By.id("reg_email")).sendKeys("2123123@mail.pro");
        driver.findElement(By.id("reg_password")).sendKeys(PASSWORD);

        WebElement registerButton = driver.findElement(By.xpath(REGISTER_XPATH));

        // Нужно протыкать кнопку не один раз. Первый раз - валидациы пароль, второй раз - регистрация. Мы просто отправляем sendKeys, нет иммитации живого ввода
        Thread.sleep(1000L);
        registerButton.click();
        Thread.sleep(1000L);
        registerButton.click();

        // Проверяем, что отображается сообщение об ошибке
        boolean isErrorDisplayed = driver.findElement(By.cssSelector(".woocommerce-error")).isDisplayed();
        assertTrue(isErrorDisplayed, "Сообщение об ошибке не отображается при повторной регистрации.");
    }
}
