package com.serenitydojo.playwright.section_06_assertions;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.SelectOption;
import com.serenitydojo.playwright.BaseTest;
import com.serenitydojo.playwright.config.PlaywrightManager;

class AssertionTest extends BaseTest {

	@BeforeAll
	static void beforeAll() {
		PlaywrightManager.setTestIdAttribute("data-test");
	}

	@AfterAll
	static void afterAll() {
		PlaywrightManager.resetTestIdAttribute();
	}

	@BeforeEach
	void openContactPage() {
		page.navigate("https://practicesoftwaretesting.com");
		page.waitForCondition(() -> page.getByTestId("product-name").count() > 0); // wait for products to render
	}

	@DisplayName("Checking the value of a field")
	@Test
	void valueFields() {
		page.getByTestId("nav-contact").click(); // navigate to contact page

		Locator firstName = page.getByLabel("First Name");
		firstName.fill("John");
		PlaywrightAssertions.assertThat(firstName).hasValue("John");

		PlaywrightAssertions.assertThat(firstName).not().isDisabled();

		PlaywrightAssertions.assertThat(firstName).isVisible();

		PlaywrightAssertions.assertThat(firstName).isEditable();
	}

	@DisplayName("Using AsserJ assertions")
	@Test
	void assertJTest() {
		List<Double> productPrices = page.getByTestId("product-price").allInnerTexts()
			.stream()
			.map(s -> s.replace("$", ""))
			.map(Double::parseDouble)
			.toList();

		Assertions.assertThat(productPrices)
			.isNotEmpty()
			.allMatch(price -> price > 0)
			.doesNotContain(0.0)
			.allMatch(price -> price < 1000)
			.allSatisfy(price ->
				Assertions.assertThat(price)
					.as("")
					.isGreaterThan(0)
					.isLessThan(1000));
	}

	@DisplayName("Using AsserJ assertions for product name sorting test")
	@Test
	void shouldSortInAlphabeticOrder() {
		page.getByLabel("sort").selectOption("Name (A - Z)");

		// applying sorting takes time, wait for the products to be sorted
		page.waitForCondition(() -> {
			List<String> currentProductNames = page.getByTestId("product-name").allInnerTexts()
				.stream()
				.map(String::trim)
				.toList();

			List<String> sortedProductNames = currentProductNames.stream()
				.sorted(String.CASE_INSENSITIVE_ORDER)
				.toList();

			return !currentProductNames.isEmpty()
				&& currentProductNames.equals(sortedProductNames);
		}, new Page.WaitForConditionOptions().setTimeout(3000));

		List<String> productNames = page.getByTestId("product-name").allInnerTexts()
			.stream()
			.map(String::trim)
			.toList();

		//		Assertions.assertThat(productNames).isSortedAccordingTo(Comparator.naturalOrder());
		Assertions.assertThat(productNames)
			.isSortedAccordingTo(String.CASE_INSENSITIVE_ORDER);
	}

	@DisplayName("Using AsserJ assertions for price sorting test")
	@Test
	void shouldSortPriceInOrder() {
		page.getByLabel("sort").selectOption(new SelectOption().setLabel("Name (A - Z)"));

		List<Double> productPrices = page.getByTestId("product-price").allInnerTexts()
			.stream()
			.map(s -> s.replace("$", ""))
			.map(Double::parseDouble)
			.toList();

		ArrayList<Double> sortedPrices = new ArrayList<>(productPrices);
		sortedPrices.sort(Double::compareTo);

		Assertions.assertThat(productPrices).containsExactlyElementsOf(sortedPrices);
	}

}
