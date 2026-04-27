package dsg.tests.pages

import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.*

class ShopPage(private val composeRule: ComposeTestRule) {

    fun waitForFullHomePageLoad() {
        try {
            composeRule.waitUntil(timeoutMillis = 15000) {
                composeRule.onAllNodesWithText("Shop", useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()
            }
        } catch (e: Exception) {
            Thread.sleep(5000)
        }
    }

    fun clickSearchBar() {
        try {
            composeRule.waitUntil(timeoutMillis = 8000) {
                composeRule.onAllNodesWithText("Search", useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()
            }
            composeRule.onNodeWithText("Search", useUnmergedTree = true)
                .assertIsDisplayed()
                .performClick()
        } catch (e: Exception) {
            throw AssertionError("Failed to click search bar: ${e.message}")
        }
    }
}