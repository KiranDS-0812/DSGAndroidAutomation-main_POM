package com.dcsg.espresso_uitests.pages

import android.view.View
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.test.espresso.Espresso
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.dcsg.espresso_uitests.identifiers.DSGProductListingTags
import com.dcsg.espresso_uitests.identifiers.ProductTags
import org.hamcrest.Matcher
import org.hamcrest.Matchers

class DSGProductListPage(private val composeTestRule: ComposeTestRule) {

    /**
     * Waits for the product list to be displayed.
     */
    fun waitForPageLoad(timeoutMillis: Long = 30000): DSGProductListPage {
        composeTestRule.waitUntil(timeoutMillis) {
            try {
                // Wait for the list container or at least one product card to be present
                composeTestRule.onAllNodesWithTag(DSGProductListingTags.PRODUCT_LIST, useUnmergedTree = true)
                    .fetchSemanticsNodes().isNotEmpty() ||
                composeTestRule.onAllNodesWithTag(DSGProductListingTags.PRODUCT_CARD, useUnmergedTree = true)
                    .fetchSemanticsNodes().isNotEmpty()
            } catch (e: Exception) {
                false
            }
        }
        return this
    }

    /**
     * Alias for waitForPageLoad to support existing test calls.
     */
    fun waitForProductsToLoad(timeoutMillis: Long = 30000): DSGProductListPage {
        return waitForPageLoad(timeoutMillis)
    }

    /**
     * Verifies that the product card details are visible.
     */
    fun verifyProductCardDetails() {
        // Wait specifically for at least one product card to be displayed on screen.
        composeTestRule.waitUntil(20000) {
            try {
                composeTestRule.onAllNodesWithTag(DSGProductListingTags.PRODUCT_CARD, useUnmergedTree = true)
                    .onFirst()
                    .assertIsDisplayed()
                true
            } catch (e: Throwable) {
                false
            }
        }

        // Assert the first product card is displayed
        composeTestRule.onAllNodesWithTag(DSGProductListingTags.PRODUCT_CARD, useUnmergedTree = true)
            .onFirst()
            .assertIsDisplayed()

        // Verify key product card components within the first card
        composeTestRule.onAllNodesWithTag(DSGProductListingTags.PRODUCT_IMAGE, useUnmergedTree = true)
            .onFirst()
            .assertIsDisplayed()

        composeTestRule.onAllNodesWithTag(DSGProductListingTags.PRODUCT_TITLE, useUnmergedTree = true)
            .onFirst()
            .assertIsDisplayed()

        composeTestRule.onAllNodesWithTag(DSGProductListingTags.PRODUCT_PRICE, useUnmergedTree = true)
            .onFirst()
            .assertIsDisplayed()
    }

    /**
     * Taps on the second color swatch of the first product card.
     */
    fun tapOnColorSwatch() {
        // Find the first product card's swatch container and click the second swatch
        composeTestRule.onAllNodesWithTag(DSGProductListingTags.COLOR_SWATCH, useUnmergedTree = true)
            .onFirst()
            .let {
                val swatches = composeTestRule.onAllNodesWithTag(DSGProductListingTags.COLOR_SWATCH, useUnmergedTree = true)
                if (swatches.fetchSemanticsNodes().size > 1) {
                    swatches[1].performClick()
                } else {
                    swatches[0].performClick()
                }
            }

        composeTestRule.waitForIdle()
    }

    /**
     * Verifies that the product image is displayed.
     */
    fun verifyProductImageUpdates() {
        composeTestRule.onAllNodesWithTag(DSGProductListingTags.PRODUCT_IMAGE, useUnmergedTree = true)
            .onFirst()
            .assertIsDisplayed()
    }

