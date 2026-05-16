package com.serenitydojo.playwright.section_12_page_objects.components;

import java.util.List;

import com.microsoft.playwright.Page;

public class ProductGrid extends BaseComponent {

	public ProductGrid(Page page) {
		super(page);
	}

	public List<String> getProductNames() {
		return page.getByTestId("product-name").allInnerTexts();
	}

}
