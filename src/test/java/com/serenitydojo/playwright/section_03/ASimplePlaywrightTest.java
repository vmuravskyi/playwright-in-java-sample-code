package com.serenitydojo.playwright.section_03;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import com.serenitydojo.playwright.BaseTest;

class ASimplePlaywrightTest extends BaseTest {

	@Test
	void shouldShowThePageTitle() {
		page.navigate("https://practicesoftwaretesting.com");

		Assertions.assertThat(page.title())
			.contains("Practice Software Testing");
	}

	@Test
	void shouldSearchByKeyword() {
		page.navigate("https://practicesoftwaretesting.com");

		page.locator("[placeholder=Search]").fill("Pliers");
		page.locator("button:has-text('Search')").click();

		int countOfMatchingResults = page.locator(".card").count();

		Assertions.assertThat(countOfMatchingResults)
			.isGreaterThan(0);
	}

}