    /**
     * Verifies that the promotional message is displayed for at least one product.
     * Scrolls if necessary to find it.
     */
    fun verifyPromotionalMessageDisplayed() {
        var found = false
        val maxScrolls = 5

        for (i in 0 until maxScrolls) {
            val nodes = composeTestRule.onAllNodesWithTag(DSGProductListingTags.PROMOTION_MESSAGE, useUnmergedTree = true)
                .fetchSemanticsNodes()

            if (nodes.isNotEmpty()) {
                found = true
                break
            }

            // Swipe up to scroll down and reveal more products
            composeTestRule.onNodeWithTag(DSGProductListingTags.PRODUCT_LIST, useUnmergedTree = true)
                .performTouchInput { swipeUp() }
            composeTestRule.waitForIdle()
        }

        if (!found) {
            // Final attempt with standard wait if scrolling didn't help immediately
            composeTestRule.waitUntil(10000) {
                composeTestRule.onAllNodesWithTag(DSGProductListingTags.PROMOTION_MESSAGE, useUnmergedTree = true)
                    .fetchSemanticsNodes().isNotEmpty()
            }
        }

        composeTestRule.onAllNodesWithTag(DSGProductListingTags.PROMOTION_MESSAGE, useUnmergedTree = true)
            .onFirst()
            .assertIsDisplayed()
    }

    /**
     * Taps on the first product card to navigate to PDP.
     */
    fun selectFirstProduct() {
        composeTestRule.waitUntil(30000) {
            try {
                composeTestRule.onAllNodes(hasTestTag("productImage") or hasTestTag("productTitleText"), useUnmergedTree = true)
                    .fetchSemanticsNodes().isNotEmpty()
            } catch (e: Exception) { false }
        }

        // Target the first product components
        val productMatcher = (hasTestTag("productImage") or hasTestTag("productTitleText"))

        composeTestRule.onAllNodes(productMatcher, useUnmergedTree = true).onFirst().performClick()

        composeTestRule.waitForIdle()
        // Small delay to allow the PDP transition to settle
        Thread.sleep(2000)
    }

    /**
     * Taps on the "Add to Cart" button specifically on the PLP product card.
     * This action opens the Quick View modal.
     */
    fun clickAddToCartOnPLP() {
        composeTestRule.waitUntil(30000) {
            try {
                composeTestRule.onAllNodesWithTag(ProductTags.ADD_TO_CART_BUTTON, useUnmergedTree = true)
                    .fetchSemanticsNodes().isNotEmpty()
            } catch (e: Throwable) { false }
        }

        // Try to scroll to the node first to ensure it's hit-testable
        try {
            composeTestRule.onAllNodesWithTag(ProductTags.ADD_TO_CART_BUTTON, useUnmergedTree = true)
                .onFirst()
                .performScrollTo()
        } catch (e: Exception) {}

        // Use touch input click for better reliability
        composeTestRule.onAllNodesWithTag(ProductTags.ADD_TO_CART_BUTTON, useUnmergedTree = true)
            .onFirst()
            .performTouchInput { click() }

        composeTestRule.waitForIdle()
        // Give time for the Quick View modal to animate in
        Thread.sleep(3000)
    }

    /**
     * Validates that all key product card elements are displayed on the PLP.
     */
    fun validateProductCardElements() {
        // Core elements check
        verifyProductCardDetails()

        // Additional elements for Scenario 015
        composeTestRule.onAllNodesWithTag(DSGProductListingTags.PRODUCT_FAVORITE_ICON, useUnmergedTree = true)
            .onFirst()
            .assertIsDisplayed()

        // Some products might have color swatches, check if any exist
        val swatches = composeTestRule.onAllNodesWithTag(DSGProductListingTags.COLOR_SWATCH, useUnmergedTree = true)
            .fetchSemanticsNodes()
        if (swatches.isNotEmpty()) {
            composeTestRule.onAllNodesWithTag(DSGProductListingTags.COLOR_SWATCH, useUnmergedTree = true)
                .onFirst()
                .assertIsDisplayed()
        }
    }

    /**
     * Validates that the Quick View modal is displayed.
     * Robust check for both Compose and XML elements in the modal.
     */
    fun verifyQuickViewDisplayed() {
        composeTestRule.waitUntil(45000) {
            try {
                // Check for attribute container (Compose)
                val composeCheck = composeTestRule.onAllNodesWithTag("AttributeWidgetContainer", useUnmergedTree = true)
                    .fetchSemanticsNodes().isNotEmpty()

                if (composeCheck) return@waitUntil true

                // Fallback: Check for the "ADD TO CART" button (XML) using Espresso with relaxed constraints
                var espressoCheck = false
                try {
                    androidx.test.espresso.Espresso.onView(
                        org.hamcrest.Matchers.anyOf(
                            androidx.test.espresso.matcher.ViewMatchers.withId(com.dcsg.shop.R.id.info_add_to_cart_button),
                            androidx.test.espresso.matcher.ViewMatchers.withText(org.hamcrest.Matchers.equalToIgnoringCase("ADD TO CART"))
                        )
                    ).check(androidx.test.espresso.assertion.ViewAssertions.matches(androidx.test.espresso.matcher.ViewMatchers.isDisplayed()))
                    espressoCheck = true
                } catch (e: Throwable) {
                    try {
                        androidx.test.espresso.Espresso.onView(androidx.test.espresso.matcher.ViewMatchers.withId(com.dcsg.shop.R.id.info_add_to_cart_button))
                            .check { view, _ -> if (view == null || view.visibility != android.view.View.VISIBLE) throw Exception() }
                        espressoCheck = true
                    } catch (e2: Throwable) {
                        espressoCheck = false
                    }
                }

                espressoCheck
            } catch (e: Exception) { false }
        }
    }

