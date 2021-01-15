package com.conduit;

import com.conduit.pages.HomePage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class LoginTest extends BaseTest {

    @DataProvider(name = "user-credentials", parallel = true)
    public Object[][] returnUserCreds() {
        return new Object[][] {
                {"java1@selenium.test", "JavaSeleniumTest1"},
                {"java2@selenium.test", "JavaSeleniumTest2"},
                {"java3@selenium.test", "JavaSeleniumTest3"},
                {"java4@selenium.test", "JavaSeleniumTest4"}
        };
    }

    @Test(dataProvider = "user-credentials")
    void loginTest(String email, String password) {
        HomePage homePage = new HomePage();
        homePage
                .open()
                .navigateToLoginPage()
                .loginAs(email, password);
        assertThat(homePage.isNewArticleButtonDisplayed(), equalTo(true));
    }
}
