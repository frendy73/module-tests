package ru.roman.moduletests.search;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import ru.roman.moduletests.BaseTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchTest extends BaseTest {

    @Test
    public void testSearchWithResults() {
        // Вводим ключевое слово в поле поиска
        driver.findElement(By.id("s")).sendKeys("HTML" + Keys.ENTER);

        // Проверяем, что результаты поиска отображаются
        boolean resultsDisplayed = driver.findElements(By.xpath("/html/body/div[1]/div[2]/div/div/div")).size() > 0;
        assertTrue(resultsDisplayed, "Результаты поиска не отображаются.");
    }

    @Test
    public void testSearchWithoutResults() {
        // Вводим несуществующий запрос и нажимаем Enter
        driver.findElement(By.id("s")).sendKeys("NonExistentProduct" + Keys.ENTER);

        // Проверяем, что отображается сообщение об отсутствии результатов
        boolean noResultsMessageDisplayed = driver.findElements(By.xpath("/html/body/div[1]/div[2]/div/div/div")).size() == 0;
        assertTrue(noResultsMessageDisplayed, "Сообщение об отсутствии результатов не отображается.");
    }
}
