import iSkilloElements.*;
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
        return new Object[][]{{"m_puh", "metodi86"}, //login with username
                {"testMail1@gmail.com", "Dimitar1.Tarkalanov1"}, //login with email
                {"testAdmin@gmail.com", "Admin1.User1"}, //login with admin user
                {"manager@gmail.com", "Manager1.Use1"} //login with manager user
        };
    }

    //REQ-1.1: The search field is available only for the logged users.
    @Test(dataProvider = "getUsers")
    public void testVisibilityOfSearchBarAfterLogin(String username, String password) {
        WebDriver driver = getDriver();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo();
        loginPage.logIn(username,password);

        Header header = new Header(driver);
        Assert.assertTrue(header.searchBar());
    }


    //REQ-2: Clicking in the search field and pressing space shows all available users.
    @Test(retryAnalyzer = RetryAnalyzer.class, dataProvider = "getUsers")
    public void testShowAllUsersInSearchField(String password, String username) {
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
    //REQ-5: Clicking on the profile picture or name opens the user’s profile page.
    @Test (retryAnalyzer = RetryAnalyzer.class, dataProvider = "getUsers")
    public void testSearchFieldFindUserOnHomePage(String password, String username) {
               WebDriver driver = getDriver();
               LoginPage loginPage = new LoginPage(driver);
               loginPage.logIn(username,password);
               LoggedHomePage loggedHomePage = new LoggedHomePage(driver);
               Assert.assertTrue(loggedHomePage.isUrlLoaded(), "The Home URL is not correct!");

               Header header = new Header(driver);
               header.findUser();
               Assert.assertEquals(header.userText(),"Lora");

           }

    @Test (retryAnalyzer = RetryAnalyzer.class, dataProvider = "getUsers")
    public void testSearchFieldOnProfilePage(String password, String username) {
        WebDriver driver = getDriver();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.logIn(username,password);
        LoggedHomePage loggedHomePage = new LoggedHomePage(driver);
        Assert.assertTrue(loggedHomePage.isUrlLoaded(), "The Home URL is not correct!");
        Header header = new Header(driver);
        header.clickProfile();

        header.findUserAndSelect();

        UserProfilePage userProfilePage = new UserProfilePage(driver);
        Assert.assertTrue(userProfilePage.isUrlLoaded(), "The Home URL is not correct!");
    }

    //REQ-6: Clicking Follow/Unfollow – follows or unfollows the relevant user
    @Test (retryAnalyzer = RetryAnalyzer.class, dataProvider = "getUsers")
    public void testFollowUserInSearchField(String password, String username) {
        WebDriver driver = getDriver();

        LoginPage loginPage = new LoginPage(driver);
        Header header = new Header(driver);
        LoggedHomePage loggedHomePage = new LoggedHomePage(driver);

        loginPage.logIn(username,password);
        Assert.assertTrue(loggedHomePage.isUrlLoaded(), "The Home Page URL is not correct!");

        header.findUser();
        header.checkFollowButtonState();
        header.FollowUnfollow();
        Assert.assertEquals( header.followButtonText(), "Unfollow","The user is not Followed!");
    }

    @Test (retryAnalyzer = RetryAnalyzer.class, dataProvider = "getUsers")
    public void testUnFollowUserInSearchField(String password, String username) {
        WebDriver driver = getDriver();

        LoginPage loginPage = new LoginPage(driver);
        Header header = new Header(driver);
        LoggedHomePage loggedHomePage = new LoggedHomePage(driver);

        loginPage.logIn(username,password);
        Assert.assertTrue(loggedHomePage.isUrlLoaded(), "The Home Page URL is not correct!");

        header.findUser();
        header.checkUnFollowButtonState();
        Assert.assertTrue(header.checkUnFollowButtonState());
        header.FollowUnfollow();
        Assert.assertEquals( header.followButtonText(), "Follow","The user is not Unfollowed!");
    }

    //REQ-9: If no results are found nothing is shown.
    @Test(dataProvider = "getUsers")
    public void testNoResultsFoundInSearchField(String password, String username) {
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
