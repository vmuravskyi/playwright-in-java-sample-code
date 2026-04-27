package com.serenitydojo.playwright.config;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class PlaywrightSuiteExtension implements BeforeAllCallback {

	private static final ExtensionContext.Namespace NAMESPACE =
		ExtensionContext.Namespace.create(PlaywrightSuiteExtension.class);

	@Override
	public void beforeAll(ExtensionContext context) {
		context.getRoot()
			.getStore(NAMESPACE)
			.getOrComputeIfAbsent(
				"playwright",
				key -> new PlaywrightResource(),
				PlaywrightResource.class
			);
	}

	private static final class PlaywrightResource implements ExtensionContext.Store.CloseableResource {

		@Override
		public void close() {
			PlaywrightManager.closeAll();
		}
	}

}
