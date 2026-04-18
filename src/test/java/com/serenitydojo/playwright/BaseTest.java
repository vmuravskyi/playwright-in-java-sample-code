package com.serenitydojo.playwright;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import java.nio.file.Paths;
import java.util.Arrays;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;

//@UsePlaywright
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class BaseTest {

    protected static final String AUTH_FILE = "target/auth.json";

    protected static Playwright playwright;
    protected static Browser browser;

    protected BrowserContext browserContext;
    protected Page page;

    @BeforeAll
    void globalSetup() {
        if (playwright == null) {
            playwright = Playwright.create();

            browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                    .setHeadless(false)
                    .setArgs(Arrays.asList("--no-sandbox", "--disable-extensions", "--disable-gpu"))
                    .setSlowMo(100)       // optional for debug
            );
        }

        //generateAuthState(); // login once and save state
    }

    @BeforeEach
    void setUpTest() {
        browserContext = browser.newContext(new Browser.NewContextOptions()
            .setViewportSize(1280, 720)
        );

        page = browserContext.newPage();
    }

    @AfterEach
    void tearDownTest() {
        if (browserContext != null) {
            browserContext.close();
        }
    }

    @AfterAll
    void globalTearDown() {
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }

    private void generateAuthState() {
        BrowserContext context = browser.newContext();
        Page authPage = context.newPage();

        authPage.navigate("https://practicesoftwaretesting.com/login");

        // replace with real login steps
        authPage.fill("#email", "test@example.com");
        authPage.fill("#password", "password");
        authPage.click("button[type='submit']");

        // wait until logged in
        authPage.waitForURL("**/account");

        // save auth state
        context.storageState(new BrowserContext.StorageStateOptions()
            .setPath(Paths.get(AUTH_FILE)));

        context.close();
    }

}
