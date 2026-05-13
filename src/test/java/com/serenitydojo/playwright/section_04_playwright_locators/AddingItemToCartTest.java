package com.serenitydojo.playwright.section_04_playwright_locators;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.serenitydojo.playwright.BaseTest;
import com.serenitydojo.playwright.config.PlaywrightManager;

class AddingItemToCartTest extends BaseTest {

	@BeforeAll
	static void setTestIdAttribute() {
		PlaywrightManager.setTestIdAttribute("data-test");
	}

	@AfterAll
	static void resetTestIdAttribute() {
		PlaywrightManager.resetTestIdAttribute();
	}

	@BeforeEach
	void openContactPage() {
		page.navigate("https://practicesoftwaretesting.com");
	}

	@DisplayName("Search for pliers")
	@Test
	void searchForPliers() {
		page.getByPlaceholder("Search").fill("Pliers");
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Search")).click();
		assertThat(page.locator(".card")).hasCount(4);

		List<String> productNames = page.getByTestId("product-name").allTextContents();
		Assertions.assertThat(productNames).allMatch(s -> s.contains("Pliers"));

		Locator outOfStockItem = page
			.locator(".card")
			.filter(new Locator.FilterOptions().setHasText("Out of stock"))
			.getByTestId("product-name");

		assertThat(outOfStockItem).hasCount(1);
		assertThat(outOfStockItem).hasText("Long Nose Pliers");
	}

}
