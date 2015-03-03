package test.pages;

import org.fluentlenium.core.FluentPage;
import org.openqa.selenium.WebDriver;

public class NewContactPage extends FluentPage {
    private String url;

    public NewContactPage(WebDriver webDriver, int port) {
        super(webDriver);
        this.url = "http://localhost:" + port + "/contacts/new";
    }

    @Override
    public String getUrl() {
        return this.url;
    }
}
