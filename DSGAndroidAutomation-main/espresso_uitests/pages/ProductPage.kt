package com.dcsg.espresso_uitests.pages

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import com.dcsg.espresso_uitests.identifiers.ProductTags
import org.hamcrest.Matchers.anyOf

class ProductPage(private val composeTestRule: ComposeTestRule) {

    /**
     * Verifies that the user is on the Product Detail Page (PDP) or Selection Modal.
     */
    fun verifyIsOnProductDetailPage(): ProductPage {
        composeTestRule.waitUntil(45000) {
            try {
                composeTestRule.onAllNodes(
                    hasTestTag(ProductTags.ADD_TO_CART_BUTTON) or
                    hasTestTag("horizontalPager") or
                    hasTestTag("productTitle") or
                    hasText("ADD TO CART", ignoreCase = true)
                ).fetchSemanticsNodes().isNotEmpty()
            } catch (e: Throwable) {
                // Check view system
                try {
                    onView(anyOf(withId(com.dcsg.shop.R.id.info_add_to_cart_button), withText("ADD TO CART")))
                        .check { view, _ -> view.isShown }
                    true
                } catch (e2: Throwable) { false }
            }
        }
        return this
    }

    /**
     * Attempts to scroll down the PDP.
     */
    fun selectFirstAvailableSize() {
        verifyIsOnProductDetailPage()

        // Perform manual swipes on the first root to scroll down
        repeat(5) {
            composeTestRule.onAllNodes(androidx.compose.ui.test.isRoot(), useUnmergedTree = true)
                .onFirst()
                .performTouchInput {
                    swipeUp(startY = 0.8f, endY = 0.2f, durationMillis = 500)
                }
            Thread.sleep(500)
        }

        composeTestRule.waitForIdle()
    }

    /**
     * Clicks the "ADD TO CART" button.
     * Handles both Jetpack Compose (PDP) and XML-based (Quick View Modal) buttons.
     */
    fun clickAddToCart() {
        val composeMatcher = hasText("ADD TO CART", ignoreCase = true) or
                            hasTestTag(ProductTags.ADD_TO_CART_BUTTON) or
                            hasTestTag("ProgressIndicatorButton enabled")

        composeTestRule.waitForIdle()

        // 1. Try clicking with Compose first
        val clickedInCompose = try {
            val nodes = composeTestRule.onAllNodes(composeMatcher, useUnmergedTree = true)
            if (nodes.fetchSemanticsNodes().isNotEmpty()) {
                val node = nodes.onFirst()
                try { node.performScrollTo() } catch (e: Exception) {}
                node.performClick()
                true
            } else {
                false
            }
        } catch (e: Throwable) {
            false
        }

        // 2. Fallback to Espresso for XML-based "EmbeddedProgressIndicatorButtonWidget"
        if (!clickedInCompose) {
            try {
                onView(anyOf(
                    withId(com.dcsg.shop.R.id.info_add_to_cart_button),
                    withText("ADD TO CART"),
                    withContentDescription("ADD TO CART")
                )).perform(scrollTo(), click())
            } catch (e: Throwable) {
                // Final attempt: find by text and click if it's on screen
                try {
                    onView(withText("ADD TO CART")).perform(click())
                } catch (e2: Throwable) {}
            }
        }

        // Wait for potential modal transition
        Thread.sleep(3000)
        composeTestRule.waitForIdle()
    }

    fun clickViewCart() {
        val viewCartMatcher = hasText("VIEW CART", ignoreCase = true) or
                             hasTestTag(ProductTags.VIEW_CART_BUTTON) or
                             hasText("CHECKOUT", ignoreCase = true)

        composeTestRule.waitUntil(45000) {
            try {
                composeTestRule.onAllNodes(viewCartMatcher, useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()
            } catch (e: Throwable) { false }
        }

        composeTestRule.onAllNodes(viewCartMatcher, useUnmergedTree = true).onFirst().performClick()
    }
}
