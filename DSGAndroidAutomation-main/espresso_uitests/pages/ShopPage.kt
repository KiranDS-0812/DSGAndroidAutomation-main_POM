package com.dcsg.espresso_uitests.pages

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.ComposeTestRule
import com.dcsg.espresso_uitests.identifiers.ShopTags

class ShopPage(private val composeTestRule: ComposeTestRule) {

    /**
     * Waits for the Shop Page to be ready and the search bar to be displayed.
     */
    fun waitForFullHomePageLoad(timeoutMillis: Long = 40000): ShopPage {
        composeTestRule.waitUntil(timeoutMillis) {
            try {
                // Wait for the search bar to be present in the hierarchy
                composeTestRule.onAllNodesWithTag(ShopTags.SEARCH_BAR, useUnmergedTree = true)
                    .fetchSemanticsNodes().isNotEmpty()
            } catch (e: Throwable) {
                false
            }
        }
        composeTestRule.waitForIdle()
        return this
    }

    fun verifyShopPageVisible(): ShopPage {
        // Wait specifically for the search bar to be displayed (visible on screen)
        composeTestRule.waitUntil(20000) {
            try {
                composeTestRule.onNodeWithTag(ShopTags.SEARCH_BAR, useUnmergedTree = true)
                    .assertIsDisplayed()
                true
            } catch (e: Throwable) {
                false
            }
        }
        return this
    }

    fun clickSearchBar() {
        // Ensure we wait for it to be ready before clicking
        composeTestRule.onNodeWithTag(ShopTags.SEARCH_BAR, useUnmergedTree = true)
            .performClick()
    }
}
