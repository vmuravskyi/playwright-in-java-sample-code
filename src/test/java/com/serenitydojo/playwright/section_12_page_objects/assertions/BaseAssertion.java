package com.serenitydojo.playwright.section_12_page_objects.assertions;

import com.microsoft.playwright.Page;

public class BaseAssertion {

	protected Page page;

	public BaseAssertion(Page page) {
		this.page = page;
	}

}
