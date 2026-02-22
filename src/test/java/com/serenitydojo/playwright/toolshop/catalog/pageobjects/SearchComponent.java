package com.serenitydojo.playwright.toolshop.catalog.pageobjects;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class SearchComponent {
    private final Page page;

    public SearchComponent(Page page) {
        this.page = page;
    }

    public void searchBy(String keyword) {
        page.waitForResponse("**/products/search?**", () -> {
            page.getByPlaceholder("Search").fill(keyword);
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Search")).click();
        });
        page.waitForTimeout(250);
    }

    public void clearSearch() {
        page.waitForResponse("**/products**", () -> {
            page.getByTestId("search-reset").click();
        });
        page.waitForTimeout(250);
    }

    public void filterBy(String filterName) {
        page.waitForResponse("**/products?**by_category=**", () -> {
            page.getByLabel(filterName).click();
        });
    }

    public void filterByBrand(String brandName) {
        page.waitForResponse("**/products?**by_brand=**", () -> {
            page.getByLabel(brandName).click();
        });
    }

    public void sortBy(String sortFilter) {
        page.waitForResponse("**/products?page=0&sort=**", () -> {
            page.getByTestId("sort").selectOption(sortFilter);
        });
        page.waitForTimeout(250);
    }
}