package iSkillo.Elements.pageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.charset.Charset;
import java.time.Duration;
import java.util.Random;

public class Header {
    private final WebDriver driver;
    @FindBy(id = "nav-link-profile")
    private WebElement profileLink;
    @FindBy(id = "nav-link-login")
    private WebElement loginLink;
    @FindBy(id = "search-bar")
    private WebElement searchBar;
    @FindBy(className = "dropdown-container")
    private WebElement dropDownContainer;
    @FindBy(id = "nav-link-new-post")
    private WebElement newPostLink;
    @FindBy(xpath = "//a[text()='Lora']")
    private WebElement userLora;
    @FindBy(xpath = ".//a[text()='Lora']/parent::div/parent::div//button")
    private WebElement followUnfollowButton;
    final String user = "Lora";

    public Header(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickProfile() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(profileLink));
        profileLink.click();
    }
    public void clickLogin() {
        loginLink.click();
    }
    public void clickNewPost() {
    newPostLink.click();
    }
    public void allUsersInSearchBar() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.visibilityOf(searchBar));
        searchBar.sendKeys(" ");
    }
    public void randomCredentialsUsersInSearchBar() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.visibilityOf(searchBar));
        searchBar.sendKeys(generatingRandomString());
    }
    public String generatingRandomString() {
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
        return generatedString;
    }

    public void findUser(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.visibilityOf(searchBar));
        searchBar.sendKeys(user);
        wait.until(ExpectedConditions.visibilityOf(userLora));
    }
    public void findUserAndSelect(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.visibilityOf(searchBar));
        searchBar.sendKeys(user);
        wait.until(ExpectedConditions.elementToBeClickable(userLora));
        userLora.click();
    }
    public void FollowUnfollow() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.visibilityOf(followUnfollowButton));
        followUnfollowButton.click();

    }
    public boolean checkFollowButtonState() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.visibilityOf(followUnfollowButton));
        String actualButtonText = userText();
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
        wait.until(ExpectedConditions.visibilityOf(followUnfollowButton));
        String actualButtonText = userText();
        String buttonText = "Unfollow";
        if (actualButtonText.equals(buttonText)) {
            return true;
        } else {
            wait.until(ExpectedConditions.elementToBeClickable(followUnfollowButton));
            followUnfollowButton.click();}
        return true;
    }
    public boolean dropDownContainer(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        return(dropDownContainer.isDisplayed());
    }
    public String userText() {return userLora.getText();}
    public boolean searchBar(){return searchBar.isDisplayed();}
    public String followButtonText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        return followUnfollowButton.getText();
    }






}
