package com.serenitydojo.playwright.section_07_waits;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.serenitydojo.playwright.BaseTest;
import com.serenitydojo.playwright.config.PlaywrightManager;

class AutomaticWaitsTest extends BaseTest {

	@BeforeAll
	static void beforeAll() {
		PlaywrightManager.setTestIdAttribute("data-test");
	}

	@AfterAll
	static void afterAll() {
		PlaywrightManager.resetTestIdAttribute();
	}

	@BeforeEach
	void openContactPage() {
		page.navigate("https://practicesoftwaretesting.com");
		page.waitForCondition(
			() -> page.getByTestId("product-name").count() > 0); // wait for products to render
	}

	@DisplayName("Automation wait")
	@Test
	void shouldWaitForTheFilterCheckboxes() {
		Locator screwdriverFilter = page.getByLabel("Screwdriver");
		screwdriverFilter.click();

		PlaywrightAssertions.assertThat(screwdriverFilter).isChecked();
	}

}
