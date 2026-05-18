package com.serenitydojo.playwright.section_12_page_objects.pages;

import java.util.stream.Stream;

import com.microsoft.playwright.Page;
import com.serenitydojo.playwright.section_12_page_objects.pages.components.BasePage;

public class ProductDetailsPage extends BasePage {

	public ProductDetailsPage(Page page) {
		super(page);
	}

	public ProductDetailsPage increaseProductQuantity(String productName, int quantity) {
		Stream.of(0, quantity).forEach(index -> increateQuantity(productName));
		return this;
	}

	private void increateQuantity(String productName) {
		page.getByTestId("increase-quantity").click();
	}

	public ProductDetailsPage addToCart() {
		page.getByText("Add to cart").click();
		return this;
	}

	public CartPage openCart() {
		page.getByTestId("nav-cart").click();
		return new CartPage(page);
	}

}
