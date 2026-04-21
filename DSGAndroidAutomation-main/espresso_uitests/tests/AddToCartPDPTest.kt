package com.dcsg.espresso_uitests.tests

import com.dcsg.espresso_uitests.base.BaseTest
import com.dcsg.espresso_uitests.pages.*
import org.junit.Before
import org.junit.Test

class AddToCartPDPTest : BaseTest() {

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
    fun testSearchAndAddFirstItemToCartViaPDP() {
        // 1. Onboarding Flow
        onboardingPage.clickContinueAsGuest()
        onboardingPage.handleLocationScreen(allow = false)
        onboardingPage.handleNotificationScreen(allow = false)

        // 2. Home Page: Wait for search bar
        shopPage.waitForFullHomePageLoad()
        shopPage.clickSearchBar()

        searchPage.enterSearchText("Carhartt Adult Buffalo Hat")
        searchPage.selectFirstSuggestion()

        // This navigates to the full Product Detail Page (PDP) instead of Quick View
        productListPage.selectFirstProduct()

        // We use selectFirstAvailableSize which is now very robust
       // productPage.selectFirstAvailableSize()
        
        // 6. PDP: Click Add to Cart
        productPage.clickAddToCart()

        // 7. View Cart from the success popover or header
        productPage.clickViewCart()

        // 8. Verify and Cleanup
        cartPage.verifyItemInCart()
    }
}
