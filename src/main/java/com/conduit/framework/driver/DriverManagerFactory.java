package com.conduit.framework.driver;

import java.util.Objects;

public class DriverManagerFactory {

    private static final ThreadLocal<DriverManager> DRIVER_MANAGER = new ThreadLocal<>();

    public static synchronized DriverManager getManager(Browser browser) {
        if (Objects.isNull(DRIVER_MANAGER.get())) {
            switch (browser) {
                case FIREFOX:
                    DRIVER_MANAGER.set(new FirefoxDriverManager());
                    break;
                default:
                    DRIVER_MANAGER.set(new ChromeDriverManager());
                    break;
            }
        }
        return DRIVER_MANAGER.get();
    }

    public static synchronized DriverManager getDriverManager() {
        return DRIVER_MANAGER.get();
    }
}
