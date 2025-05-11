import { test, expect } from '@playwright/test';

test.describe('CalendarContainer', () => {
  test.beforeEach(async ({ page }) => {
    await page.goto('http://localhost:5173');
  });

  test('should render the calendar component', async ({ page }) => {
    const calendar = await page.locator('.calendarMain');
    await expect(calendar).toBeVisible();
  });

  test('should display "+" button on a calendar day', async ({ page }) => {
    const addButton = page.locator('.addConferenceButton').first();
    await expect(addButton).toBeVisible();
  });

  test('should open creation modal on "+" button click', async ({ page }) => {
    const addButton = page.locator('.addConferenceButton').first();
    await addButton.click();

    const creationModal = page.locator('.conferenceCreationModal');
    await expect(creationModal).toBeVisible();
  });
});