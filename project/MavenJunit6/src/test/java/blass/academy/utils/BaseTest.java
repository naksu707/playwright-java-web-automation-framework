package blass.academy.utils;

import blass.academy.listeners.ExceptionListeners;
import blass.academy.listeners.ResultListeners;
import blass.academy.listeners.TestListeners;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith({TestListeners.class, ResultListeners.class, ExceptionListeners.class})
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
