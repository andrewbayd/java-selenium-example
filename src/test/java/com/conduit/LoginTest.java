package com.conduit;

import com.conduit.models.User;
import com.conduit.pages.HomePage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.conduit.testdata.TestUser.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

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

    @Test(dataProvider = "user-credentials")
    void loginTest(User user) {
        HomePage homePage = new HomePage();
        homePage
                .open()
                .navigateToLoginPage()
                .loginAs(user.getEmail(), user.getPassword());
        assertThat(homePage.isNewArticleButtonDisplayed(), equalTo(true));
    }
}
