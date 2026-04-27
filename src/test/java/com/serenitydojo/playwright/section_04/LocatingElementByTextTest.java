package com.serenitydojo.playwright.section_04;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.serenitydojo.playwright.BaseTest;

@DisplayName("Locating elements by Text")
class LocatingElementByTextTest extends BaseTest {

	@BeforeEach
	void beforeEach() {
		openPage();
	}

	@DisplayName("Using a text")
	@Test
	void byText() {
		page.getByText("Bolt Cutters").click();
		PlaywrightAssertions.assertThat(page.getByText("MightyCraft Hardware")).isVisible();
	}

	@DisplayName("Using an alt text")
	@Test
	void byAltText() {
		page.getByAltText("Combination Pliers").click();
		PlaywrightAssertions.assertThat(page.getByText("ForgeFlex Tools")).isVisible();
	}

	@DisplayName("Using a title")
	@Test
	void byTitle() {
		page.getByAltText("Combination Pliers").click();
		page.getByTitle("Practice Software Testing - Toolshop").click();
		PlaywrightAssertions.assertThat(page).hasURL("https://practicesoftwaretesting.com/");
	}

}
