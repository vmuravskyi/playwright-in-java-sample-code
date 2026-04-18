package com.serenitydojo.playwright;

import com.microsoft.playwright.*;
import com.microsoft.playwright.junit.UsePlaywright;
import org.junit.jupiter.api.*;

@UsePlaywright
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class BaseTest {

    protected Playwright playwright;
    protected Browser browser;

    protected BrowserContext context;
    protected Page page;

//    @BeforeAll
//    void globalSetup() {
//        playwright = Playwright.create();
//
//        browser = playwright.chromium().launch(
//            new BrowserType.LaunchOptions()
//                .setHeadless(false)   // change to true for CI
////                .setSlowMo(500)       // optional for debug
//        );
//    }
//
//    @AfterAll
//    void globalTearDown() {
//        if (browser != null) {
//            browser.close();
//        }
//        if (playwright != null) {
//            playwright.close();
//        }
//    }
//
//    @BeforeEach
//    void setUpTest() {
//        context = browser.newContext(new Browser.NewContextOptions()
//            .setViewportSize(1920, 1080) // optional config
//        );
//
//        page = context.newPage();
//    }
//
//    @AfterEach
//    void tearDownTest() {
//        if (context != null) {
//            context.close();
//        }
//    }

}
