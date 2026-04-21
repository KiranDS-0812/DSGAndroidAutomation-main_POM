package com.dcsg.espresso_uitests.tests

import com.dcsg.espresso_uitests.base.BaseTest
import com.dcsg.espresso_uitests.pages.*
import org.junit.Before
import org.junit.Test

class AddToCartTest : BaseTest() {

    private lateinit var onboardingPage: OnboardingPage
    private lateinit var shopPage: ShopPage
    private lateinit var searchPage: SearchPage
    private lateinit var productListPage: ProductListPage
    private lateinit var productPage: ProductPage
    private lateinit var cartPage: CartPage

    @Before
    fun setup() {
        onboardingPage = OnboardingPage(composeTestRule)
        shopPage = ShopPage(composeTestRule)
        searchPage = SearchPage(composeTestRule)
        productListPage = ProductListPage(composeTestRule)
        productPage = ProductPage(composeTestRule)
        cartPage = CartPage(composeTestRule)
    }

    @Test
    fun testSearchAndAddFirstItemToCart() {
        // 1. Onboarding Flow
        // We use a robust onboarding handling to ensure we reach the Home Page
        onboardingPage.clickContinueAsGuest()
        onboardingPage.handleLocationScreen(allow = false)
        onboardingPage.handleNotificationScreen(allow = false)

        // 2. Home Page: Wait for the search bar to be interactive
        shopPage.waitForFullHomePageLoad()
        shopPage.clickSearchBar()

        // 3. Search Page: Search for a common item
        searchPage.enterSearchText("Carhartt Adult Buffalo Hat")
        searchPage.selectFirstSuggestion()

        // 4. Product List Page: Click the "Add to Cart" shortcut on the first item
        // This usually opens a "Quick View" or "Attribute Picker" bottom sheet
        productListPage.addFirstProductToCart()

        //productPage.selectSizeIfPresent("S")
        
        // 6. Add to Cart: Click the final confirmation button in the bottom sheet
        productPage.clickAddToCart()

        // 7. View Cart: Navigate to the cart page from the success confirmation
        productPage.clickViewCart()

        // 8. Verify and Cleanup: Ensure the item is present and then remove it
        cartPage.verifyItemInCart()

    }
}
