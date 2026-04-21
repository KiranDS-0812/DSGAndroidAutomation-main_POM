package com.dcsg.espresso_uitests.tests

import com.dcsg.espresso_uitests.base.BaseTest
import com.dcsg.espresso_uitests.pages.*
import com.dcsg.espresso_uitests.identifiers.OnboardingTags
import com.dcsg.espresso_uitests.identifiers.ShopTags
import androidx.compose.ui.test.*
import org.junit.Before
import org.junit.Test

class DSGProductCartFlowTest : BaseTest() {

    private lateinit var onboardingPage: OnboardingPage
    private lateinit var shopPage: ShopPage
    private lateinit var searchPage: SearchPage
    private lateinit var dsgProductListPage: DSGProductListPage
    private lateinit var productPage: ProductPage
    private lateinit var dsgProductCartFlowPage: DSGProductCartFlowPage

    @Before
    fun setup() {
        onboardingPage = OnboardingPage(composeTestRule)
        shopPage = ShopPage(composeTestRule)
        searchPage = SearchPage(composeTestRule)
        dsgProductListPage = DSGProductListPage(composeTestRule)
        productPage = ProductPage(composeTestRule)
        dsgProductCartFlowPage = DSGProductCartFlowPage(composeTestRule)
    }

    /**
     * Scenario: DSG Product Cart Flow
     * Flow: Onboarding -> Search -> PLP -> Quick View (via Add to Cart on card) -> Add to Cart -> View Cart
     */
    @Test
    fun verifyProductCartFlowTillPayment() {
        // Step 1-7: Onboarding
        performCommonOnboardingSteps(allowLocation = true, allowNotification = true)

        // Step 8: Home Screen
        shopPage.waitForFullHomePageLoad()
        shopPage.verifyShopPageVisible()

        // Step 9: Search for product
        shopPage.clickSearchBar()
        searchPage.enterSearchText("Carhartt Adult Buffalo Hat")

        // Step 10: Tap View All
        try {
            searchPage.clickViewAll()
        } catch (e: Throwable) {
            searchPage.selectFirstSuggestion()
        }

        // Step 11: Product Listing Screen
        dsgProductListPage.waitForPageLoad()

        // Step 12: Tap "Add to Cart" on the product card in PLP to open Quick View modal
        dsgProductListPage.clickAddToCartOnPLP()

        // Step 13: Add all attribute values if needed
        dsgProductCartFlowPage.selectAllAttributes()

        // Step 14: Tap "ADD TO CART" button inside the Quick View modal
        dsgProductCartFlowPage.clickAddToCartInQuickView()
        dsgProductCartFlowPage.clickAddToCartInQuickView()

        // Step 15: Navigate to Cart (Payment Page)
        productPage.clickViewCart()
    }

    /**
     * Scenario: 005 Verify the user is able to see the title 'CART' with the Cart ID,
     * if user taps on 'X' icon in the popup modal.
     */
    @Test
    fun verifyScenario005CartTitleAndId() {
        // Step 1-7: Onboarding
        performCommonOnboardingSteps(allowLocation = true, allowNotification = true)

        // Step 8: Home Screen
        shopPage.waitForFullHomePageLoad()
        shopPage.verifyShopPageVisible()

        // Step 9: Search for product
        shopPage.clickSearchBar()
        searchPage.enterSearchText("Carhartt Adult Buffalo Hat")

        // Step 10: Tap View All
        try {
            searchPage.clickViewAll()
        } catch (e: Throwable) {
            searchPage.selectFirstSuggestion()
        }

        // Step 11: Product Listing Screen
        dsgProductListPage.waitForPageLoad()
        Thread.sleep(3000)
        // Step 12: Navigate to PDP screen on tapping any product in PLP
        dsgProductListPage.selectFirstProduct()

        // Step 13: Add all attribute values
        //dsgProductCartFlowPage.selectAllAttributes()
        // Step 14: Tap "ADD TO CART" button on PDP screen
        dsgProductCartFlowPage.clickAddToCartOnPDP()

        // Step 15: Tap on 'X' icon in the popup modal
        dsgProductCartFlowPage.clickCloseIconInModal()

        // Step 16: Navigate to Cart screen via the Cart Icon/Tab
        dsgProductCartFlowPage.clickCartIcon()

        // Step 17: Verify the title 'CART' with the Cart ID (Order #)
        dsgProductCartFlowPage.verifyCartTitleAndId()

        // Step 18: Verify product details in the cart
        dsgProductCartFlowPage.verifyProductInCart()
    }
// This function will do the common onboarding
    private fun performCommonOnboardingSteps(allowLocation: Boolean = false, allowNotification: Boolean = false) {
        composeTestRule.waitUntil(45000) {
            try {
                composeTestRule.onAllNodesWithTag(OnboardingTags.GUEST, useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty() ||
                composeTestRule.onAllNodesWithTag(ShopTags.SEARCH_BAR, useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()
            } catch (e: Exception) {
                false
            }
        }

        val isOnboardingVisible = try {
            composeTestRule.onAllNodesWithTag(OnboardingTags.GUEST, useUnmergedTree = true)
                .fetchSemanticsNodes().isNotEmpty()
        } catch (e: Exception) {
            false
        }

        if (isOnboardingVisible) {
            onboardingPage.clickContinueAsGuest()
            onboardingPage.handleLocationScreen(allow = allowLocation)
            onboardingPage.handleNotificationScreen(allow = allowNotification)
        }
    }
}
