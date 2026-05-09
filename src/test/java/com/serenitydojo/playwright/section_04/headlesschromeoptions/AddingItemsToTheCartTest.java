package com.serenitydojo.playwright.section_04.headlesschromeoptions;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.junit.UsePlaywright;
import com.microsoft.playwright.options.AriaRole;

@UsePlaywright(HeadlessChromeOptions.class)
class AddingItemsToTheCartTest {

	@DisplayName("Search for pliers")
	@Test
	void searchForPliers(Page page, Playwright playwright, Browser browser, BrowserContext context) {
		page.navigate("https://practicesoftwaretesting.com");
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

	@DisplayName("Search for pliers")
	@Test
	void searchForPliers2(Page page, Playwright playwright, Browser browser, BrowserContext context) {
		page.navigate("https://practicesoftwaretesting.com");
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

	@DisplayName("Search for pliers")
	@Test
	void searchForPliers3(Page page, Playwright playwright, Browser browser, BrowserContext context) {
		page.navigate("https://practicesoftwaretesting.com");
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

	@DisplayName("Search for pliers")
	@Test
	void searchForPliers4(Page page, Playwright playwright, Browser browser, BrowserContext context) {
		page.navigate("https://practicesoftwaretesting.com");
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

	@DisplayName("Search for pliers")
	@Test
	void searchForPliers5(Page page, Playwright playwright, Browser browser, BrowserContext context) {
		page.navigate("https://practicesoftwaretesting.com");
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

	@DisplayName("Search for pliers")
	@Test
	void searchForPliers6(Page page, Playwright playwright, Browser browser, BrowserContext context) {
		page.navigate("https://practicesoftwaretesting.com");
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

	@DisplayName("Search for pliers")
	@Test
	void searchForPliers7(Page page, Playwright playwright, Browser browser, BrowserContext context) {
		page.navigate("https://practicesoftwaretesting.com");
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

	@DisplayName("Search for pliers")
	@Test
	void searchForPliers8(Page page, Playwright playwright, Browser browser, BrowserContext context) {
		page.navigate("https://practicesoftwaretesting.com");
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
