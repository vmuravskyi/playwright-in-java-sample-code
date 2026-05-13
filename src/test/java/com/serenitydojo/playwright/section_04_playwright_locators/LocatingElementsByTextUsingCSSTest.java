package com.serenitydojo.playwright.section_04_playwright_locators;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.assertions.LocatorAssertions;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
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

		PlaywrightAssertions.assertThat(page.locator("#first_name")).hasValue("Sarah-Jane");
		PlaywrightAssertions.assertThat(page.locator("#last_name")).hasValue("Smith");
		PlaywrightAssertions.assertThat(page.locator("#email_alert")).isVisible();
		PlaywrightAssertions.assertThat(page.locator("#email_alert")).hasText("Email is required");
	}

	// CSS class
	@DisplayName("By CSS class")
	@Test
	void locateTheSendButtonCssClass() {
		page.locator("#first_name").fill("Sarah-Jane");
		page.locator(".btnSubmit").click();
		//List<String> alerts = page.locator(".alert").allTextContents();
		PlaywrightAssertions
			.assertThat(page.locator(".alert"))
			.hasCount(4, new LocatorAssertions.HasCountOptions().setTimeout(5000));
		/*
			"Last name is required",
			"Email is required",
			"Subject is required",
			"Message is required"
		 */
	}

	@DisplayName("By Attribute")
	@Test
	void locateByAttribute() {
		page.locator("input[placeholder='Your first name *']").fill("Sarah-Jane");
		page.locator("input[placeholder='Your last name *']").fill("Smith");
		PlaywrightAssertions.assertThat(page.locator("#first_name")).hasValue("Sarah-Jane");
		PlaywrightAssertions.assertThat(page.locator("#last_name")).hasValue("Smith");
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
