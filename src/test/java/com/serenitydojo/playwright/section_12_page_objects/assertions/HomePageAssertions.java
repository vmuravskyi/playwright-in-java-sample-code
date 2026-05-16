package com.serenitydojo.playwright.section_12_page_objects.assertions;

import java.util.List;

import org.assertj.core.api.Assertions;

import com.microsoft.playwright.Page;

public class HomePageAssertions extends BaseAssertion {

	public HomePageAssertions(Page page) {
		super(page);
	}

	public HomePageAssertions productGridContains(List<String> products) {
		Assertions
			.assertThat(page.getByTestId("product-name").allInnerTexts())
			.containsExactlyInAnyOrderElementsOf(products);
		return this;
	}

}
