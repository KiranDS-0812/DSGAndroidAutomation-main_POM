package com.dcsg.espresso_uitests.tests

import com.dcsg.espresso_uitests.base.BaseTest
import com.dcsg.espresso_uitests.pages.*
import org.junit.Before
import org.junit.Test

class ShopTest : BaseTest() {

    private lateinit var onboardingPage: OnboardingPage
    private lateinit var shopPage: ShopPage
    private lateinit var searchPage: SearchPage
    private lateinit var productListPage: ProductListPage

    @Before
    fun setup() {
        onboardingPage = OnboardingPage(composeTestRule)
        shopPage = ShopPage(composeTestRule)
        searchPage = SearchPage(composeTestRule)
        productListPage = ProductListPage(composeTestRule)
    }

    @Test
    fun testSearchAndDisplayResults() {
        // 1. Navigate through Onboarding as Guest
        onboardingPage.clickContinueAsGuest()
        onboardingPage.handleLocationScreen(allow = false)
        onboardingPage.handleNotificationScreen(allow = false)

        // 2. Wait for Shop Page to be fully loaded with data
        shopPage.waitForFullHomePageLoad()
        shopPage.verifyShopPageVisible()
        
        // Brief pause to visually see the home page content
        Thread.sleep(3000)

        // 3. Search for an item (e.g., 'Rain gear')
        shopPage.clickSearchBar()
        searchPage.enterSearchText("Shoes")
        
        // 4. Select a suggestion to show results
        searchPage.selectSuggestionByText("Shoes")

        // 5. Wait for and Verify Search Results
        productListPage.waitForPageLoad()
        productListPage.verifyProductListIsDisplayed()
        
        // Keep the app open for few seconds to visually see the product list
        Thread.sleep(5000)
    }
}
