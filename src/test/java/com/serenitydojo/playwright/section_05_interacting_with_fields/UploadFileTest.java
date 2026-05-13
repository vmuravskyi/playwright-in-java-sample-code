package com.serenitydojo.playwright.section_05_interacting_with_fields;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.serenitydojo.playwright.BaseTest;

class UploadFileTest extends BaseTest {

	@BeforeEach
	void openContactPage() {
		page.navigate("https://practicesoftwaretesting.com/contact");
	}

	@DisplayName("Upload File")
	@Test
	void updateFile() {
		Locator firstName = page.getByLabel("First Name");
		Locator lastName = page.getByLabel("Last Name");
		Locator email = page.getByLabel("Email");
		Locator messageField = page.getByLabel("Message");
		Locator subjectDropdown = page.getByLabel("Subject"); // dropdown
		Locator uploadField = page.getByLabel("Attachment"); // upload field

		firstName.fill("John");
		lastName.fill("Doe");
		email.fill("test@test.com");
		messageField.fill("Test\nTest\nTest");
		subjectDropdown.selectOption("Warranty"); // also can accept array of values for multiple select

		Path fileToUpload = Path.of(ClassLoader.getSystemResource("test-data/sample-data.txt").getPath());
//		page.setInputFiles("#attachment",  fileToUpload); // upload the file through interacting with page, not uploadField locator
		uploadField.setInputFiles(fileToUpload); // upload through the locator directly instead of using the selector

		PlaywrightAssertions.assertThat(firstName).hasValue("John");
		PlaywrightAssertions.assertThat(lastName).hasValue("Doe");
		PlaywrightAssertions.assertThat(email).hasValue("test@test.com");
		PlaywrightAssertions.assertThat(messageField).hasValue("Test\nTest\nTest");
		PlaywrightAssertions
			.assertThat(subjectDropdown.locator("option:checked")) // get actual visible selected text
			.hasText("Warranty");

		// assert uploaded file with Playwright assertion
		PlaywrightAssertions
			.assertThat(uploadField)
			.hasValue(java.util.regex.Pattern.compile(".*sample-data\\.txt$"));

		// alternative way to verify uploaded file
		String uploadedFile = uploadField.inputValue();
		Assertions.assertThat(uploadedFile)
			.as("Verifying uploaded file")
			.endsWith("sample-data.txt");
	}

}
