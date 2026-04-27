package com.serenitydojo.playwright.section_04;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.util.List;
import java.util.regex.Pattern;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.LocatorAssertions;
import com.microsoft.playwright.options.AriaRole;
import com.serenitydojo.playwright.BaseTest;

@DisplayName("Locating elements by role")
class LocatingElementsByRoleTest extends BaseTest {

	@DisplayName("Using the BUTTON role")
	@Test
	void byButton() {
		page.navigate("https://practicesoftwaretesting.com/contact");

		page
			.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Send"))
			.click();

		// Wait up to 3s for all 5 validation alerts to be present
		assertThat(page.getByRole(AriaRole.ALERT)).hasCount(5, new LocatorAssertions.HasCountOptions().setTimeout(3000));

		List<String> errorMessages = page.getByRole(AriaRole.ALERT).allTextContents();
		org.assertj.core.api.Assertions.assertThat(errorMessages)
			.as("Expected 5 validation alerts for empty contact form: first name, last name, email, subject, message")
			.containsExactlyInAnyOrderElementsOf(List.of(
				"First name is required",
				"Last name is required",
				"Email is required",
				"Subject is required",
				"Message is required"
			));
	}

	@DisplayName("Using the HEADING role")
	@Test
	void byHeaderRole() {
		openPage();

		page.locator("#search-query").fill("Pliers");

		page.getByRole(AriaRole.BUTTON,
				new Page.GetByRoleOptions().setName("Search"))
			.click();

		Locator searchHeading = page.getByRole(AriaRole.HEADING,
			new Page.GetByRoleOptions().setName(Pattern.compile("Searched for:.*")));

		assertThat(searchHeading).isVisible();
		assertThat(searchHeading).hasText("Searched for: Pliers");
	}

	@DisplayName("Using the HEADING role and level")
	@Test
	void byHeaderRoleLevel() {
		openPage();

		Locator headings = page.getByRole(
			AriaRole.HEADING,
			new Page.GetByRoleOptions().setLevel(5)
		);
		assertThat(headings.first()).isVisible();

		List<String> level4Headings = headings.allTextContents();

		org.assertj.core.api.Assertions.assertThat(level4Headings).isNotEmpty();
	}

	@DisplayName("Identifying checkboxes")
	@Test
	void byCheckboxes() {
		//		playwright.selectors().setTestIdAttribute("data-test");

		openPage();
		page.getByLabel("Hammer").click();
		page.getByLabel("Chisels").click();
		page.getByLabel("Wrench").click();

		Locator checkedFilters = page.locator("#filters")
			.getByRole(
				AriaRole.CHECKBOX,
				new Locator.GetByRoleOptions().setChecked(true)
			);
		// Wait until UI reflects the 3 checked options (up to 2s here)
		assertThat(checkedFilters).hasCount(
			3,
			new LocatorAssertions.HasCountOptions().setTimeout(2000)
		);

		int selectedCount = checkedFilters.count();

		org.assertj.core.api.Assertions.assertThat(selectedCount).isEqualTo(3);

		List<String> selectedOptions =
			page.locator("#filters")
				.getByRole(AriaRole.CHECKBOX,
					new Locator.GetByRoleOptions().setChecked(true))
				.all()
				.stream()
				.map(Locator::inputValue)
				.toList();

		org.assertj.core.api.Assertions.assertThat(selectedOptions).hasSize(3);
	}

}
