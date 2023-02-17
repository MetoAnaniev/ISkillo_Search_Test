import ISkilloElements.Header;
import ISkilloElements.LoggedHomePage;
import ISkilloElements.LoginPage;
import ISkilloElements.UserProfilePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import static org.apache.commons.io.FileUtils.cleanDirectory;

public class SearchFieldTests extends ConfigClass{


    @Test (invocationCount = 2)
    public void testSearchFieldOnHomePage() {
        WebDriver driver = getDriver();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.logIn();

        LoggedHomePage loggedHomePage = new LoggedHomePage(driver);
        Assert.assertTrue(loggedHomePage.isUrlLoaded(), "The Home URL is not correct!");

        Header header = new Header(driver);
        header.findUser();
        Assert.assertEquals(header.userText(),"Lora");

    }

    @Test (invocationCount = 2)
    public void testSearchFieldOnProfilePage() {
        WebDriver driver = getDriver();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.logIn();
        LoggedHomePage loggedHomePage = new LoggedHomePage(driver);
        Assert.assertTrue(loggedHomePage.isUrlLoaded(), "The Home URL is not correct!");
        Header header = new Header(driver);
        header.clickProfile();

        header.findUserAndSelect();

        UserProfilePage userProfilePage = new UserProfilePage(driver);
        Assert.assertTrue(userProfilePage.isUrlLoaded(), "The Home URL is not correct!");


    }

    @Test (invocationCount = 2)
    public void testFollowUserInSearchField() {
        WebDriver driver = getDriver();

        LoginPage loginPage = new LoginPage(driver);
        Header header = new Header(driver);
        LoggedHomePage loggedHomePage = new LoggedHomePage(driver);

        loginPage.logIn();
        Assert.assertTrue(loggedHomePage.isUrlLoaded(), "The Home Page URL is not correct!");

        header.findUser();
        header.checkFollowButtonState();
        Assert.assertTrue(header.checkFollowButtonState());
        header.FollowUnfollow();
        Assert.assertEquals( header.followButtonText(), "Unfollow","The user is not Followed!");
    }

    @Test (invocationCount = 2)
    public void testUnFollowUserInSearchField() {
        WebDriver driver = getDriver();

        LoginPage loginPage = new LoginPage(driver);
        Header header = new Header(driver);
        LoggedHomePage loggedHomePage = new LoggedHomePage(driver);

        loginPage.logIn();
        Assert.assertTrue(loggedHomePage.isUrlLoaded(), "The Home Page URL is not correct!");

        header.findUser();
        header.checkUnFollowButtonState();
        Assert.assertTrue(header.checkUnFollowButtonState());
        header.FollowUnfollow();
        Assert.assertEquals( header.followButtonText(), "Follow","The user is not Unfollowed!");
    }
}
