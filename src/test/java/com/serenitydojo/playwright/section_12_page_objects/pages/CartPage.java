package com.serenitydojo.playwright.section_12_page_objects.pages;

import com.microsoft.playwright.Page;
import com.serenitydojo.playwright.section_12_page_objects.pages.assertions.CartPageAssertions;
import com.serenitydojo.playwright.section_12_page_objects.pages.components.BasePage;

public class CartPage extends BasePage {

	protected CartPage(Page page) {
		super(page);
	}

	public CartPageAssertions verify() {
		return new CartPageAssertions(page);
	}

}
