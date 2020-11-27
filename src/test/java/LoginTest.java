//////////////////////////////////////////
// Alexandre Vryghem - r0747249         //
// Mathias Van den Cruijce - r0785409   //
//////////////////////////////////////////

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.*;

public class LoginTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\alexa\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @After
    public void clean() {
        driver.quit();
    }

    @Test
    public void test_Login_LoginWithValidUseridAndMatchingPassword_LoginSuccessful() {
        RegisterPage registerPage = PageFactory.initElements(driver, RegisterPage.class);
        String email = generateRandomEmailInOrderToRunTestMoreThanOnce("anakin.skywalker@hotmail.com");
        registerPage.addValidPerson("Anakin", "Skywalker", email, "t");

        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.setEmail(email);
        homePage.setPassword("t");
        homePage.submitLogIn();

        assertEquals("Home", homePage.getTitle());
        assertTrue(homePage.hasWelcomeMessage("Welcome Anakin!"));
    }

    @Test
    public void test_Login_LoginWithEmptyUserid_LoginUnsuccessful() {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.setEmail("");
        homePage.setPassword("t");
        homePage.submitLogIn();

        assertEquals("Home", homePage.getTitle());
        assertTrue(homePage.hasErrorMessage("No valid userid/password"));
    }

    @Test
    public void test_Login_LoginWithEmptyPassword_LoginUnsuccessful() {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.setEmail("ahsoka.tano@hotmail.com");
        homePage.setPassword("");
        homePage.submitLogIn();

        assertEquals("Home", homePage.getTitle());
        assertTrue(homePage.hasErrorMessage("No valid userid/password"));
    }

    @Test
    public void test_Login_LoginWithInvalidPassword_LoginUnsuccessful() {
        RegisterPage registerPage = PageFactory.initElements(driver, RegisterPage.class);
        String email = generateRandomEmailInOrderToRunTestMoreThanOnce("boba.fett@hotmail.com");
        registerPage.addValidPerson("Boba", "Fett", email, "t");

        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.setEmail(email);
        homePage.setPassword("1324");
        homePage.submitLogIn();

        assertEquals("Home", homePage.getTitle());
        assertTrue(homePage.hasErrorMessage("No valid userid/password"));
    }

    @Test
    public void test_Login_LoginWithInvalidUserid_LoginUnsuccessful() {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.setEmail("nonStarWars@email.com");
        homePage.setPassword("t");
        homePage.submitLogIn();

        assertEquals("Home", homePage.getTitle());
        assertTrue(homePage.hasErrorMessage("No valid userid/password"));
    }

    @Test
    public void test_Login_LoginWithValidUseridAndMatchingPasswordRegardlessOfUseridCasing_LoginSuccessful() {
        RegisterPage registerPage = PageFactory.initElements(driver, RegisterPage.class);
        String email = generateRandomEmailInOrderToRunTestMoreThanOnce("Jabba.TheHutt@hotmail.com");
        registerPage.addValidPerson("Jabba", "The Hutt", email, "t");

        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.setEmail(email.toLowerCase());
        homePage.setPassword("t");
        homePage.submitLogIn();

        assertEquals("Home", homePage.getTitle());
        assertTrue(homePage.hasWelcomeMessage("Welcome Jabba!"));
    }

    private String generateRandomEmailInOrderToRunTestMoreThanOnce(String component) {
        int random = (int) (Math.random() * 1000 + 1);
        return random + component;
    }
}
