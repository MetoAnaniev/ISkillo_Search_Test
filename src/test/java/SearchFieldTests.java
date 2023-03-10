import iSkillo.Elements.pageObject.Header;
import iSkillo.Elements.pageObject.LoggedHomePage;
import iSkillo.Elements.pageObject.LoginPage;
import iSkillo.Elements.pageObject.UserProfilePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
// Tests are based on Software Requirements Specification
       //iSkillo project
       //Version 3.0
       //section - 3.10 "Search field"


public class SearchFieldTests extends ConfigClass{

    @DataProvider(name = "getUsers")
    public Object[][] getUsers() {
        return new Object[][]{{"m_puh", "metodi86", "Lora"},//login with username
                {"testAdmin@gmail.com", "Admin1.User1", "M_Puh"},
        };
    }

    //REQ-1.1: The search field is available only for the logged users.
    @Test(dataProvider = "getUsers")
    public void testVisibilityOfSearchBarAfterLogin(String username, String password, String user) {
        WebDriver driver = getDriver();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.logIn(username,password);

        Header header = new Header(driver);
        Assert.assertTrue(header.searchBar());
    }


    //REQ-2: Clicking in the search field and pressing space shows all available users.
    @Test(retryAnalyzer = RetryAnalyzer.class, dataProvider = "getUsers")
    public void testShowAllUsersInSearchField(String username, String password, String user) {
        WebDriver driver = getDriver();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.logIn(username,password);
        LoggedHomePage loggedHomePage = new LoggedHomePage(driver);
        Assert.assertTrue(loggedHomePage.isUrlLoaded(), "The Home URL is not correct!");
        Header header = new Header(driver);
        header.allUsersInSearchBar();
        Assert.assertTrue(header.dropDownContainer());

    }

    //REQ-3: Search field is working using filter as you type functionality.
    //REQ-4: The results shown contains the profile picture, the name of the user and Follow/Unfollow button.
    //REQ-5: Clicking on the profile picture or name opens the user???s profile page.
    @Test (retryAnalyzer = RetryAnalyzer.class, dataProvider = "getUsers")
    public void testSearchFieldFindUserOnHomePage(String username, String password, String user) {
               WebDriver driver = getDriver();
               LoginPage loginPage = new LoginPage(driver);
               loginPage.logIn(username,password);
               LoggedHomePage loggedHomePage = new LoggedHomePage(driver);
               Assert.assertTrue(loggedHomePage.isUrlLoaded(), "The Home URL is not correct!");

               Header header = new Header(driver);
               header.findUser(user);
               Assert.assertEquals(header.userText(user),user);

           }

    @Test (retryAnalyzer = RetryAnalyzer.class, dataProvider = "getUsers")
    public void testSearchFieldOnProfilePage(String username, String password, String user) {
        WebDriver driver = getDriver();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.logIn(username,password);
        LoggedHomePage loggedHomePage = new LoggedHomePage(driver);
        Assert.assertTrue(loggedHomePage.isUrlLoaded(), "The Home URL is not correct!");
        Header header = new Header(driver);
        header.clickProfile();

        header.findUserAndSelect(user);

        UserProfilePage userProfilePage = new UserProfilePage(driver);
        Assert.assertTrue(userProfilePage.isUrlLoaded(), "The Home URL is not correct!");
    }

    //REQ-6: Clicking Follow/Unfollow ??? follows or unfollows the relevant user
    @Test (retryAnalyzer = RetryAnalyzer.class, dataProvider = "getUsers")
    public void testFollowUserInSearchField(String username, String password, String user) {
        WebDriver driver = getDriver();

        LoginPage loginPage = new LoginPage(driver);
        Header header = new Header(driver);
        LoggedHomePage loggedHomePage = new LoggedHomePage(driver);

        loginPage.logIn(username,password);
        Assert.assertTrue(loggedHomePage.isUrlLoaded(), "The Home Page URL is not correct!");

        header.findUser(user);
        header.checkFollowButtonState(user);
        Assert.assertTrue(header.checkFollowButtonState(user));
        header.followUnfollow(user);
        Assert.assertEquals( header.followButtonText(user), "Unfollow","The user is not Followed!");
    }

    @Test (retryAnalyzer = RetryAnalyzer.class, dataProvider = "getUsers")
    public void testUnFollowUserInSearchField(String username, String password, String user) {
        WebDriver driver = getDriver();

        LoginPage loginPage = new LoginPage(driver);
        Header header = new Header(driver);
        LoggedHomePage loggedHomePage = new LoggedHomePage(driver);

        loginPage.logIn(username,password);
        Assert.assertTrue(loggedHomePage.isUrlLoaded(), "The Home Page URL is not correct!");

        header.findUser(user);
        header.checkUnFollowButtonState(user);
        Assert.assertTrue(header.checkUnFollowButtonState(user));
        header.followUnfollow(user);
        Assert.assertEquals( header.followButtonText(user), "Follow","The user is not Unfollowed!");
    }

    //REQ-9: If no results are found nothing is shown.
    @Test(dataProvider = "getUsers")
    public void testNoResultsFoundInSearchField(String username, String password, String user) {
               WebDriver driver = getDriver();

               LoginPage loginPage = new LoginPage(driver);
               loginPage.logIn(username,password);
               LoggedHomePage loggedHomePage = new LoggedHomePage(driver);
               Assert.assertTrue(loggedHomePage.isUrlLoaded(), "The Home URL is not correct!");
               Header header = new Header(driver);
               header.randomCredentialsUsersInSearchBar();
               Assert.assertFalse(header.dropDownContainer(),"Users are found!");
           }
}
