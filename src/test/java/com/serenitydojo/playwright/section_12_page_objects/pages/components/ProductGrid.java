package com.serenitydojo.playwright.section_12_page_objects.pages.components;

import java.util.List;

import com.microsoft.playwright.Page;
import com.serenitydojo.playwright.section_12_page_objects.pages.ProductDetailsPage;

public class ProductGrid extends BaseComponent {

	public ProductGrid(Page page) {
		super(page);
	}

	public List<String> getProductNames() {
		return page.getByTestId("product-name").allInnerTexts();
	}

	public void clickOnProduct(String productName) {
		page.locator(".card").getByText(productName).click();
	}

}
