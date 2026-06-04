package blass.academy.listeners;

import blass.academy.utils.Logs;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

public class ExceptionListeners implements TestExecutionExceptionHandler {
    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
        Logs.info("handleTestExecutionException: %s", context.getDisplayName());
        Logs.info("Throwable: %s", throwable.getLocalizedMessage());
        throw throwable;
    }
}
