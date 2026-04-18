package com.serenitydojo.playwright.annotated;

import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.Options;
import com.microsoft.playwright.junit.OptionsFactory;
import com.microsoft.playwright.junit.UsePlaywright;
import java.util.Arrays;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

@UsePlaywright(AnAnnotatedPlaywrightTest.MyOptions.class)
class AnAnnotatedPlaywrightTest {

    @Test
    void shouldShowThePageTitle(Page page) {
        page.navigate("https://practicesoftwaretesting.com");

        Assertions.assertThat(page.title()).as("").contains("Practice Software Testing");
    }

    @Test
    void shouldSearchByKeywork(Page page) {
        page.navigate("https://practicesoftwaretesting.com");
        page.locator("[placeholder=Search]").fill("Pliers");
        page.locator("button:has-text('Search')").click();

        int countOfMatchingResults = page.locator(".card").count();
        Assertions.assertThat(countOfMatchingResults).as("").isGreaterThan(0);
    }

    public static class MyOptions implements OptionsFactory {

        @Override
        public Options getOptions() {
            return new Options()
                .setHeadless(false)
                .setLaunchOptions(
                    new BrowserType.LaunchOptions()
                        .setArgs(Arrays.asList("--no-sandbox", "--disable-gpu"))
                );
        }
    }

}
