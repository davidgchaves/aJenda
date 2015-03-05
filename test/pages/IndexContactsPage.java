package test.pages;

import org.fluentlenium.core.FluentPage;
import org.openqa.selenium.WebDriver;

public class IndexContactsPage extends FluentPage {
    private String url;

    public IndexContactsPage(WebDriver webDriver, int port) {
        super(webDriver);
        this.url = "http://localhost:" + port + "/contacts";
    }

    @Override
    public String getUrl() {
        return this.url;
    }
}
