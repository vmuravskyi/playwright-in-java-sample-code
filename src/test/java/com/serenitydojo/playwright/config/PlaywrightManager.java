package com.serenitydojo.playwright.config;

import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;

public final class PlaywrightManager {

	private static final Set<PlaywrightSession> SESSIONS = ConcurrentHashMap.newKeySet();

	private static final ThreadLocal<PlaywrightSession> CURRENT_SESSION = new ThreadLocal<>();

	private PlaywrightManager() {
	}

	public static Browser browser() {
		return session().browser();
	}

	public static BrowserContext newContext(Browser.NewContextOptions options) {
		return session().newContext(options);
	}

	private static PlaywrightSession session() {
		PlaywrightSession session = CURRENT_SESSION.get();

		if (session == null || session.isClosed()) {
			session = createSession();
			CURRENT_SESSION.set(session);
		}

		return session;
	}

	private static PlaywrightSession createSession() {
		Playwright playwright = Playwright.create();

		Browser browser = playwright.chromium().launch(
			new BrowserType.LaunchOptions()
				.setHeadless(false)
				.setArgs(Arrays.asList("--no-sandbox", "--disable-extensions", "--disable-gpu"))
//				.setEnv(System.getenv())
//				.setDownloadsPath("target/downloads")
//				.setHeadless(false)
//				.setProxy()
//				.setSlowMo(100) // optional for debug
		);

		PlaywrightSession session = new PlaywrightSession(playwright, browser);

		try {
			// AuthStateManager.ensureAuthState(browser); // uncomment when login state is implemented

			SESSIONS.add(session);
			return session;
		} catch (RuntimeException e) {
			session.close();
			throw e;
		}
	}

	public static void closeAll() {
		RuntimeException failure = null;

		for (PlaywrightSession session : SESSIONS) {
			try {
				session.close();
			} catch (RuntimeException e) {
				if (failure == null) {
					failure = e;
				} else {
					failure.addSuppressed(e);
				}
			}
		}

		SESSIONS.clear();
		CURRENT_SESSION.remove();

		if (failure != null) {
			throw failure;
		}
	}

	private static final class PlaywrightSession implements AutoCloseable {

		private final Playwright playwright;
		private final Browser browser;
		private final AtomicBoolean closed = new AtomicBoolean(false);

		private PlaywrightSession(Playwright playwright, Browser browser) {
			this.playwright = playwright;
			this.browser = browser;
		}

		private Browser browser() {
			return browser;
		}

		private BrowserContext newContext(Browser.NewContextOptions options) {
			return browser.newContext(options);
		}

		private boolean isClosed() {
			return closed.get();
		}

		@Override
		public void close() {
			if (!closed.compareAndSet(false, true)) {
				return;
			}

			try {
				browser.close();
			} finally {
				playwright.close();
			}
		}
	}

}
