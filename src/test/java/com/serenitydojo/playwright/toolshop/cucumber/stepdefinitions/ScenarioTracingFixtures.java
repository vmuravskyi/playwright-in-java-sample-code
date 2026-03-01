package com.serenitydojo.playwright.toolshop.cucumber.stepdefinitions;

import com.microsoft.playwright.Tracing;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import net.serenitybdd.playwright.PlaywrightBrowserManager;

import java.nio.file.Paths;

public class ScenarioTracingFixtures {

    @Before(order = 200)
    public void setupTracing() {
        PlaywrightBrowserManager.current().getCurrentContext().tracing().start(
                new Tracing.StartOptions()
                        .setScreenshots(true)
                        .setSnapshots(true)
                        .setSources(true)
        );
    }

    @After
    public void recordTraces(Scenario scenario) {
        String traceName = scenario.getName().replace(" ", "-").toLowerCase();
        PlaywrightBrowserManager.current().getCurrentContext().tracing().stop(
                new Tracing.StopOptions()
                        .setPath(Paths.get("target/traces/trace-" + traceName + ".zip"))
        );
    }
}
