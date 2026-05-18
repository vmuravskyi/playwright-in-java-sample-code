package com.serenitydojo.playwright.section_12_page_objects.pages.components;

import com.microsoft.playwright.Page;

public class BaseComponent {

	protected Page page;

	public BaseComponent(Page page) {
		this.page = page;
	}

}
