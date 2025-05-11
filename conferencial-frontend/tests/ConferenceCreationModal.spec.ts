import { test, expect } from '@playwright/test';

test.describe('ConferenceCreationModal', () => {

  test.beforeEach(async ({ page }) => {
    await page.goto('http://localhost:5173');
    await page.route('**/rooms', async (route) => {
        route.fulfill({
          status: 200,
          contentType: 'application/json',
          body: JSON.stringify([
            { id: 1, name: 'Room A', maxSeats: 10 },
            { id: 2, name: 'Room B', maxSeats: 15 }
          ]),
        });
      });
    await page.locator('.addConferenceButton').first().click();
  });

  test('should render the modal with form fields', async ({ page }) => {
    const modal = page.locator('.conferenceCreationModal');

    await expect(modal).toBeVisible();
    await expect(modal.locator('text=Create conference')).toBeVisible();
    await expect(modal.locator('input[placeholder="Example conference"]')).toBeVisible();
    await expect(modal.getByLabel('duration')).toBeVisible();
    await expect(modal.locator('input[placeholder="Start time"]')).toBeVisible();
    await expect(modal.locator('input[placeholder="End time"]')).toBeVisible();
    await expect(modal.getByLabel('room')).toBeVisible();
  });

  test('should show validation messages on empty submit', async ({ page }) => {
    const submitButton = page.getByRole('button', { name: 'Submit' });
    await submitButton.click();

    await expect(page.locator('text=Please input conference name')).toBeVisible();
    await expect(page.locator('text=Please input conference duration')).toBeVisible();
    await expect(page.locator('text=Please select a room')).toBeVisible();
  });

});