    /**
     * Selects all available attributes in the Quick View modal.
     */
    fun selectAllAttributesInQuickView() {
        try {
            composeTestRule.waitUntil(15000) {
                try {
                    composeTestRule.onAllNodesWithTag("AttributeWidgetContainer", useUnmergedTree = true)
                        .fetchSemanticsNodes().isNotEmpty()
                } catch (e: Exception) { false }
            }

            val sections = composeTestRule.onAllNodesWithTag("AttributeWidgetContainer", useUnmergedTree = true)
            val sectionCount = sections.fetchSemanticsNodes().size

            for (i in 0 until sectionCount) {
                val valueMatcher = hasTestTag("${ProductTags.ATTRIBUTE_VALUE_PREFIX}0")
                try {
                    composeTestRule.onAllNodes(valueMatcher, useUnmergedTree = true)
                        .onFirst()
                        .performClick()
                    composeTestRule.waitForIdle()
                    Thread.sleep(1000) 
                } catch (e: Exception) {}
            }
        } catch (e: Throwable) {}
    }

    /**
     * FORCE CLICK Implementation for Sticky Footer / XML Buttons
     */
    private fun forceClick(): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> = ViewMatchers.isDisplayed()
            override fun getDescription(): String = "force click"
            override fun perform(uiController: UiController, view: View) {
                view.performClick()
            }
        }
    }

    /**
     * Clicks the "ADD TO CART" button inside the Quick View modal.
     * Updated with Force Click to handle sticky footers and different stacks.
     */
    fun clickAddToCartInQuickView() {
        composeTestRule.waitForIdle()
        Thread.sleep(2000)

        val buttonId = com.dcsg.shop.R.id.info_add_to_cart_button
        var success = false

        // Strategy 1: Espresso Force Click (direct view.performClick())
        try {
            Espresso.onView(ViewMatchers.withId(buttonId)).perform(forceClick())
            success = true
        } catch (e: Throwable) {
            // Strategy 2: Compose click
            try {
                composeTestRule.onAllNodes(hasText("ADD TO CART", ignoreCase = true), useUnmergedTree = true)
                    .onFirst()
                    .performClick()
                success = true
            } catch (e2: Throwable) {
                // Strategy 3: Espresso standard click by text
                try {
                    Espresso.onView(ViewMatchers.withText(Matchers.equalToIgnoringCase("ADD TO CART")))
                        .perform(ViewActions.click())
                    success = true
                } catch (e3: Throwable) {}
            }
        }

        if (!success) {
            error("FAILED to click 'ADD TO CART' button in Quick View modal.")
        }

        Thread.sleep(4000)
        composeTestRule.waitForIdle()
    }

    /**
     * Validates that product tile has Compare check boxes visible.
     */
    fun validateCompareCheckboxesVisible() {
        composeTestRule.waitUntil(20000) {
            try {
                composeTestRule.onAllNodesWithTag(DSGProductListingTags.COMPARE_CHECKBOX, useUnmergedTree = true)
                    .fetchSemanticsNodes().isNotEmpty()
            } catch (e: Throwable) {
                false
            }
        }

        composeTestRule.onAllNodesWithTag(DSGProductListingTags.COMPARE_CHECKBOX, useUnmergedTree = true)
            .onFirst()
            .assertIsDisplayed()
    }

    /**
     * Selects Compare check box for product at given index.
     * Includes robust scrolling to ensure the checkbox is not covered by footers/comparison sheet.
     */
    fun selectCompareCheckboxAtIndex(index: Int) {
        // Ensure the list is present
        composeTestRule.onNodeWithTag(DSGProductListingTags.PRODUCT_LIST, useUnmergedTree = true).assertExists()

        // 1. Wait for the product card at given index to be available, scrolling if necessary
        composeTestRule.waitUntil(30000) {
            try {
                val nodes = composeTestRule.onAllNodesWithTag(DSGProductListingTags.PRODUCT_CARD, useUnmergedTree = true)
                    .fetchSemanticsNodes()
                if (nodes.size > index) return@waitUntil true
                
                // Swipe up to load more products
                composeTestRule.onNodeWithTag(DSGProductListingTags.PRODUCT_LIST, useUnmergedTree = true)
                    .performTouchInput { swipeUp() }
                false
            } catch (e: Throwable) { false }
        }

        // 2. Scroll the specific card into view first
        composeTestRule.onAllNodesWithTag(DSGProductListingTags.PRODUCT_CARD, useUnmergedTree = true)[2]
            .performScrollTo()
        composeTestRule.waitForIdle()

        // 3. CRITICAL: If the comparison sheet is visible, we must scroll UP (content moves up) 
        // to bring the item out from under the sheet which covers the bottom ~20% of the screen.
        val isSheetVisible = try {
            composeTestRule.onNodeWithTag(DSGProductListingTags.COMPARE_SHEET, useUnmergedTree = true).assertIsDisplayed()
            true
        } catch (e: Throwable) { false }

        if (isSheetVisible) {
            // Swipe content up to move our target card away from the bottom footer
            composeTestRule.onNodeWithTag(DSGProductListingTags.PRODUCT_LIST, useUnmergedTree = true)
                .performTouchInput { 
                    swipeUp(startY = 0.8f, endY = 0.3f, durationMillis = 500)
                }
            composeTestRule.waitForIdle()
            Thread.sleep(1000) // Allow layout to settle
        }

        // 4. Click the checkbox. We re-query to ensure we have the correct node after scrolling.
        composeTestRule.onAllNodesWithTag(DSGProductListingTags.COMPARE_CHECKBOX, useUnmergedTree = true)[index]
            .performTouchInput { click() }
        
        composeTestRule.waitForIdle()
        Thread.sleep(2000) // Give time for state update to propagate
    }

    /**
     * Verifies Product comparison container is displayed with one product added.
     */
    fun verifyCompareSheetWithOneProduct() {
        composeTestRule.waitUntil(20000) {
            try {
                composeTestRule.onAllNodesWithTag(DSGProductListingTags.COMPARE_SHEET, useUnmergedTree = true)
                    .fetchSemanticsNodes().isNotEmpty()
            } catch (e: Throwable) { false }
        }
        composeTestRule.onNodeWithTag(DSGProductListingTags.COMPARE_SHEET, useUnmergedTree = true)
            .assertIsDisplayed()

        // Verify exactly one product slot is occupied (contains an image)
        composeTestRule.onAllNodesWithTag(DSGProductListingTags.COMPARE_PRODUCT_SLOT, useUnmergedTree = true)
            .assertCountEquals(1)
    }

    /**
     * Verifies Compare product button "disabled" or enabled state.
     * Uses waitUntil to handle the asynchronous nature of button enabling.
     */
    fun verifyCompareButtonState(expectedState: String) {
        val isEnabledExpected = expectedState.lowercase() != "disabled"
        
        composeTestRule.waitUntil(30000) {
            try {
                val button = composeTestRule.onNodeWithTag(DSGProductListingTags.COMPARE_PRIMARY_BUTTON, useUnmergedTree = true)
                if (isEnabledExpected) {
                    button.assertIsEnabled()
                } else {
                    button.assertIsNotEnabled()
                }
                true
            } catch (e: Throwable) {
                false
            }
        }
    }

    /**
     * Closes the comparison sheet by clicking the close button.
     * Handles the confirmation popup.
     */
    fun closeCompareSheet() {
        // 1. Wait for the Cross button to be ready
        composeTestRule.waitUntil(20000) {
            try {
                composeTestRule.onAllNodesWithTag(DSGProductListingTags.COMPARE_CLOSE_BUTTON, useUnmergedTree = true)
                    .fetchSemanticsNodes().isNotEmpty()
            } catch (e: Throwable) { false }
        }

        // 2. Click the Cross button
        composeTestRule.onNodeWithTag(DSGProductListingTags.COMPARE_CLOSE_BUTTON, useUnmergedTree = true)
            .performClick()

        // 3. Wait specifically for the "Close List" option to appear in the popup
        composeTestRule.waitUntil(20000) {
            try {
                composeTestRule.onAllNodes(hasText("Close List", ignoreCase = true), useUnmergedTree = true)
                    .fetchSemanticsNodes().isNotEmpty()
            } catch (e: Throwable) { false }
        }

        // 4. Click the "Close List" button
        composeTestRule.onNode(hasText("Close List", ignoreCase = true), useUnmergedTree = true)
            .performClick()

        // 5. Wait for the sheet to effectively disappear
        composeTestRule.waitUntil(15000) {
            try {
                val nodes = composeTestRule.onAllNodesWithTag(DSGProductListingTags.COMPARE_SHEET, useUnmergedTree = true)
                    .fetchSemanticsNodes()

                if (nodes.isEmpty()) return@waitUntil true

                !composeTestRule.onNodeWithTag(DSGProductListingTags.COMPARE_SHEET, useUnmergedTree = true)
                    .fetchSemanticsNode().layoutInfo.isPlaced
            } catch (e: Throwable) {
                true
            }
        }
    }

    /**
     * Removes a product from the comparison container at the specified index.
     * Index 0 is the first product, index 1 is the second product in the container.
     */
    fun removeProductFromCompareAtIndex(index: Int) {
        composeTestRule.waitUntil(20000) {
            try {
                composeTestRule.onAllNodesWithTag(DSGProductListingTags.COMPARE_REMOVE_BUTTON, useUnmergedTree = true)
                    .fetchSemanticsNodes().size > index
            } catch (e: Throwable) { false }
        }

        composeTestRule.onAllNodesWithTag(DSGProductListingTags.COMPARE_REMOVE_BUTTON, useUnmergedTree = true)[index]
            .performClick()
        
        composeTestRule.waitForIdle()
        Thread.sleep(1000)
    }

    /**
     * Verifies that the confirmation popup for removing products is displayed.
     */
    fun verifyRemovalConfirmationPopup() {
        composeTestRule.waitUntil(20000) {
            try {
                composeTestRule.onNodeWithTag(DSGProductListingTags.COMPARE_ALERT_DIALOG, useUnmergedTree = true)
                    .assertIsDisplayed()
                true
            } catch (e: Throwable) {
                false
            }
        }
    }

    /**
     * Validates that pinned content cards are displayed.
     * Searches for pinned card elements and asserts their visibility.
     */
    fun validatePinnedContentCards() {
        var found = false
        val maxScrolls = 10

        for (i in 0 until maxScrolls) {
            val nodes = composeTestRule.onAllNodesWithTag(DSGProductListingTags.PINNED_PRODUCT, useUnmergedTree = true)
                .fetchSemanticsNodes()

            if (nodes.isNotEmpty()) {
                found = true
                break
            }

            // Swipe up to scroll down and reveal more items
            try {
                composeTestRule.onRoot().performTouchInput { swipeUp() }
            } catch (e: Exception) {
                try {
                    composeTestRule.onAllNodes(hasScrollAction(), useUnmergedTree = true).onLast().performTouchInput { swipeUp() }
                } catch (e2: Exception) {}
            }
            composeTestRule.waitForIdle()
            Thread.sleep(1000)
        }

        if (!found) {
            composeTestRule.waitUntil(10000) {
                composeTestRule.onAllNodesWithTag(DSGProductListingTags.PINNED_PRODUCT, useUnmergedTree = true)
                    .fetchSemanticsNodes().isNotEmpty()
            }
        }

        // Use performScrollTo() before assertion to ensure visibility and fix display errors
        composeTestRule.onAllNodesWithTag(DSGProductListingTags.PINNED_PRODUCT, useUnmergedTree = true)
            .onFirst()
            .performScrollTo()
            .assertIsDisplayed()

        composeTestRule.onAllNodesWithTag(DSGProductListingTags.PINNED_CONTENT_TITLE, useUnmergedTree = true)
            .onFirst()
            .performScrollTo()
            .assertIsDisplayed()

        composeTestRule.onAllNodesWithTag(DSGProductListingTags.PINNED_CONTENT_IMAGE, useUnmergedTree = true)
            .onFirst()
            .performScrollTo()
            .assertIsDisplayed()
    }
}
