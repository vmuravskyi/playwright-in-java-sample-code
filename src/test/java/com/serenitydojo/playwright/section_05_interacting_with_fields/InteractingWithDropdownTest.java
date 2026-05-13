package com.serenitydojo.playwright.section_05_interacting_with_fields;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.SelectOption;
import com.serenitydojo.playwright.BaseTest;

class InteractingWithDropdownTest extends BaseTest {

	@BeforeEach
	void openContactPage() {
		page.navigate("https://practicesoftwaretesting.com/contact");
	}

	@DisplayName("Dropdown")
	@Test
	void completeForm() {
		Locator firstName = page.getByLabel("First Name");
		Locator lastName = page.getByLabel("Last Name");
		Locator email = page.getByLabel("Email");
		Locator messageField = page.getByLabel("message");

		// dropdown
		Locator subjectDropdown = page.getByLabel("subject");

		firstName.fill("John");
		lastName.fill("Doe");
		email.fill("test@test.com");
		messageField.fill("Test\nTest\nTest");
		subjectDropdown.selectOption("Warranty"); // also can accept array of values for multiple select

		// optional
//		subjectDropdown.selectOption(new SelectOption().setLabel("Warranty"));
//		subjectDropdown.selectOption(new SelectOption().setValue("Warranty"));
//		subjectDropdown.selectOption(new SelectOption().setIndex(2));

		PlaywrightAssertions.assertThat(firstName).hasValue("John");
		PlaywrightAssertions.assertThat(lastName).hasValue("Doe");
		PlaywrightAssertions.assertThat(email).hasValue("test@test.com");
		PlaywrightAssertions.assertThat(messageField).hasValue("Test\nTest\nTest");
		PlaywrightAssertions.assertThat(subjectDropdown).hasValue("warranty"); // gets HTML value
		PlaywrightAssertions
			.assertThat(subjectDropdown.locator("option:checked")) // get actual visible selected text
			.hasText("Warranty");
	}

}
