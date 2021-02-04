package com.conduit.login;

import com.conduit.BaseTest;
import com.conduit.dataproviders.CredentialsProvider;
import com.conduit.models.User;
import com.conduit.pages.HomePage;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Feature("Login")
public class LoginTest extends BaseTest {

    @Test(dataProvider = "user-credentials", dataProviderClass = CredentialsProvider.class,
            description = "User login with valid credentials should log in successfully")
    void loginTest(User user) {
        HomePage homePage = new HomePage();
        homePage
                .open()
                .navigateToLoginPage()
                .loginAs(user.getEmail(), user.getPassword());

        assertThat(homePage.isNewArticleButtonDisplayed(), equalTo(true));
    }
}
