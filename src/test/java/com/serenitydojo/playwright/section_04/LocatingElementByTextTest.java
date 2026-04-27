package com.serenitydojo.playwright.section_04;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.serenitydojo.playwright.BaseTest;

class LocatingElementByTextTest extends BaseTest {

	@BeforeEach
	void beforeEach() {
		openPage();
	}

	@Test
	void byText() {
		page.getByText("Bolt Cutters").click();
		PlaywrightAssertions.assertThat(page.getByText("MightyCraft Hardware")).isVisible();
	}

	@Test
	void byAltText() {
		page.getByAltText("Combination Pliers").click();
		PlaywrightAssertions.assertThat(page.getByText("ForgeFlex Tools")).isVisible();
	}

	@Test
	void byTitle() {
		page.getByAltText("Combination Pliers").click();
		page.getByTitle("Practice Software Testing - Toolshop").click();
		PlaywrightAssertions.assertThat(page).hasURL("https://practicesoftwaretesting.com/");
	}

}
