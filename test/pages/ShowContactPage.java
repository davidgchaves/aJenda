package test.pages;

import org.fluentlenium.core.FluentPage;
import org.openqa.selenium.WebDriver;

import models.Contact;

public class ShowContactPage extends FluentPage {
    private String url;

    public ShowContactPage(WebDriver webDriver, int port, Contact contact) {
        super(webDriver);
        this.url = "http://localhost:" + port + "/contacts/" + contact.id;
    }

    @Override
    public String getUrl() {
        return this.url;
    }
}
