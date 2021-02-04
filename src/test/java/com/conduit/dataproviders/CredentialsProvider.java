package com.conduit.dataproviders;

import org.testng.annotations.DataProvider;

import static com.conduit.testdata.TestUser.*;
import static com.conduit.testdata.TestUser.TEST_USER_4;

public class CredentialsProvider {

    @DataProvider(name = "user-credentials", parallel = true)
    public Object[][] returnUserCredentials() {
        return new Object[][] {
                {TEST_USER_1},
                {TEST_USER_2},
                {TEST_USER_3},
                {TEST_USER_4}
        };
    }
}
