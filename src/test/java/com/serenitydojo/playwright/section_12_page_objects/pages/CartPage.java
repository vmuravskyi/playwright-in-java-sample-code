package com.serenitydojo.playwright.section_12_page_objects.pages;

import java.util.List;
import java.util.stream.IntStream;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.serenitydojo.playwright.section_12_page_objects.pages.assertions.CartPageAssertions;
import com.serenitydojo.playwright.section_12_page_objects.pages.components.BasePage;
import com.serenitydojo.playwright.section_12_page_objects.pages.models.CartRow;

public class CartPage extends BasePage {

	protected CartPage(Page page) {
		super(page);
	}

	public CartPageAssertions verify() {
		return new CartPageAssertions(page);
	}

	public List<CartRow> getCartRows() {
		Locator rows = page.locator("tr");
		int rowCount = rows.count();

		// if only header for footer - return empty list
		if (rowCount <= 2) {
			return List.of();
		}

		return IntStream.range(1, rowCount - 1)
			.mapToObj(index -> {
				Locator row = rows.nth(index);

				String item = row.locator(".product-title").innerText().trim();
				int quantity = Integer.parseInt(
					row.locator(".product-quantity").inputValue().trim()
				);
				double price = parseMoney(
					row.locator("[data-test='product-price']").innerText()
				);
				double total = parseMoney(
					row.locator("[data-test='line-price']").innerText()
				);

				return new CartRow(item, quantity, price, total);
			})
			.toList();
	}

	private double parseMoney(String value) {
		return Double.parseDouble(
			value.replace("$", "").replace(",", "").trim()
		);
	}

}
