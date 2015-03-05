import org.junit.*;

import play.mvc.*;
import play.test.*;
import play.libs.F.*;

import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;

import static org.fluentlenium.core.filter.FilterConstructor.*;

import test.pages.NewContactPage;
import test.pages.IndexContactsPage;

public class IntegrationTest {

    /**
     * add your integration test here
     * in this example we just check if the welcome page is being shown
     */
    @Test
    public void test() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                browser.goTo("http://localhost:3333");
                assertThat(browser.pageSource()).contains("Your new application is ready.");
            }
        });
    }

    @Test
    public void creatingANewContactSuccessfullyShowsUpInTheIndexPage() {
        final int PORT = 3333;
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

            assertThat(browser.pageSource()).contains("Name: " + name);
            assertThat(browser.pageSource()).contains("Phone: " + phone);
            assertThat(browser.pageSource()).contains("Email: " + email);
        });
    }

}
