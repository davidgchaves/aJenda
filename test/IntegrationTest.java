import org.junit.*;

import play.mvc.*;
import play.test.*;
import play.libs.F.*;

import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;
import static org.fluentlenium.core.filter.FilterConstructor.*;

import test.pages.NewContactPage;
import test.pages.IndexContactsPage;
import test.pages.ShowContactPage;

import models.Contact;

public class IntegrationTest {

    private final int PORT = 3333;

    @Test
    public void user_visiting_home_page_sees_contacts() {
        running(testServer(PORT, fakeApplication(inMemoryDatabase())), HTMLUNIT, browser -> {
            Contact andrei = new Contact("Andrei Tarkovski", "981665544", "andrei.tarkovski@example.com");
            andrei.save();

            Contact hou = new Contact("Hou Hsiao-hsien", "934665544", "hou.hsiao.hsien@example.com");
            hou.save();

            browser.goTo("http://localhost:" + PORT);

            assertThat(browser.pageSource()).contains(andrei.name);
            assertThat(browser.pageSource()).contains(andrei.phone);
            assertThat(browser.pageSource()).contains(andrei.email);
            assertThat(browser.pageSource()).contains(hou.name);
            assertThat(browser.pageSource()).contains(hou.phone);
            assertThat(browser.pageSource()).contains(hou.email);
        });
    }

    @Test
    public void user_creates_a_contact_successfully() {
        running(testServer(PORT, fakeApplication(inMemoryDatabase())), HTMLUNIT, browser -> {
            IndexContactsPage indexContactsPage = new IndexContactsPage(browser.getDriver(), PORT);
            NewContactPage newContactPage = new NewContactPage(browser.getDriver(), PORT);
            String name = "Andrei Tarkovski";
            String phone = "981665544";
            String email = "andrei.tarkovski@example.com";

            browser.goTo(indexContactsPage);
            indexContactsPage.click("#goto-new-contact");

            newContactPage.fill("#name").with(name);
            newContactPage.fill("#phone").with(phone);
            newContactPage.fill("#email").with(email);
            newContactPage.submit("#create-contact");

            assertThat(browser.pageSource()).contains(name);
            assertThat(browser.pageSource()).contains(phone);
            assertThat(browser.pageSource()).contains(email);
        });
    }

    @Test
    public void user_sees_contact_details() {
        running(testServer(PORT, fakeApplication(inMemoryDatabase())), HTMLUNIT, browser -> {
            Contact andrei = new Contact("Andrei Tarkovski", "981665544", "andrei.tarkovski@example.com");
            andrei.save();

            ShowContactPage showContactPage = new ShowContactPage(browser.getDriver(), PORT, andrei);

            browser.goTo(showContactPage);

            assertThat(browser.pageSource()).contains("Name: " + andrei.name);
            assertThat(browser.pageSource()).contains("Phone: " + andrei.phone);
            assertThat(browser.pageSource()).contains("Email: " + andrei.email);
        });
    }

}
