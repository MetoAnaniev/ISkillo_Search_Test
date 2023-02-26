package iSkillo.Elements.pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoggedHomePage {
    public static final String PAGE_URL = "http://training.skillo-bg.com:4300/posts/all";
    private final WebDriver driver;
    public LoggedHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigateTo() {
        this.driver.get(PAGE_URL);
    }
    public boolean isUrlLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.urlToBe(PAGE_URL));

    }
}
