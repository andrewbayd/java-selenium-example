package com.conduit.api.clients;

import com.conduit.models.User;
import com.conduit.models.UserAuthData;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class AuthClient {

    public String getToken(User user) {
        UserAuthData userAuthData = new UserAuthData(user);
        return given().
                contentType(JSON).
                body(userAuthData).
        when().
                post("https://conduit.productionready.io/api/users/login").
        then().
                statusCode(200).
        extract().
                path("user.token");

    }
}
