package com.serenitydojo.playwright.toolshop.catalog.pageobjects;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import net.serenitybdd.annotations.Step;

public class SearchComponent {
    private final Page page;

    public SearchComponent(Page page) {
        this.page = page;
    }

    @Step("Search for {0}")
    public void searchBy(String keyword) {
        page.waitForResponse("**/products/search?**", () -> {
            page.getByPlaceholder("Search").fill(keyword);
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Search")).click();
        });
        page.waitForTimeout(250);
    }

    @Step("Clear search")
    public void clearSearch() {
        page.waitForResponse("**/products**", () -> {
            page.getByTestId("search-reset").click();
        });
        page.waitForTimeout(250);
    }

    @Step("Filter by {0}")
    public void filterBy(String filterName) {
        page.waitForResponse("**/products?**by_category=**", () -> {
            page.getByLabel(filterName).click();
        });
    }

    @Step("Sort by {0}")
    public void sortBy(String sortFilter) {
        page.waitForResponse("**/products?page=0&sort=**", () -> {
            page.getByTestId("sort").selectOption(sortFilter);
        });
        page.waitForTimeout(250);
    }
}