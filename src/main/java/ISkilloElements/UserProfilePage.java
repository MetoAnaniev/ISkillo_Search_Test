package ISkilloElements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UserProfilePage {

    public static final String PAGE_URL = "http://training.skillo-bg.com:4300/users/";
    private final WebDriver driver;

    public UserProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateTo() {
        this.driver.get(UserProfilePage.PAGE_URL);
    }
    public boolean isUrlLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.urlContains(UserProfilePage.PAGE_URL));

    }
}
