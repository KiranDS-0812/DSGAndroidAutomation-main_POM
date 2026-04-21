package com.dcsg.espresso_uitests.pages

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.ComposeTestRule
import com.dcsg.espresso_uitests.identifiers.ProductTags

class ProductListPage(private val composeTestRule: ComposeTestRule) {

    /**
     * Waits for the product list grid to be present in the UI hierarchy.
     */
    fun waitForPageLoad(timeoutMillis: Long = 30000): ProductListPage {
        composeTestRule.waitUntil(timeoutMillis) {
            try {
                composeTestRule.onAllNodesWithTag("productList", useUnmergedTree = true)
                    .fetchSemanticsNodes().isNotEmpty()
            } catch (e: Exception) { false }
        }
        return this
    }

    /**
     * Verifies that the product list is displayed.
     */
    fun verifyProductListIsDisplayed() {
        composeTestRule.onAllNodesWithTag("productList", useUnmergedTree = true)
            .onFirst()
            .assertIsDisplayed()
    }

    /**
     * Clicks on the first product's image to navigate to the full PDP.
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

    fun addFirstProductToCart() {
        composeTestRule.waitUntil(20000) {
            try {
                composeTestRule.onAllNodesWithTag(ProductTags.ADD_TO_CART_BUTTON, useUnmergedTree = true)
                    .fetchSemanticsNodes().isNotEmpty()
            } catch (e: Exception) { false }
        }
        composeTestRule.onAllNodesWithTag(ProductTags.ADD_TO_CART_BUTTON, useUnmergedTree = true)
            .onFirst()
            .performClick()
    }
}
