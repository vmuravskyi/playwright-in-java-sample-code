package com.serenitydojo.playwright.toolshop.cucumber.stepdefinitions;

import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.junit.Options;
import com.microsoft.playwright.junit.OptionsFactory;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import net.serenitybdd.playwright.PlaywrightBrowserManager;

import java.util.Arrays;

public class PlaywrightHooks {

      public static class HeadlessChromiumOptions implements OptionsFactory {
          @Override
          public Options getOptions() {
              return new Options()
                      .setLaunchOptions(
                              new BrowserType.LaunchOptions()
                                      .setArgs(Arrays.asList("--no-sandbox", "--disable-gpu")))
                      .setHeadless(true)
                      .setTestIdAttribute("data-test");
          }
      }

      private static final PlaywrightBrowserManager browser =
              PlaywrightBrowserManager.managed(new HeadlessChromiumOptions());

      @Before(order = 100)
      public void setUp() {
          browser.openNewPage();
      }

      @After(order = 100)
      public void tearDown() {
          browser.closeCurrentContext();
      }

      @AfterAll
      public static void shutdown() {
          browser.close();
      }
  }
