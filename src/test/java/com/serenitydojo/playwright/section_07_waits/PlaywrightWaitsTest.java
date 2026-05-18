package com.serenitydojo.playwright.section_07_waits;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.SelectOption;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.serenitydojo.playwright.BaseTest;
import com.serenitydojo.playwright.config.PlaywrightManager;

class PlaywrightWaitsTest extends BaseTest {

	@BeforeAll
	static void beforeAll() {
		PlaywrightManager.setTestIdAttribute("data-test");
	}

	@AfterAll
	static void afterAll() {
		PlaywrightManager.resetTestIdAttribute();
	}

	@BeforeEach
	void openHomePage() {
		page.navigate("https://practicesoftwaretesting.com");
	}

	@Test
	void shouldShowAllProductNames() {
		// wait for products to upload
		page.waitForSelector("[data-test='product-name']");

		List<String> productText = page.getByTestId("product-name").allInnerTexts();

		Assertions.assertThat(productText).contains("Pliers", "Bolt Cutters", "Hammer");
	}

	@Test
	void shouldShowAllProductImages() {
		page.waitForSelector(".card-img-top");

		List<String> productImages = page.locator(".card-img-top").all()
			.stream()
			.map(image -> image.getAttribute("alt"))
			.toList();
		Assertions.assertThat(productImages).contains("Pliers", "Bolt Cutters", "Hammer");
	}

	@DisplayName("Should filter products by category")
	@Test
	void shouldFilterProductsByCategory() {
		page
			.getByRole(AriaRole.MENUBAR)
			.getByTestId("nav-categories")
			.click();

		page
			.getByRole(AriaRole.MENUBAR)
			.getByTestId("nav-power-tools")
			.click();

		// wait for products to filter
		page.waitForSelector(
			".card",
			new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE)
		);

		List<String> filteredProducts = page.getByTestId("product-name").allInnerTexts();

		Assertions.assertThat(filteredProducts)
			.contains("Sheet Sander", "Cordless Drill 24V", "Cordless Drill 12V");

	}

	@DisplayName("Should display a toast message when an item is added to the cart")
	@Test
	void shouldDisplayToasterMessage() {
		page.getByText("Bolt Cutters").click();
		page.getByText("Add to cart").click();

		// wait for the toaster message to appear
		PlaywrightAssertions
			.assertThat(page.getByRole(AriaRole.ALERT))
			.isVisible();
		PlaywrightAssertions
			.assertThat(page.getByRole(AriaRole.ALERT))
			.hasText("Product added to shopping cart.");

		// wait until toast message disappears
		page.waitForCondition(() -> page.getByRole(AriaRole.ALERT).isHidden());
	}

	@DisplayName("Should update the cart item count")
	@Test
	void shouldUpdateCartItemCount() {
		page.getByText("Bolt Cutters").click();
		page.getByText("Add to cart").click();

		// wait for the toaster message to appear
		PlaywrightAssertions
			.assertThat(page.getByRole(AriaRole.ALERT))
			.isVisible();
		PlaywrightAssertions
			.assertThat(page.getByRole(AriaRole.ALERT))
			.hasText("Product added to shopping cart.");

		// wait for cart item to be updated
		page.getByTestId("cart-quantity").textContent();
		page.waitForCondition(() ->
			Integer.valueOf(page.getByTestId("cart-quantity").textContent()).equals(1)
		);
	}

	@DisplayName("Should sort by descending price")
	@Test
	void shouldSortByDescendingPrice() {

		// option 1: wait for at least the first product to be visible
		page.getByTestId("product-price").first().waitFor();

		// option 2: wait for the API sort call (e.g. https://api.practicesoftwaretesting.com/products?page=0&sort=price,desc&between=price,1,100&is_rental=false)
		page.waitForResponse("**/products**sort=**", () -> {
			page.getByLabel("sort").selectOption(new SelectOption().setLabel("Price (Low - High)"));
			page.getByTestId("product-price").first().waitFor();
		});

		List<Double> productPrices = page.getByTestId("product-price").allInnerTexts()
			.stream()
			.map(s -> s.replace("$", ""))
			.map(Double::parseDouble)
			.toList();

		ArrayList<Double> sortedPrices = new ArrayList<>(productPrices);
		sortedPrices.sort(Double::compareTo);

		Assertions.assertThat(productPrices).isNotEmpty();
		Assertions.assertThat(productPrices).containsExactlyElementsOf(sortedPrices);
	}

}
