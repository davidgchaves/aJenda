import org.junit.*;

import play.mvc.*;
import play.test.*;
import play.libs.F.*;

import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;

import static org.fluentlenium.core.filter.FilterConstructor.*;

import test.pages.NewContactPage;

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
    public void whenCreatingANewContactSuccessfullyItShowsUpInTheIndexPage() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                NewContactPage newContactPage = new NewContactPage(browser.getDriver(), 3333);
                String name = "Andrei Tarkovski";
                String phone = "981665544";
                String email = "andrei.tarkovski@example.com";

                browser.goTo(newContactPage);

                newContactPage.fill("#name").with(name);
                newContactPage.fill("#phone").with(phone);
                newContactPage.fill("#email").with(email);
                newContactPage.submit("#create-contact");

                assertThat(browser.pageSource()).contains("Name: " + name);
                assertThat(browser.pageSource()).contains("Phone: " + phone);
                assertThat(browser.pageSource()).contains("Email: " + email);
            }
        });
    }

}
