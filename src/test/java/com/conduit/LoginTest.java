package com.conduit;

import com.conduit.pages.HomePage;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class LoginTest extends BaseTest {

    @Test
    void loginTest() {
        HomePage homePage = new HomePage();
        homePage
                .open()
                .navigateToLoginPage()
                .loginAs("java1@selenium.test", "JavaSeleniumTest1");
        assertThat(homePage.isNewArticleButtonDisplayed(), equalTo(true));
    }
}
