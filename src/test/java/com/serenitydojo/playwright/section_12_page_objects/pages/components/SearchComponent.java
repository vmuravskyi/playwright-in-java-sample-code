package com.serenitydojo.playwright.section_12_page_objects.pages.components;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.serenitydojo.playwright.section_12_page_objects.pages.HomePage;

public class SearchComponent extends BaseComponent {

	public SearchComponent(Page page) {
		super(page);
	}

	public HomePage searchBy(String keyword) {
		page.waitForResponse("**/products/search?q=%s".formatted(keyword), () -> {
			page.getByPlaceholder("Search").fill(keyword);
			page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Search")).click();
		});
		return new HomePage(page);
	}

}
