package com.serenitydojo.playwright.section_04;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.serenitydojo.playwright.BaseTest;

class LocatingElementsByTextUsingCSSTest extends BaseTest {

	@BeforeEach
	void openContactPage() {
		page.navigate("https://practicesoftwaretesting.com/contact");
	}

	// :has-text matches any element containing specified text somewhere inside.
	@DisplayName("Using :has-text")
	@Test
	void locateTheSendButtonByText() {
		page.locator("#first_name").fill("Sarah-Jane");
		page.locator("#last_name").fill("Smith");
		page.locator("input:has-text('Send')").click();
	}

	// :text matches the smallest element containing specified text.
	@DisplayName("Using :text")
	@Test
	void locateAProductItemByText() {
		page.locator(".navbar :text('Home')").click();
		page.locator(".card :text('Bolt')").click();
		assertThat(page.locator("[data-test=product-name]")).hasText("Bolt Cutters");
	}

	// Exact matches
	@DisplayName("Using :text-is")
	@Test
	void locateAProductItemByTextIs() {
		page.locator(".navbar :text('Home')").click();
		page.locator(".card :text-is('Bolt Cutters')").click();
		assertThat(page.locator("[data-test=product-name]")).hasText("Bolt Cutters");
	}

	// matching with regular expressions
	@DisplayName("Using :text-matches")
	@Test
	void locateAProductItemByTextMatches() {
		page.locator(".navbar :text('Home')").click();
		page.locator(".card :text-matches('Bolt \\\\w+')").click();
		assertThat(page.locator("[data-test=product-name]")).hasText("Bolt Cutters");
	}

}
