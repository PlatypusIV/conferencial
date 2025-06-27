import { test, expect } from "@playwright/test";

//TODO: re write all tests
test.describe("CalendarContainer", () => {
  test.beforeEach(async ({ page }) => {
    await page.goto("http://localhost:5173");
  });
  test("should render the calendar component", async ({ page }) => {
    const calendar = await page.locator(".calendarMain");
    await expect(calendar).toBeVisible();
  });
  test("should open creation modal upon clicking a calendar cell", async ({
    page,
  }) => {
    const addButton = page.locator(".calendarConferenceListContainer").first();
    await addButton.click();
    const creationModal = page.locator(".conferenceCreationModal");
    await expect(creationModal).toBeVisible();
  });
});
