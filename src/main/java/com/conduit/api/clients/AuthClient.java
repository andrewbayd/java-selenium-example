package com.conduit.api.clients;

import com.conduit.models.User;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class AuthClient {

    public String getToken(String email,  String password) {
        return given().
                contentType(JSON).
                body(getUserJson(email, password)).
        when().
                post("https://conduit.productionready.io/api/users/login").
        then().
                statusCode(200).
        extract().
                path("user.token");

    }

    public String getToken(User user) {
        return getToken(user.getEmail(), user.getPassword());
    }

    private Map<String, Object> getUserJson(String email, String password) {
        Map<String, String> userCreds = new HashMap<>();
        userCreds.put("email", email);
        userCreds.put("password", password);
        Map<String, Object> userJson = new HashMap<>();
        userJson.put("user", userCreds);
        return userJson;
    }
}
