package blass.academy.listeners;

import blass.academy.utils.Logs;
import io.qameta.allure.listener.TestLifecycleListener;
import io.qameta.allure.model.TestResult;

public class AllureListeners implements TestLifecycleListener {
    @Override
    public void beforeTestStop(TestResult result) {
        Logs.flushToAllure();
    }
}