//////////////////////////////////////////
// Alexandre Vryghem - r0747249         //
// Mathias Van den Cruijce - r0785409   //
//////////////////////////////////////////

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends Page {

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "logIn")
    private WebElement logInButton;

    @FindBy(id = "logOut")
    private WebElement logOutButton;

    public HomePage(WebDriver driver) {
        super(driver);
        this.driver.get(getPath());
    }

    public void setEmail(String email) {
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void setPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void submitLogIn() {
        logInButton.click();
    }

    public HomePage submitLogOut() {
        logOutButton.click();
        return PageFactory.initElements(driver, HomePage.class);
    }

    public boolean hasErrorMessage(String message) {
        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger p"));
        return (message.equals(errorMsg.getText()));
    }

    public boolean hasWelcomeMessage(String message) {
        return driver.findElement(By.id("welcomeMessage")).getText().equals(message);
    }
}
