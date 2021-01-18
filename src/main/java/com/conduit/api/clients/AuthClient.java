package com.conduit.api.clients;

import com.conduit.models.User;
import com.google.gson.JsonObject;


import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class AuthClient {

    public String getToken(String email,  String password) {
        JsonObject userCreds = new JsonObject();
        userCreds.addProperty("email", email);
        userCreds.addProperty("password", password);

        JsonObject userObject = new JsonObject();
        userObject.add("user", userCreds);

        return given().
                contentType(JSON).
                body(userObject).
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
}
