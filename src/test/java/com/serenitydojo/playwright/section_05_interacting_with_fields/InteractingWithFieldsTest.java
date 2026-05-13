package com.serenitydojo.playwright.section_05_interacting_with_fields;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.assertions.LocatorAssertions;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;
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

	@DisplayName("Mandatory fields")
	@Test
	void mandatoryFields() {
		Locator firstName = page.getByLabel("First Name");
		Locator lastName = page.getByLabel("Last Name");
		Locator email = page.getByLabel("Email");
		Locator messageField = page.getByLabel("message");
		Locator subjectDropdown = page.getByLabel("subject");
		Locator sendButton = page.getByText("Send");

		sendButton.click();

		// verify there are 5 alers
		assertThat(page.getByRole(AriaRole.ALERT)).hasCount(5, new LocatorAssertions.HasCountOptions().setTimeout(3000));

		// verify alert messages
		List<String> alerts = page.getByRole(AriaRole.ALERT).allTextContents();
		org.assertj.core.api.Assertions.assertThat(alerts)
			.as("Expected 5 validation alerts for empty contact form: first name, last name, email, subject, message")
			.containsExactlyInAnyOrderElementsOf(List.of(
				"First name is required",
				"Last name is required",
				"Email is required",
				"Subject is required",
				"Message is required"
			));
	}

	@DisplayName("Mandatory fields parameterized")
	@ParameterizedTest
	@ValueSource(strings = {"First name", "Last name", "Email", "Message"})
	void mandatoryFieldsParameterized(String fieldName) {
		Locator firstName = page.getByLabel("First Name");
		Locator lastName = page.getByLabel("Last Name");
		Locator email = page.getByLabel("Email");
		Locator messageField = page.getByLabel("message");
		Locator subjectDropdown = page.getByLabel("subject");

		// fill in the field values
		firstName.fill("John");
		lastName.fill("Doe");
		email.fill("test@test.com");
		messageField.fill("Test\nTest\nTest");
		subjectDropdown.selectOption("Warranty"); // also can accept array of values for multiple select

		// clear one of the field
		page.getByLabel(fieldName).clear();

		// check the error message for that field
		page.getByRole(AriaRole.ALERT).getByText("%s is required".formatted(fieldName));
	}

}
