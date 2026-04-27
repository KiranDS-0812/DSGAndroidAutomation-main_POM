package dsg.tests.pages

import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.*

class ProductPage(private val composeRule: ComposeTestRule) {

    fun selectSizeIfPresent(size: String) {
        try {
            val sizeNode = composeRule.onAllNodesWithText(size, useUnmergedTree = true)
            if (sizeNode.fetchSemanticsNodes().isNotEmpty()) {
                sizeNode.onFirst().performClick()
            }
        } catch (_: Exception) {
        }
    }

    fun clickAddToCart() {
        try {
            composeRule.waitUntil(timeoutMillis = 8000) {
                composeRule.onAllNodesWithText("ADD TO CART", useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()
            }
            composeRule.onNodeWithText("ADD TO CART", useUnmergedTree = true)
                .assertIsDisplayed()
                .performClick()
        } catch (e: Exception) {
            throw AssertionError("Failed to click Add to Cart: ${e.message}")
        }
    }

    fun clickViewCart() {
        try {
            composeRule.waitUntil(timeoutMillis = 8000) {
                composeRule.onAllNodesWithTag("viewCartButton", useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()
            }
            composeRule.onNodeWithTag("viewCartButton", useUnmergedTree = true)
                .assertIsDisplayed()
                .performClick()
        } catch (e: Exception) {
            try {
                composeRule.onNodeWithTag("navigation_cart", useUnmergedTree = true)
                    .assertIsDisplayed()
                    .performClick()
            } catch (e2: Exception) {
                throw AssertionError("Failed to click View Cart: ${e2.message}")
            }
        }
    }
}