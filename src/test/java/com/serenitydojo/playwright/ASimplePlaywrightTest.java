package com.serenitydojo.playwright;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ASimplePlaywrightTest extends BaseTest {

    @Test
    void shouldShowThePageTitle() {
        page.navigate("https://practicesoftwaretesting.com");

        Assertions.assertThat(page.title()).as("").contains("Practice Software Testing");
    }

    @Test
    void shouldSearchByKeywork() {
        page.navigate("https://practicesoftwaretesting.com");
        page.locator("[placeholder=Search]").fill("Pliers");
        page.locator("button:has-text('Search')").click();

        int countOfMatchingResults = page.locator(".card").count();
        Assertions.assertThat(countOfMatchingResults).as("").isGreaterThan(0);
    }

}
