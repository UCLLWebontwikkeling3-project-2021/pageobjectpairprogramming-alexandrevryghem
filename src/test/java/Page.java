//////////////////////////////////////////
// Alexandre Vryghem - r0747249         //
// Mathias Van den Cruijce - r0785409   //
//////////////////////////////////////////

import org.openqa.selenium.WebDriver;

public abstract class Page {

    WebDriver driver;
    //todo hernoemen naar 8080
    String path = "http://localhost:8081/Controller";

    public Page(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public String getPath() {
        return path;
    }

    public String getTitle() {
        return driver.getTitle();
    }

}
