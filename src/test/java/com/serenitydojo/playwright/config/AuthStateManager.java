package com.serenitydojo.playwright.config;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;

public final class AuthStateManager {

	private static final Path AUTH_FILE = Paths.get("target/auth.json");

	private AuthStateManager() {
	}

	public static Path authFile() {
		return AUTH_FILE;
	}

	public static synchronized void ensureAuthState(Browser browser) {
		if (Files.exists(AUTH_FILE)) {
			return;
		}

		try {
			Files.createDirectories(AUTH_FILE.getParent());
			generateAuthState(browser);
		} catch (Exception e) {
			throw new IllegalStateException("Failed to generate auth state", e);
		}
	}

	private static void generateAuthState(Browser browser) {

		try (BrowserContext context = browser.newContext()) {
			Page page = context.newPage();

			page.navigate("https://practicesoftwaretesting.com/login");
			page.fill("#email", System.getProperty("test.user.email", "test@example.com"));
			page.fill("#password", System.getProperty("test.user.password", "password"));
			page.click("button[type='submit']");
			page.waitForURL("**/account");

			context.storageState(
				new BrowserContext.StorageStateOptions()
					.setPath(AUTH_FILE)
			);
		}
	}

}
