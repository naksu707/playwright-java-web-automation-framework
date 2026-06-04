package blass.academy.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {
    @BeforeEach
    void masterSetUp() {
        Logs.info("Setup padre");
    }

    @AfterEach
    void masterTearDown() {
        Logs.info("Teardown padre");
    }
}
