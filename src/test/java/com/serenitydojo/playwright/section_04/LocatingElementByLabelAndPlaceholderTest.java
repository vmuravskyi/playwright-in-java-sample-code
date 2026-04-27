package com.serenitydojo.playwright.section_04;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.SelectOption;
import com.serenitydojo.playwright.BaseTest;

@DisplayName("Locating elements by Label and Placeholder")
class LocatingElementByLabelAndPlaceholderTest extends BaseTest {

	@DisplayName("Using a label")
	@Test
	void byLabel() {
		page.navigate("https://practicesoftwaretesting.com/contact");

		page.getByLabel("First name").fill("Obi-Wan");
		page.getByLabel("Last name").fill("Kenobi");
		page.getByLabel("Email address").fill("obi-wan@kenobi.com");
		page.getByLabel("Subject").selectOption(new SelectOption().setLabel("Customer service"));
		page.getByLabel("Message *").fill("Hello there");
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Send"));
	}

	@DisplayName("Using a placeholder text")
	@Test
	void byPlaceholder() {
		page.navigate("https://practicesoftwaretesting.com/contact");

		page.getByPlaceholder("Your first name").fill("Obi-Wan");

		page.getByPlaceholder("Your last name").fill("Kenobi");
		page.getByPlaceholder("Your email").fill("obi-wan@kenobi.com");
		page.getByLabel("Subject").selectOption(new SelectOption().setLabel("Customer service"));
		page.getByLabel("Message *").fill("Hello there");
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Send"));
	}

}
