package com.serenitydojo.playwright.section_05_interacting_with_fields;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.serenitydojo.playwright.BaseTest;

class InteractingWithFieldsTest extends BaseTest {

	@BeforeEach
	void openContactPage() {
		page.navigate("https://practicesoftwaretesting.com/contact");
	}

	@DisplayName("Using the Label")
	@Test
	void fieldValues() {
		Locator firstName = page.getByLabel("First Name");
		firstName.fill("John");
		PlaywrightAssertions.assertThat(firstName).hasValue("John");
	}

	@DisplayName("Complete Form")
	@Test
	void completeForm() {
		Locator firstName = page.getByLabel("First Name");
		Locator lastName = page.getByLabel("Last Name");
		Locator email = page.getByLabel("Email");
		Locator messageField = page.getByLabel("message");

		firstName.fill("John");
		lastName.fill("Doe");
		email.fill("test@test.com");
		messageField.fill("Test\nTest\nTest");

		PlaywrightAssertions.assertThat(firstName).hasValue("John");
		PlaywrightAssertions.assertThat(lastName).hasValue("Doe");
		PlaywrightAssertions.assertThat(email).hasValue("test@test.com");
		PlaywrightAssertions.assertThat(messageField).hasValue("Test\nTest\nTest");
	}

}
