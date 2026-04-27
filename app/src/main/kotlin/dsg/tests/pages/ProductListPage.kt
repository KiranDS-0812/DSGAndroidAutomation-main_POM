package dsg.tests.pages

import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.*

class ProductListPage(private val composeRule: ComposeTestRule) {

    fun addFirstProductToCart() {
        try {
            composeRule.waitUntil(timeoutMillis = 10000) {
                composeRule.onAllNodesWithTag("productImage", useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()
            }
            composeRule.onAllNodesWithTag("productImage", useUnmergedTree = true)
                .onFirst()
                .performClick()
        } catch (e: Exception) {
            throw AssertionError("Failed to add first product to cart: ${e.message}")
        }
    }
}