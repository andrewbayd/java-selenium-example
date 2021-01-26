package com.conduit.utils;

import com.conduit.api.clients.AuthClient;
import com.conduit.models.User;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static lombok.AccessLevel.PRIVATE;

@Slf4j
@NoArgsConstructor(access = PRIVATE)
public class AuthHelper {

    public static String getAuthToken(User user) {
        log.info(String.format("Getting jwtToken for %s", user.getEmail()));
        AuthClient authClient = new AuthClient();
        return authClient.getToken(user);
    }
}
