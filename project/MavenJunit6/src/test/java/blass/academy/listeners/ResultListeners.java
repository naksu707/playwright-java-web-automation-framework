package blass.academy.listeners;

import blass.academy.utils.Logs;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.util.Optional;

public class ResultListeners implements TestWatcher {
    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        Logs.info("Test deshabilitado: %s, razon: %s",
                context.getDisplayName(), reason.orElse(""));
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        Logs.info("Test exitoso: %s", context.getDisplayName());
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        Logs.info("Test fallido: %s, causa: %s",
                context.getDisplayName(),
                cause != null ? cause.getLocalizedMessage() : "desconocida");
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        Logs.info("Test abortado: %s, causa: %s",
                context.getDisplayName(),
                cause != null ? cause.getLocalizedMessage() : "desconocida");
    }
}
