import ISkilloElements.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;

import javax.lang.model.element.Element;
       // Tests are based on Software Requirements Specification
       //iSkillo project
       //Version 3.0
       //section - 3.10 "Search field"
public class SearchFieldTests extends ConfigClass{

    //REQ-1.1: The search field is available only for the logged users.
    @Test
    public void testVisibilityOfSearchBarAfterLogin() {
        WebDriver driver = getDriver();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.logIn();

        Header header = new Header(driver);
        Assert.assertTrue(header.searchBar());
    }

    //REQ-1.2: The search field is available only for the logged users.
   /* @Test
    public void testNotVisibleSearchFieldBeforeLogIn() {
        WebDriver driver = getDriver();
        HomePage homePage = new HomePage(driver);
        homePage.navigateTo();
        Header header = new Header(driver);
        Assert.assertTrue(ExpectedConditions.invisibilityOf(header.searchField));

    }*/


    //REQ-2: Clicking in the search field and pressing space shows all available users.
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

    //REQ-3: Search field is working using filter as you type functionality.
    //REQ-4: The results shown contains the profile picture, the name of the user and Follow/Unfollow button.
    //REQ-5: Clicking on the profile picture or name opens the user’s profile page.
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

    //REQ-6: Clicking Follow/Unfollow – follows or unfollows the relevant user
    @Test
    public void testFollowUserInSearchField() {
        WebDriver driver = getDriver();

        LoginPage loginPage = new LoginPage(driver);
        Header header = new Header(driver);
        LoggedHomePage loggedHomePage = new LoggedHomePage(driver);

        loginPage.logIn();
        Assert.assertTrue(loggedHomePage.isUrlLoaded(), "The Home Page URL is not correct!");

        header.findUser();
        header.checkFollowButtonState();
        Assert.assertEquals( header.followButtonText(), "Follow","The user is not Followed!");
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

    //REQ-9: If no results are found nothing is shown.
    @Test
    public void testNoResultsFoundInSearchField() {
               WebDriver driver = getDriver();

               LoginPage loginPage = new LoginPage(driver);
               loginPage.logIn();
               LoggedHomePage loggedHomePage = new LoggedHomePage(driver);
               Assert.assertTrue(loggedHomePage.isUrlLoaded(), "The Home URL is not correct!");
               Header header = new Header(driver);
               header.randomCredentialsUsersInSearchBar();
               Assert.assertFalse(header.dropDownContainer(),"Users are found!");
           }
}
