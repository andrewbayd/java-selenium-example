package com.conduit.logout;

import com.conduit.BaseTest;
import com.conduit.dataproviders.CredentialsProvider;
import com.conduit.models.User;
import com.conduit.pages.HomePage;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class LogoutTest extends BaseTest {

    @Test(dataProvider = "user-credentials", dataProviderClass = CredentialsProvider.class,
            description = "Test logout")
    void testLogout(User user) {
        HomePage homePage = new HomePage();
        homePage
                .openLoggedInAs(user)
                .navigateToSettingsPage()
                .logout();

        assertThat(homePage.isBannerDisplayed(), equalTo(true));
        assertThat(homePage.isSignInButtonDisplayed(), equalTo(true));
    }
}
