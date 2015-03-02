package test.pages;

import org.fluentlenium.core.FluentPage;
import org.openqa.selenium.WebDriver;

public class CreateContactPage extends FluentPage {
    private String url;

    public CreateContactPage(WebDriver webDriver, int port) {
        super(webDriver);
        this.url = "http://localhost:" + port + "/contacts/new";
    }
}
