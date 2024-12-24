import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class CaptchaTest {
    @Test
    public void FirstTest() {
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:/Users/kisel/Downloads/chrome-win64/chrome-win64/chrome.exe");
        options.addArguments("--remote-allow-origins=*");
        System.setProperty("webdriver.chrome.driver", "C:/webTest/drivers/chromedriver.exe");
        ChromeDriver driver = new ChromeDriver(options);

        driver.get("https://mail.ru");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // нажать "войти"
        driver.findElement(By.xpath("//button[@class='resplash-btn resplash-btn_primary ckc-dhdf-1ebh38x']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // переключиться на окно авторизации
        driver.switchTo().frame(driver.findElement(By.className("ag-popup__frame__layout__iframe")));

        // ввести логин
        WebElement login = driver.findElement(By.className("input-0-2-71"));
        login.clear();
        login.sendKeys("professionaltester");

        // перейти к вводу пароля
        driver.findElement(By.xpath("//button[@data-test-id='next-button']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        // выбрать авторизацию через пароль
        driver.findElement(By.xpath("//button[@data-test-id='bind-screen-vkid-change-restore-type-btn']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        // ввести пароль
        WebElement password = driver.findElement(By.xpath("//input[@name='password']"));
        password.clear();
        password.sendKeys("toptester123");

        // войти
        driver.findElement(By.xpath("//button[@data-test-id='submit-button']")).click();

        // выйти из фрейма авторизации
        String handle = driver.getWindowHandle();
        driver.switchTo().window(handle);

        // проверить содержимое
        Assert.assertEquals("Хотим убедиться, что это вы", driver.findElement(By.xpath("//h1[@data-test-id='header-text-recaptcha-inter']")).getText());

        // нажать "назад"
        driver.findElement(By.xpath("//button[@data-test-id='recaptcha-inter-back']")).click();
        //driver.findElement(By.className("innerText-0-2-103 inner-0-2-101")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.quit();
    }
}
