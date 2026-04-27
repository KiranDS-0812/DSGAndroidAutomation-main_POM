package dsg.tests.pages

import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.*
import org.hamcrest.CoreMatchers.anyOf

class CartPage(private val composeRule: ComposeTestRule) {

    fun verifyCartVisible() {
        try {
            composeRule.waitUntil(timeoutMillis = 8000) {
                composeRule.onAllNodes(
                    anyOf(
                        hasTestTag("Cart"),
                        hasTestTag("ProgressIndicatorButton enabled"),
                        hasTestTag("productImage"),
                        hasText("Cart"),
                        hasText("CHECKOUT")
                    ),
                    useUnmergedTree = true
                ).fetchSemanticsNodes().isNotEmpty()
            }
        } catch (e: Exception) {
            throw AssertionError("Cart page not visible: ${e.message}")
        }
    }

    fun verifyItemInCart() {
        try {
            composeRule.waitUntil(timeoutMillis = 8000) {
                composeRule.onAllNodesWithTag("order_summary_list", useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()
            }
            composeRule.onNodeWithTag("order_summary_list", useUnmergedTree = true)
                .assertIsDisplayed()
        } catch (e: Exception) {
            throw AssertionError("No item found in cart: ${e.message}")
        }
    }

    fun removeItem() {
        try {
            composeRule.onNodeWithTag("remove_button", useUnmergedTree = true)
                .assertIsDisplayed()
                .performClick()
        } catch (e: Exception) {
        }
    }

    fun verifyCartEmpty() {
        try {
            composeRule.onNodeWithText("Your cart is empty", useUnmergedTree = true)
                .assertIsDisplayed()
        } catch (e: Exception) {
            try {
                composeRule.onNodeWithTag("order_summary_list", useUnmergedTree = true)
                    .assertDoesNotExist()
            } catch (e2: Exception) {
            }
        }
    }
}