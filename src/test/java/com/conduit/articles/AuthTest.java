package com.conduit.articles;

import com.conduit.BaseTest;
import com.conduit.pages.HomePage;
import org.testng.annotations.Test;

import static com.conduit.testdata.TestUser.TEST_USER_1;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class AuthTest extends BaseTest {

    @Test
    void testAuthClient() {
        HomePage homePage = new HomePage();
        homePage.openLoggedInAs(TEST_USER_1);
        assertThat(homePage.isNewArticleButtonDisplayed(), equalTo(true));
    }
}
