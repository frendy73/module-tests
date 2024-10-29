package ru.roman.moduletests.login;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import ru.roman.moduletests.BaseTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest extends BaseTest {

    @Test
    public void testSuccessfulLogin() {
        // Переходим на страницу входа
        driver.findElement(By.linkText("My Account")).click();

        // Вводим корректные учетные данные
        driver.findElement(By.id("username")).sendKeys("2123123@mail.pro");
        driver.findElement(By.id("password")).sendKeys("sabvor-5Zuxqe-buzsod");
        driver.findElement(By.name("login")).click();

        // Проверяем, что вход был успешным
        boolean isLoginSuccessful = driver.findElement(By.linkText("Logout")).isDisplayed();
        assertTrue(isLoginSuccessful, "Вход не был успешным.");
    }
}
