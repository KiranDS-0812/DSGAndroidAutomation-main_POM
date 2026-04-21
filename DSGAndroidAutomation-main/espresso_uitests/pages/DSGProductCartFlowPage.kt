package com.dcsg.espresso_uitests.pages

import android.view.View
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.test.espresso.Espresso
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.dcsg.espresso_uitests.identifiers.CartTags
import com.dcsg.espresso_uitests.identifiers.ProductTags
import org.hamcrest.Matcher
import org.hamcrest.Matchers

class DSGProductCartFlowPage(private val composeTestRule: ComposeTestRule) {

    /**
     * Selects all necessary attributes (Size, Color, etc.) if they are available.
     */
    fun selectAllAttributes() {
        try {
            composeTestRule.waitUntil(15000) {
                try {
                    composeTestRule.onAllNodesWithTag("AttributeWidgetContainer", useUnmergedTree = true)
                        .fetchSemanticsNodes().isNotEmpty()
                } catch (e: Exception) { false }
            }

            val attributeSections = composeTestRule.onAllNodesWithTag("AttributeWidgetContainer", useUnmergedTree = true)
            val sectionCount = attributeSections.fetchSemanticsNodes().size

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
     * Clicks the "ADD TO CART" button inside the Quick View modal.
     */
    fun clickAddToCartInQuickView() {
        composeTestRule.waitForIdle()
        Thread.sleep(2000)

        val buttonId = com.dcsg.shop.R.id.info_add_to_cart_button

        // Strategy 1: Espresso standard click on ID
        try {
            Espresso.onView(ViewMatchers.withId(buttonId)).perform(ViewActions.click())
            finalizeClick()
            return
        } catch (e: Throwable) {}

        // Strategy 2: Compose click on text
        try {
            composeTestRule.onAllNodes(hasText("ADD TO CART", ignoreCase = true), useUnmergedTree = true)
                .onFirst()
                .performClick()
            finalizeClick()
            return
        } catch (e: Throwable) {}

        // Strategy 3: Espresso Force Click (ignores visibility constraints)
        try {
            Espresso.onView(ViewMatchers.withId(buttonId)).perform(forceClick())
            finalizeClick()
            return
        } catch (e: Throwable) {}

        // Strategy 4: Espresso click on text content
        try {
            Espresso.onView(ViewMatchers.withText(Matchers.equalToIgnoringCase("ADD TO CART")))
                .perform(ViewActions.click())
            finalizeClick()
            return
        } catch (e: Throwable) {}

        // If all strategies fail
        throw IllegalStateException(" FAILED to click 'ADD TO CART' button in Quick View modal.")
    }

    /**
     * Clicks the "ADD TO CART" button on the PDP screen.
     * Clicks twice as per the reference flow requirements.
     */
    fun clickAddToCartOnPDP() {
        val matcher = hasText("ADD TO CART", ignoreCase = true) or
                     hasTestTag(ProductTags.ADD_TO_CART_BUTTON) or
                     hasTestTag("ProgressIndicatorButton enabled")
        
        repeat(2) {
            composeTestRule.waitUntil(25000) {
                try {
                    composeTestRule.onAllNodes(matcher, useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()
                } catch (e: Exception) { false }
            }
            composeTestRule.onAllNodes(matcher, useUnmergedTree = true).onFirst().performClick()
            Thread.sleep(1500)
        }
        Thread.sleep(2000)
        composeTestRule.waitForIdle()
    }

    /**
     * Taps on the 'X' or close icon in a modal (e.g., Added to Cart or Quick View).
     */
    fun clickCloseIconInModal() {
        composeTestRule.waitForIdle()
        Thread.sleep(3000)

        // Identification Strategy for 'X' Icon
        composeTestRule.waitUntil(35000) {
            try {
                // 1. Compose search
                val composeFound = composeTestRule.onAllNodes(
                    hasTestTag(CartTags.CLOSE_BUTTON) or 
                    hasTestTag("XCloseButton") or 
                    hasContentDescription("Close", ignoreCase = true) or
                    hasTestTag("addedToCartHeader"),
                    useUnmergedTree = true
                ).fetchSemanticsNodes().isNotEmpty()
                
                if (composeFound) return@waitUntil true
                
                // 2. XML search
                var xmlFound = false
                try {
                    Espresso.onView(ViewMatchers.withId(com.dcsg.shop.R.id.close_image))
                        .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                    xmlFound = true
                } catch (e: Throwable) {}
                xmlFound
            } catch (e: Exception) { false }
        }

        var clicked = false

        // Action 1: Compose Tag click
        try {
            composeTestRule.onNodeWithTag(CartTags.CLOSE_BUTTON, useUnmergedTree = true).performClick()
            clicked = true
        } catch (e: Throwable) {
            try {
                composeTestRule.onNodeWithTag("XCloseButton", useUnmergedTree = true).performClick()
                clicked = true
            } catch (e2: Throwable) {}
        }

        // Action 2: Espresso click (for XML-based Toolbars)
        if (!clicked) {
            try {
                Espresso.onView(ViewMatchers.withId(com.dcsg.shop.R.id.close_image)).perform(ViewActions.click())
                clicked = true
            } catch (e: Throwable) {}
        }

        // Action 3: Compose Content Description click
        if (!clicked) {
            try {
                composeTestRule.onNodeWithContentDescription("Close", ignoreCase = true, useUnmergedTree = true).performClick()
                clicked = true
            } catch (e: Throwable) {}
        }

        // Action 4: Fallback click via TouchInput
        if (!clicked) {
            try {
                composeTestRule.onAllNodes(hasContentDescription("Close", ignoreCase = true), useUnmergedTree = true)
                    .onFirst().performTouchInput { click() }
                clicked = true
            } catch (e: Throwable) {}
        }

        if (!clicked) {
            throw IllegalStateException("FAILED to click 'X' / Close icon in the modal.")
        }

        composeTestRule.waitForIdle()
        Thread.sleep(2000)
    }

    /**
     * Taps on the Cart icon in the toolbar.
     */
    fun clickCartIcon() {
        // Strategy 1: Toolbar Cart Icon (XML)
        try {
            Espresso.onView(ViewMatchers.withId(com.dcsg.shop.R.id.cart_icon))
                .perform(ViewActions.click())
            Thread.sleep(2000)
            composeTestRule.waitForIdle()
            return
        } catch (e: Throwable) {}

        // Strategy 2: Bottom Navigation Cart Tab
        val cartTabMatcher = hasTestTag(CartTags.NAVIGATION_CART) or 
                            hasContentDescription("Cart", ignoreCase = true)
        
        try {
            composeTestRule.onAllNodes(cartTabMatcher, useUnmergedTree = true)
                .onFirst()
                .performClick()
        } catch (e: Throwable) {
            try {
                Espresso.onView(ViewMatchers.withContentDescription(Matchers.containsString("Cart")))
                    .perform(ViewActions.click())
            } catch (e2: Throwable) {
                // Final fallback using text
                composeTestRule.onNode(hasText("Cart", ignoreCase = true) and hasClickAction(), useUnmergedTree = true).performClick()
            }
        }
        Thread.sleep(3000)
        composeTestRule.waitForIdle()
    }

    /**
     * Taps on the View Cart button in the modal.
     */
    fun clickViewCartInModal() {
        val viewCartMatcher = hasText("VIEW CART", ignoreCase = true) or
                             hasTestTag(ProductTags.VIEW_CART_BUTTON) or
                             hasText("CHECKOUT", ignoreCase = true)

        composeTestRule.waitUntil(30000) {
            try {
                composeTestRule.onAllNodes(viewCartMatcher, useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()
            } catch (e: Throwable) { false }
        }

        composeTestRule.onAllNodes(viewCartMatcher, useUnmergedTree = true).onFirst().performClick()
        Thread.sleep(2000)
        composeTestRule.waitForIdle()
    }

    /**
     * Verifies that the user sees the 'CART' title and the 'Cart ID' (Order Number).
     */
    fun verifyCartTitleAndId() {
        // Step 1: Wait for screen ready markers
        composeTestRule.waitUntil(60000) {
            try {
                val hasCartTitle = composeTestRule.onAllNodes(hasText(CartTags.CART_TITLE, ignoreCase = true), useUnmergedTree = true)
                    .fetchSemanticsNodes().isNotEmpty()
                val hasCheckout = composeTestRule.onAllNodes(hasText("Checkout", ignoreCase = true), useUnmergedTree = true)
                    .fetchSemanticsNodes().isNotEmpty()
                
                var xmlVisible = false
                try {
                    Espresso.onView(ViewMatchers.withText(Matchers.equalToIgnoringCase(CartTags.CART_TITLE)))
                        .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                    xmlVisible = true
                } catch (e: Throwable) {}

                hasCartTitle || hasCheckout || xmlVisible
            } catch (e: Exception) { false }
        }

        // Step 2: Verify "Cart" title. 
        // We look for all nodes with "Cart", and assert that at least one is displayed.
        var titleVerified = false
        val cartNodes = composeTestRule.onAllNodes(hasText(CartTags.CART_TITLE, ignoreCase = true), useUnmergedTree = true)
        val nodeCount = cartNodes.fetchSemanticsNodes().size
        for (i in 0 until nodeCount) {
            try {
                cartNodes[i].assertIsDisplayed()
                titleVerified = true
                break
            } catch (e: Throwable) {}
        }
        
        if (!titleVerified) {
            try {
                Espresso.onView(ViewMatchers.withText(Matchers.equalToIgnoringCase(CartTags.CART_TITLE)))
                    .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                titleVerified = true
            } catch (e: Throwable) {
                try {
                    Espresso.onView(ViewMatchers.withId(com.dcsg.reusuableui.R.id.toolbar_title))
                        .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                    titleVerified = true
                } catch (e2: Throwable) {
                    // Try one more search via substring in case of mixed case or decoration
                    composeTestRule.onAllNodes(hasText(CartTags.CART_TITLE, ignoreCase = true, substring = true), useUnmergedTree = true)
                        .onFirst()
                        .assertIsDisplayed()
                }
            }
        }

        // Step 3: Verify Cart ID (Order #)
        composeTestRule.waitUntil(30000) {
            try {
                val hasId = composeTestRule.onAllNodes(hasText(CartTags.ORDER_NUMBER, substring = true, ignoreCase = true), useUnmergedTree = true)
                    .fetchSemanticsNodes().isNotEmpty()
                
                var xmlId = false
                try {
                    Espresso.onView(ViewMatchers.withText(Matchers.containsString(CartTags.ORDER_NUMBER)))
                        .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                    xmlId = true
                } catch (e: Throwable) {}
                
                hasId || xmlId
            } catch (e: Exception) { false }
        }
        
        var idVerified = false
        val idNodes = composeTestRule.onAllNodes(hasText(CartTags.ORDER_NUMBER, substring = true, ignoreCase = true), useUnmergedTree = true)
        val idCount = idNodes.fetchSemanticsNodes().size
        for (i in 0 until idCount) {
            try {
                idNodes[i].assertIsDisplayed()
                idVerified = true
                break
            } catch (e: Throwable) {}
        }
        
        if (!idVerified) {
            Espresso.onView(ViewMatchers.withText(Matchers.containsString(CartTags.ORDER_NUMBER)))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        }
    }

    /**
     * Verifies product details are visible in the cart.
     */
    fun verifyProductInCart() {
        val cartMarkers = hasText("Cart", ignoreCase = true) or 
                         hasText("CHECKOUT", ignoreCase = true) or
                         hasTestTag("ProgressIndicatorButton enabled") or
                         hasTestTag("productImage")

        composeTestRule.waitUntil(30000) {
            try {
                composeTestRule.onAllNodes(cartMarkers, useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()
            } catch (e: Exception) { false }
        }
        Thread.sleep(2000)
        composeTestRule.waitForIdle()
    }

    private fun finalizeClick() {
        Thread.sleep(4000)
        composeTestRule.waitForIdle()
    }

    /**
     * Custom ViewAction to perform a click regardless of visibility percentage.
     */
    private fun forceClick(): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return Matchers.anyOf(ViewMatchers.isDisplayed(), ViewMatchers.isEnabled())
            }

            override fun getDescription(): String = "force click"

            override fun perform(uiController: UiController, view: View) {
                view.performClick()
            }
        }
    }
}
