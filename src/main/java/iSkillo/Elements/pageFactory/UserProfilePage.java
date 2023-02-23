package iSkillo.Elements.pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class UserProfilePage {
    private final WebDriver driver;
    public static final String PAGE_URL = "http://training.skillo-bg.com:4300/users/";
    public UserProfilePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void navigateTo() {
        this.driver.get(iSkillo.Elements.pageObject.UserProfilePage.PAGE_URL);
    }

}
