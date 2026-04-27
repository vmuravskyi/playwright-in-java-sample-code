package com.serenitydojo.playwright;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.serenitydojo.playwright.config.PlaywrightManager;
import com.serenitydojo.playwright.config.PlaywrightSuiteExtension;

@ExtendWith(PlaywrightSuiteExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD) // each test method has its own instance of a test class
@Execution(ExecutionMode.CONCURRENT)
public abstract class BaseTest {

	protected BrowserContext browserContext;
	protected Page page;

	@BeforeEach
	void setupTest() {
		browserContext = PlaywrightManager.newContext(
			new Browser.NewContextOptions()
				.setViewportSize(1920, 1080)
			// .setStorageStatePath(AuthStateManager.authFile()) // uncomment when login state is implemented
		);

		page = browserContext.newPage();
	}

	@AfterEach
	void tearDownTest() {
		if (browserContext != null) {
			browserContext.close();
			browserContext = null;
			page = null;
		}
	}

}
