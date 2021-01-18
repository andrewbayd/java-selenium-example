package com.conduit.utils;

import com.conduit.api.clients.AuthClient;
import com.conduit.models.User;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthHelper {

    public static String getAuthToken(String email, String password) {
        log.info(String.format("Getting jwtToken for %s", email));
        AuthClient authClient = new AuthClient();
        return authClient.getToken(email, password);
    }

    public static String getAuthToken(User user) {
        return getAuthToken(user.getEmail(), user.getPassword());
    }
}
