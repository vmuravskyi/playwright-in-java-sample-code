package com.serenitydojo.playwright.section_12_page_objects.components;

import com.microsoft.playwright.Page;

public abstract class BasePage {

	protected Page page;

	protected BasePage(Page page) {
		this.page = page;
	}

	protected abstract BasePage navigate();

}
