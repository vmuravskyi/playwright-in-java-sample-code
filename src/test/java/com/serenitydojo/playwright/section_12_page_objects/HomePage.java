package com.serenitydojo.playwright.section_12_page_objects;

import java.util.List;

import com.microsoft.playwright.Page;
import com.serenitydojo.playwright.section_12_page_objects.assertions.HomePageAssertions;
import com.serenitydojo.playwright.section_12_page_objects.components.BasePage;
import com.serenitydojo.playwright.section_12_page_objects.components.ProductGrid;
import com.serenitydojo.playwright.section_12_page_objects.components.SearchComponent;

public class HomePage extends BasePage {

	private final SearchComponent searchComponent;
	private final ProductGrid productGrid;

	public HomePage(Page page) {
		super(page);
		this.searchComponent = new SearchComponent(page);
		this.productGrid = new ProductGrid(page);
	}

	@Override
	protected HomePage navigate() {
		page.navigate("https://practicesoftwaretesting.com");
		return this;
	}

	public HomePage searchBy(String keyword) {
		searchComponent.searchBy(keyword);
		return this;
	}

	public List<String> getProductNames() {
		return productGrid.getProductNames();
	}

	public HomePageAssertions verify() {
		return new HomePageAssertions(page);
	}

}
