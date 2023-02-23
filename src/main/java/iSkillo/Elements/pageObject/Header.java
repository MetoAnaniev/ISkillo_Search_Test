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

    private WebDriver driver;
    final String user = "Lora";

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

    public void findUser(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        WebElement searchBar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search-bar")));
        searchBar.sendKeys(user);
        WebElement findUser = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Lora']")));

    }
    public void findUserAndSelect(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        WebElement searchBar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search-bar")));
        searchBar.sendKeys(user);


        WebElement findUser = driver.findElement(By.xpath("//a[text()='Lora']"));
        wait.until(ExpectedConditions.elementToBeClickable(findUser));
        findUser.click();
    }

    public void FollowUnfollow() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        WebElement followUnfollowButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//a[text()='Lora']/parent::div/parent::div//button")));
        followUnfollowButton.click();

    }
    public boolean checkFollowButtonState() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        WebElement followUnfollowButton =driver.findElement(By.xpath(".//a[text()='Lora']/parent::div/parent::div//button"));
        wait.until(ExpectedConditions.visibilityOf(followUnfollowButton));
        String actualButtonText = driver.findElement(By.xpath(".//a[text()='Lora']/parent::div/parent::div//button")).getText();
        String buttonText = "Follow";
            if (actualButtonText.equals(buttonText)) {
                return true;
            } else {
                wait.until(ExpectedConditions.elementToBeClickable(followUnfollowButton));
                followUnfollowButton.click();}
        return true;
    }
    public boolean checkUnFollowButtonState() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        WebElement followUnfollowButton =driver.findElement(By.xpath(".//a[text()='Lora']/parent::div/parent::div//button"));

        String actualButtonText = driver.findElement(By.xpath(".//a[text()='Lora']/parent::div/parent::div//button")).getText();
        String buttonText = "Unfollow";
        if (actualButtonText.equals(buttonText)) {
            return true;
        } else {
            wait.until(ExpectedConditions.elementToBeClickable(followUnfollowButton));
            followUnfollowButton.click();}
        return true;
    }
    public String followButtonText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement followButtonText = driver.findElement(By.xpath(".//a[text()='Lora']/parent::div/parent::div//button"));
        return followButtonText.getText();
    }
    public String userText() {
        WebElement userText = driver.findElement(By.xpath("//a[text()='Lora']"));

        return userText.getText();
    }
    public boolean searchBar(){return(driver.findElement(By.id("search-bar")).isDisplayed());}
    public boolean dropDownContainer(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        return(driver.findElement(By.className("dropdown-container")).isDisplayed());
    }

    public String generatingRandomString() {
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
        return generatedString;
    }



}