package blass.academy.listeners;

import blass.academy.utils.Logs;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class TestListeners implements BeforeEachCallback, AfterEachCallback {
    @Override
    public void beforeEach(ExtensionContext context) {
        Logs.info("BeforeEach: %s", context.getDisplayName());
    }

    @Override
    public void afterEach(ExtensionContext context) {
        Logs.info("AfterEach: %s", context.getDisplayName());
    }
}
