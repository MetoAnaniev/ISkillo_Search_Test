package iSkillo.Elements.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.charset.Charset;
import java.time.Duration;
import java.util.Random;

public class Header {

    private final WebDriver driver;

    public Header(WebDriver driver) {

        this.driver = driver;
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    public void clickLogin() {
        WebElement loginLink = driver.findElement(By.id("nav-link-login"));
        loginLink.click();
    }
    public void clickNewPost() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement postLink = wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-link-new-post")));
        postLink.click();
    }

    public void clickProfile() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement profileLink = wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-link-profile")));
        profileLink.click();
    }

    public void allUsersInSearchBar() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        WebElement searchBar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search-bar")));
        searchBar.sendKeys(" ");
    }

    public void randomCredentialsUsersInSearchBar() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        WebElement searchBar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search-bar")));
        searchBar.sendKeys(generatingRandomString());
    }

    public void findUser(String user){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        WebElement searchBar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search-bar")));
        searchBar.sendKeys(user);
        WebElement findUser = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(user)));

    }
    public void findUserAndSelect(String user){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        WebElement searchBar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search-bar")));
        searchBar.sendKeys(user);

        WebElement findUser =  wait.until(ExpectedConditions.elementToBeClickable(By.linkText(user)));
        findUser.click();
    }

    public void followUnfollow(String user) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        WebElement followUnfollowButton = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath(String.format(".//a[text()='%s']/parent::div/parent::div//button",user))));
        followUnfollowButton.click();

    }
    public boolean checkFollowButtonState(String user) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        WebElement followUnfollowButton =driver.findElement
                (By.xpath(String.format(".//a[text()='%s']/parent::div/parent::div//button",user)));
        wait.until(ExpectedConditions.visibilityOf(followUnfollowButton));
        String actualButtonText = driver.findElement
                (By.xpath(String.format(".//a[text()='%s']/parent::div/parent::div//button",user))).getText();
        String buttonText = "Follow";
            if (actualButtonText.equals(buttonText)) {
                return true;
            } else {
                wait.until(ExpectedConditions.elementToBeClickable(followUnfollowButton));
                followUnfollowButton.click();}
        return true;
    }
    public boolean checkUnFollowButtonState(String user) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        WebElement followUnfollowButton =driver.findElement
                (By.xpath(String.format(".//a[text()='%s']/parent::div/parent::div//button",user)));

        String actualButtonText = driver.findElement
                (By.xpath(String.format(".//a[text()='%s']/parent::div/parent::div//button",user))).getText();
        String buttonText = "Unfollow";
        if (actualButtonText.equals(buttonText)) {
            return true;
        } else {
            wait.until(ExpectedConditions.elementToBeClickable(followUnfollowButton));
            followUnfollowButton.click();}
        return true;
    }
    public String followButtonText(String user) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement followButtonText = driver.findElement
                (By.xpath(String.format(".//a[text()='%s']/parent::div/parent::div//button",user)));
        return followButtonText.getText();
    }
    public String userText(String user) {
        WebElement userText = driver.findElement(By.linkText(String.format("%s",user)));
        return userText.getText();
    }
    public boolean searchBar(){return(driver.findElement(By.id("search-bar")).isDisplayed());}
    public boolean dropDownContainer(){
        return(driver.findElement(By.className("dropdown-container")).isDisplayed());
    }

    public String generatingRandomString() {
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
        return generatedString;
    }




}