package com.conduit.login;

import com.conduit.BaseTest;
import com.conduit.models.User;
import com.conduit.pages.HomePage;
import io.qameta.allure.Feature;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.conduit.testdata.TestUser.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Feature("Login")
public class LoginTest extends BaseTest {

    @DataProvider(name = "user-credentials", parallel = true)
    public Object[][] returnUserCreds() {
        return new Object[][] {
                {TEST_USER_1},
                {TEST_USER_2},
                {TEST_USER_3},
                {TEST_USER_4}
        };
    }

    @Test(dataProvider = "user-credentials",
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
