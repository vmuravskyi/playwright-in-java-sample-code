package com.serenitydojo.playwright.section_04;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.serenitydojo.playwright.BaseTest;
import com.serenitydojo.playwright.config.PlaywrightManager;

@DisplayName("Locating elements by test ID")
class LocationElementsByTestIdTest extends BaseTest{

	@BeforeAll
	static void setTestId() {
		PlaywrightManager.setTestIdAttribute("data-test");
	}

	@AfterAll
	static void tearDown() {
		PlaywrightManager.resetTestIdAttribute();
	}

	@DisplayName("Using a custom data-test field")
	@Test
	void byTestId() {
		openPage();
		PlaywrightManager.setTestIdAttribute("data-test");

		page.getByTestId("search-query").fill("Pliers");
		page.getByTestId("search-submit").click();
	}

}
