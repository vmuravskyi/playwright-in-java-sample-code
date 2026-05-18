package com.serenitydojo.playwright.section_12_page_objects;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;
import com.serenitydojo.playwright.BaseTest;
import com.serenitydojo.playwright.config.PlaywrightManager;
import com.serenitydojo.playwright.section_12_page_objects.pages.HomePage;

class PageObjectTest extends BaseTest {

	@BeforeAll
	static void beforeAll() {
		PlaywrightManager.setTestIdAttribute("data-test");
	}

	@AfterAll
	static void afterAll() {
		PlaywrightManager.resetTestIdAttribute();
	}

	@DisplayName("Without Page Object")
	@Test
	void withoutPageObject() {
		page.navigate("https://practicesoftwaretesting.com");
		page.waitForResponse("**/products/search?q=tape", () -> {
			page.getByPlaceholder("Search").fill("tape");
			page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Search")).click();
		});

		List<String> matchingProducts = page.getByTestId("product-name").allInnerTexts();
		Assertions.assertThat(matchingProducts)
			.contains("Tape Measure 7.5m", "Measuring Tape", "Tape Measure 5m");
	}

	@DisplayName("With Page Object")
	@Test
	void withPageObject() {
		HomePage homePage = new HomePage(page).navigate();

		List<String> productNames = homePage
			.searchBy("tape")
			.getProductNames();

		homePage
			.verify()
			.productGridContains(productNames);
	}

	@DisplayName("Adding items to the cart without Page Objects")
	@Test
	void addingItemToTheCartWithoutPageObjects() {
		page.navigate("https://practicesoftwaretesting.com");
		// Search for pliers
		page.waitForResponse("**/products/search?q=pliers", () -> {
			page.getByPlaceholder("Search").fill("pliers");
			page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Search")).click();
		});
		// Show details page
		page.locator(".card").getByText("Combination Pliers").click();

		// Increase cart quanity
		page.getByTestId("increase-quantity").click();
		page.getByTestId("increase-quantity").click();
		// Add to cart
		page.getByText("Add to cart").click();
		page.waitForCondition(() -> page.getByTestId("cart-quantity").textContent().equals("3"));
		// pop up verification

		// Open the cart
		page.getByTestId("nav-cart").click();

		// check cart contents
		PlaywrightAssertions
			.assertThat(page.locator(".product-title").getByText("Combination Pliers"))
			.isVisible();
		PlaywrightAssertions
			.assertThat(page.getByTestId("cart-quantity").getByText("3"))
			.isVisible();
	}

	@DisplayName("Adding items to the cart with Page Objects")
	@Test
	void addingItemToTheCartWithPageObjects() {
		String productName = "Combination Pliers";
		int expectedProductQuantity = 3;

		new HomePage(page)
			.navigate()
			.viewProductDetails(productName)
			.increaseProductQuantity(productName, 2)
			.addToCart()
			.openCart()
			.verify()
			.verifyProductExistsInCart(productName)
			.verifyProductQuantity(productName, expectedProductQuantity);
	}

}
