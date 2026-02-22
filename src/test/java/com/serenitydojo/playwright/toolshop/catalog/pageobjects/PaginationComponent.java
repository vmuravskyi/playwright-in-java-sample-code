package com.serenitydojo.playwright.toolshop.catalog.pageobjects;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import io.qameta.allure.Step;

public class PaginationComponent {
    private final Page page;

    public PaginationComponent(Page page) {
        this.page = page;
    }

    @Step("Go to page {pageNumber}")
    public void goToPage(int pageNumber) {
        page.waitForResponse("**/products**", () -> {
            page.getByLabel("Page-" + pageNumber).click();
        });
    }

    @Step("Go to next page")
    public void goToNextPage() {
        page.waitForResponse("**/products**", () -> {
            page.getByLabel("Next").click();
        });
    }

    @Step("Go to previous page")
    public void goToPreviousPage() {
        page.waitForResponse("**/products**", () -> {
            page.getByLabel("Previous").click();
        });
    }

    public int getActivePageNumber() {
        String text = page.locator("li.page-item.active a.page-link").textContent();
        return Integer.parseInt(text.trim());
    }

    public boolean isVisible() {
        return page.locator("nav ul.pagination").count() > 0;
    }

    public boolean isPreviousEnabled() {
        Locator previousItem = page.getByLabel("Previous").locator("xpath=ancestor::li");
        return !previousItem.getAttribute("class").contains("disabled");
    }

    public boolean isNextEnabled() {
        Locator nextItem = page.getByLabel("Next").locator("xpath=ancestor::li");
        return !nextItem.getAttribute("class").contains("disabled");
    }

    public int getPageCount() {
        return page.locator("nav ul.pagination li.page-item").all()
                .stream()
                .filter(li -> {
                    String label = li.locator("a.page-link").getAttribute("aria-label");
                    return label != null && label.startsWith("Page-");
                })
                .toList()
                .size();
    }
}
