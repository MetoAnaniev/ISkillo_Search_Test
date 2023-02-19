import ISkilloElements.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;

import javax.lang.model.element.Element;

public class SearchFieldTests extends ConfigClass{

    @Test (retryAnalyzer = RetryAnalyzer.class)
    public void testSearchFieldFindUserOnHomePage() {
        WebDriver driver = getDriver();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.logIn();
        LoggedHomePage loggedHomePage = new LoggedHomePage(driver);
        Assert.assertTrue(loggedHomePage.isUrlLoaded(), "The Home URL is not correct!");

        Header header = new Header(driver);
        header.findUser();
        Assert.assertEquals(header.userText(),"Lora");

    }

    @Test
    public void testVisibilityOfSearchBarAfterLogin() {
        WebDriver driver = getDriver();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.logIn();

        Header header = new Header(driver);
        Assert.assertTrue(header.searchBar());
    }

   /* @Test
    public void testNotVisibleSearchFieldBeforeLogIn() {
        WebDriver driver = getDriver();
        HomePage homePage = new HomePage(driver);
        homePage.navigateTo();
        Header header = new Header(driver);
        Assert.assertTrue(ExpectedConditions.invisibilityOf(header.searchField));

    }*/

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testShowAllUsersInSearchField() {
        WebDriver driver = getDriver();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.logIn();
        LoggedHomePage loggedHomePage = new LoggedHomePage(driver);
        Assert.assertTrue(loggedHomePage.isUrlLoaded(), "The Home URL is not correct!");
        Header header = new Header(driver);
        header.allUsersInSearchBar();
        Assert.assertTrue(header.dropDownContainer());

    }

    @Test
    public void testNoResultsFoundInSearchField() {
        WebDriver driver = getDriver();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.logIn();
        LoggedHomePage loggedHomePage = new LoggedHomePage(driver);
        Assert.assertTrue(loggedHomePage.isUrlLoaded(), "The Home URL is not correct!");
        Header header = new Header(driver);
        header.randomCredentialsUsersInSearchBar();
        Assert.assertTrue(!header.dropDownContainer(),"Users are found!");
    }

    @Test (retryAnalyzer = RetryAnalyzer.class)
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

    @Test (retryAnalyzer = RetryAnalyzer.class)
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

    @Test (retryAnalyzer = RetryAnalyzer.class)
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
