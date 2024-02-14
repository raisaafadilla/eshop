package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
public class CreateProductFunctionalTest {

    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String productCreationUrl;

    private WebDriver driver;

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        productCreationUrl = String.format("%s:%d/product/create", testBaseUrl, serverPort);
        driver.get(productCreationUrl);
    }

    @Test
    void testCreateAndVerifyProduct() {
        var productName = "Matcha Latte";
        var productQuantity = 20;

        fillProductCreationForm(driver, productName, productQuantity);
        submitProductCreationForm(driver);
        waitForProductListPage(driver);
        assertProductInList(driver, productName);
    }

    private void fillProductCreationForm(WebDriver driver, String productName, int productQuantity) {
        var productNameInput = driver.findElement(By.id("nameInput"));
        productNameInput.clear();
        productNameInput.sendKeys(productName);

        var productQuantityInput = driver.findElement(By.id("quantityInput"));
        productQuantityInput.clear();
        productQuantityInput.sendKeys(String.valueOf(productQuantity));
    }

    private void submitProductCreationForm(WebDriver driver) {
        var submitButton = driver.findElement(By.tagName("button"));
        submitButton.click();
    }

    private void waitForProductListPage(WebDriver driver) {
        var wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Product List']")));
    }

    private void assertProductInList(WebDriver driver, String productName) {
        var createdProduct = driver.findElement(By.xpath("//td[contains(text(), '" + productName + "')]"));
        assertEquals(productName, createdProduct.getText());
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}














