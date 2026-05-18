package com.serenitydojo.playwright.section_12_page_objects.pages.assertions;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;

public class CartPageAssertions extends BaseAssertion {

	public CartPageAssertions(Page page) {
		super(page);
	}

	public CartPageAssertions verifyProductExistsInCart(String productName) {
		// check cart contents
		PlaywrightAssertions
			.assertThat(page.locator(".product-title").getByText(productName))
			.isVisible();
		return this;
	}

	public CartPageAssertions verifyProductQuantity(String productName, int quantity) {
		Locator quantityField = page
			.locator("tr")
			.filter(new Locator.FilterOptions().setHas(page.locator(".product-title").getByText(productName)))
			.getByTestId("product-quantity");
		PlaywrightAssertions.assertThat(quantityField).hasValue(String.valueOf(quantity));
		return this;
	}

}
