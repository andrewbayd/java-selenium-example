package com.conduit.framework.driver;

import lombok.NoArgsConstructor;

import java.util.Objects;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class DriverManager {

    private static final ThreadLocal<DriverFactory> DRIVER_FACTORY = new ThreadLocal<>();

    public static synchronized DriverFactory getFactory(Browser browser) {
        if (Objects.isNull(DRIVER_FACTORY.get())) {
            switch (browser) {
                case FIREFOX:
                    DRIVER_FACTORY.set(new FirefoxDriverCreator());
                    break;
                default:
                    DRIVER_FACTORY.set(new ChromeDriverCreator());
                    break;
            }
        }
        return DRIVER_FACTORY.get();
    }

    public static synchronized DriverFactory getDriverFactory() {
        return DRIVER_FACTORY.get();
    }
}
