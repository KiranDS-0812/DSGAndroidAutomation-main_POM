package com.dcsg.espresso_uitests.tests

import com.dcsg.espresso_uitests.base.BaseTest
import com.dcsg.espresso_uitests.pages.*
import com.dcsg.espresso_uitests.identifiers.OnboardingTags
import com.dcsg.espresso_uitests.identifiers.ShopTags
import androidx.compose.ui.test.*
import org.junit.Before
import org.junit.Test

/**
 * scenario num: 003, 004, 011, 012, 015, 018, 019, 020, 021, 022, 023, 024, 025, 026 & 027
 * tags: @ManualDSGSmoke
 */
class DSGProductListTest : BaseTest() {

    private lateinit var onboardingPage: OnboardingPage
    private lateinit var shopPage: ShopPage
    private lateinit var searchPage: SearchPage
    private lateinit var dsgProductListPage: DSGProductListPage
    private lateinit var productPage: ProductPage

    @Before
    fun setup() {
        onboardingPage = OnboardingPage(composeTestRule)
        shopPage = ShopPage(composeTestRule)
        searchPage = SearchPage(composeTestRule)
        dsgProductListPage = DSGProductListPage(composeTestRule)
        productPage = ProductPage(composeTestRule)
    }

    /**
     * scenario num: 003
     * test case: Validate product card details in PLP Description
     */
    @Test
    fun validateProductCardDetailsInPLPDescription() {
        performCommonOnboardingSteps()

        // Step 8: User should be navigated to home screen
        shopPage.waitForFullHomePageLoad()
        shopPage.verifyShopPageVisible()

        // Step 9: User on Product listings screen post entering keyword and suggestionToTap
        shopPage.clickSearchBar()
        searchPage.enterSearchText("Nike")
        searchPage.selectSuggestionByText("nike shoes")

        // Step 10: User waits for 3 seconds
        Thread.sleep(3000)

        // Step 11: User should be able to see the product card details
        dsgProductListPage.waitForPageLoad()
        dsgProductListPage.verifyProductCardDetails()
    }

    /**
     * scenario num: 004
     * test case: Validate product image updates based on color swatch selection
     */
    @Test
    fun validateProductImageUpdatesBasedOnColorSwatch() {
        performCommonOnboardingSteps()

        // Step 8: User should be navigated to home screen
        shopPage.waitForFullHomePageLoad()
        shopPage.verifyShopPageVisible()

        // Step 9: User on Product listing screen
        shopPage.clickSearchBar()
        searchPage.enterSearchText("Nike")
        searchPage.selectSuggestionByText("nike shoes")
        dsgProductListPage.waitForPageLoad()

        // Step 10: When user taps on color swatch
        dsgProductListPage.tapOnColorSwatch()

        // Step 11: Then product image updates based on color
        dsgProductListPage.verifyProductImageUpdates()
    }

    /**
     * scenario num: 012
     * test case: Validate navigation to PDP from PLP
     */
    @Test
    fun validateNavigationToPDP() {
        performCommonOnboardingSteps()

        // Step 8: User should be navigated to home screen
        shopPage.waitForFullHomePageLoad()
        shopPage.verifyShopPageVisible()

        // Step 9: Open PLP
        shopPage.clickSearchBar()
        searchPage.enterSearchText("Nike")
        searchPage.selectSuggestionByText("nike shoes")
        dsgProductListPage.waitForPageLoad()

        // Step 10: Wait 3 seconds
        Thread.sleep(3000)

        // Step 11: Tap any product
        dsgProductListPage.selectFirstProduct()

        // Step 12: Validate navigation to PDP
        productPage.verifyIsOnProductDetailPage()
    }

    /**
     * scenario num: 015
     * test case: Validate product card elements in PLP after Search and View All
     */
    @Test
    fun validateProductCardElementsAfterViewAll() {
        performCommonOnboardingSteps()

        // Step 8: Navigate to home
        shopPage.waitForFullHomePageLoad()
        shopPage.verifyShopPageVisible()

        // Step 9: Search product
        shopPage.clickSearchBar()
        searchPage.enterSearchText("shoes")

        // Step 10: Tap View All
        searchPage.clickViewAll()

        // Step 11: Validate product card elements
        dsgProductListPage.waitForPageLoad()
        dsgProductListPage.validateProductCardElements()
    }

    /**
     * scenario num: 018
     * test case: Validate navigation to PLP after tapping search suggestion
     */
    @Test
    fun validateNavigationToPLPFromSuggestions() {
        // Step 1-7: Onboarding (Allow permissions)
        performCommonOnboardingSteps(allowLocation = true, allowNotification = true)

        // Step 8: Navigate to home
        shopPage.waitForFullHomePageLoad()
        shopPage.verifyShopPageVisible()

        // Step 9: Search keyword
        shopPage.clickSearchBar()
        searchPage.enterSearchText("Nike")

        // Step 10: Validate suggestions
        searchPage.validateSuggestionsDisplayed()

        // Step 11: Tap suggestion/category
        searchPage.selectSuggestionByText("nike shoes")

        // Step 12: Validate navigation to PLP
        dsgProductListPage.waitForPageLoad()
        dsgProductListPage.verifyProductCardDetails()
    }

    /**
     * scenario num: 019
     * test case: Validate navigation to PLP after selecting a brand from search
     */
    @Test
    fun validateNavigationToPLPFromBrandSelection() {
        // Step 1-7: Onboarding (Allow permissions)
        performCommonOnboardingSteps(allowLocation = true, allowNotification = true)

        // Step 8: Navigate to home
        shopPage.waitForFullHomePageLoad()
        shopPage.verifyShopPageVisible()

        // Step 9: Search keyword
        shopPage.clickSearchBar()
        searchPage.enterSearchText("Nike")

        // Step 10: Validate suggestions
        searchPage.validateSuggestionsDisplayed()

        // Step 11: Tap brand
        searchPage.selectFirstBrandSuggestion()

        // Step 12: Validate navigation to PLP
        dsgProductListPage.waitForPageLoad()
        dsgProductListPage.verifyProductCardDetails()
    }

    /**
     * scenario num: 020
     * test case: To verify whether user is direct to PLP, when user selects from suggestions.
     * tags: @ManualDSGSmoke
     */
    @Test
    fun validateNavigationToPLPFromSuggestionSelection() {
        // Step 1-7: Onboarding (Allow permissions)
        performCommonOnboardingSteps(allowLocation = true, allowNotification = true)

        // Step 8: Navigate to home screen
        shopPage.waitForFullHomePageLoad()
        shopPage.verifyShopPageVisible()

        // Step 9: User enters product keyword in search box
        shopPage.clickSearchBar()
        searchPage.enterSearchText("Nike")

        // Step 10: User should be able to see Top Products, Suggestions, Brands and Category
        searchPage.validateSuggestionsDisplayed()

        searchPage.selectSuggestionByText("nike shoes")

        // Step 11: User tap on suggestionToTap under Suggestions or Categories or Brands
        searchPage.selectFirstSuggestion()

        // Step 12: User should be directed to the Product listing screen
        dsgProductListPage.waitForPageLoad()
        dsgProductListPage.verifyProductCardDetails()
    }

    /**
     * scenario num: 021
     * test case: To verify whether user is able to see the Quick view.
     */
    @Test
    fun validateQuickViewDisplayedAfterAddToCart() {
        // Step 1-7: Onboarding (Allow permissions)
        performCommonOnboardingSteps(allowLocation = true, allowNotification = true)

        // Step 8: Home Screen
        shopPage.waitForFullHomePageLoad()
        shopPage.verifyShopPageVisible()

        // Step 9: User enters product keyword in search box
        shopPage.clickSearchBar()
        searchPage.enterSearchText("Carhartt Adult Buffalo Hat")

        // Step 10: Tap on View All button
        try {
            searchPage.clickViewAll()
        } catch (e: Throwable) {
            searchPage.selectFirstSuggestion()
        }

        // Step 11: Product Listing Screen
        dsgProductListPage.waitForPageLoad()

        // Step 12: Tap "Add to Cart" on the product card in PLP to open Quick View modal
        dsgProductListPage.clickAddToCartOnPLP()

        // Step 13: Validate Quick View modal is displayed
        dsgProductListPage.verifyQuickViewDisplayed()
    }

    /**
     * scenario num: 022
     * test case: To verify whether user able to tap on Add To Cart button, to quick purchase the product from PLP
     */
    @Test
    fun validateQuickPurchaseFromPLP() {
        // Step 1-7: Onboarding (Allow permissions)
        performCommonOnboardingSteps(allowLocation = true, allowNotification = true)

        // Step 8: Home Screen
        shopPage.waitForFullHomePageLoad()
        shopPage.verifyShopPageVisible()

        // Step 9: User enters product keyword in search box
        shopPage.clickSearchBar()
        searchPage.enterSearchText("Carhartt Adult Buffalo Hat")

        // Step 10: Tap on View All button
        try {
            searchPage.clickViewAll()
        } catch (e: Throwable) {
            searchPage.selectFirstSuggestion()
        }

        // Step 11: Product Listing Screen
        dsgProductListPage.waitForPageLoad()

        // Step 12: Tap "Add to Cart" on a product card
        dsgProductListPage.clickAddToCartOnPLP()

        // Step 13: Validate Quick View (Action tray) is displayed
        dsgProductListPage.verifyQuickViewDisplayed()

        // Step 14: Add all attribute values in QuickView
        dsgProductListPage.selectAllAttributesInQuickView()

        // Step 15: Tap add to cart button in QuickView
        dsgProductListPage.clickAddToCartInQuickView()
        dsgProductListPage.clickAddToCartInQuickView()

        // Step 16: Tap on View Cart button in the modal
        productPage.clickViewCart()

        // Step 17: Verify product details in the cart screen
        CartPage(composeTestRule).verifyItemInCart()
    }

    /**
     * scenario num: 023
     * test case: Validate Initial state and compare checkbox availability in PLP of DSG application
     */
    @Test
    fun validateCompareCheckboxesInPLP() {
        // Step 1-7: Onboarding (Allow permissions)
        performCommonOnboardingSteps(allowLocation = true, allowNotification = true)

        // Step 8: User should be navigated to home screen
        shopPage.waitForFullHomePageLoad()
        shopPage.verifyShopPageVisible()

        // Step 9: User enters product keyword in search box
        shopPage.clickSearchBar()
        searchPage.enterSearchText("Nike")

        // Step 10: Tap on View All button
        searchPage.clickViewAll()

        // Step 11: User should be directed to the Product listing screen
        dsgProductListPage.waitForPageLoad()

        // Step 12: Validate product tile has Compare check boxes visible
        dsgProductListPage.validateCompareCheckboxesVisible()
    }

    /**
     * scenario num: 024
     * test case: Validate Selecting a single product for comparison in PLP of DSG application
     */
    @Test
    fun validateSingleProductSelectionForComparison() {
        // Step 1-7: Onboarding (Allow permissions)
        performCommonOnboardingSteps(allowLocation = true, allowNotification = true)

        // Step 8: User should be navigated to home screen
        shopPage.waitForFullHomePageLoad()
        shopPage.verifyShopPageVisible()

        // Step 9: User enters product keyword in search box
        shopPage.clickSearchBar()
        searchPage.enterSearchText("Nike")

        // Step 10: Tap on View All button
        searchPage.clickViewAll()

        // Step 11: User should be directed to the Product listing screen
        dsgProductListPage.waitForPageLoad()

        // Step 12: User select Compare check box for product tile at index 1
        dsgProductListPage.selectCompareCheckboxAtIndex(0)

        // Step 13: User verify Product comparison container is displayed with one product added
        dsgProductListPage.verifyCompareSheetWithOneProduct()

        // Step 14: User verify Compare product button "disabled"
        dsgProductListPage.verifyCompareButtonState("disabled")

        // Step 15: User click on Cross Sign and close compare window
        dsgProductListPage.closeCompareSheet()
    }

    /**
     * scenario num: 025
     * test case: Validate Selecting two products for comparison in PLP of DSG application
     */
    @Test
    fun validateTwoProductsSelectionForComparison() {
        // Step 1-7: Onboarding (Allow permissions)
        performCommonOnboardingSteps(allowLocation = true, allowNotification = true)

        // Step 8: User should be navigated to home screen
        shopPage.waitForFullHomePageLoad()
        shopPage.verifyShopPageVisible()

        // Step 9: User enters product keyword in search box
        shopPage.clickSearchBar()
        searchPage.enterSearchText("Nike")

        // Step 10: Tap on View All button
        searchPage.clickViewAll()

        // Step 11: User should be directed to the Product listing screen
        dsgProductListPage.waitForPageLoad()

        // Step 12: User select Compare check box for product tile at index 1
        dsgProductListPage.selectCompareCheckboxAtIndex(0)

        // Step 13: User verify Product comparison container is displayed with one product added
        dsgProductListPage.verifyCompareSheetWithOneProduct()

        // Step 14: User verify Compare product button "disabled"
        dsgProductListPage.verifyCompareButtonState("disabled")

        // Step 15: User select Compare check box for product tile at index 2
        dsgProductListPage.selectCompareCheckboxAtIndex(1)

        // Step 16: User verify Compare product button "enabled"
        dsgProductListPage.verifyCompareButtonState("enabled")
        // Step 17: User click on Cross Sign and close compare window
        dsgProductListPage.closeCompareSheet()
    }

    /**
     * scenario num: 026
     * test case: Validate removing a single product from the comparison container of DSG application
     */
    @Test
    fun validateRemovingSingleProductFromComparison() {
        // Step 1-7: Onboarding (Allow permissions)
        performCommonOnboardingSteps(allowLocation = true, allowNotification = true)

        // Step 8: User should be navigated to home screen
        shopPage.waitForFullHomePageLoad()
        shopPage.verifyShopPageVisible()

        // Step 9: User enters product keyword in search box
        shopPage.clickSearchBar()
        searchPage.enterSearchText("Nike")

        // Step 10: Tap on View All button
        searchPage.clickViewAll()

        // Step 11: User should be directed to the Product listing screen
        dsgProductListPage.waitForPageLoad()

        // Step 12: User select Compare check box for product tile at index 1
        dsgProductListPage.selectCompareCheckboxAtIndex(0)

        // Step 13: User verify Product comparison container is displayed with one product added
        dsgProductListPage.verifyCompareSheetWithOneProduct()

        // Step 14: User verify Compare product button "disabled"
        dsgProductListPage.verifyCompareButtonState("disabled")

        // Step 15: User select Compare check box for product tile at index 2
        dsgProductListPage.selectCompareCheckboxAtIndex(1)

        // Step 16: User verify Compare product button "enabled"
        dsgProductListPage.verifyCompareButtonState("enabled")

        // Step 17: User unselect 1 product from the comparison container
        dsgProductListPage.selectCompareCheckboxAtIndex(1)

        // Step 18: User verify Product comparison container is displayed with one product added
        dsgProductListPage.verifyCompareSheetWithOneProduct()

        // Step 19: User verify Compare product button "disabled"
        dsgProductListPage.verifyCompareButtonState("disabled")
    }

    /**
     * scenario num: 027
     * test case: Validate Removing the both product from the comparison container of DSG application
     */
    @Test
    fun validateRemovingBothProductsFromComparison() {
        // Step 1-7: Onboarding (Allow permissions)
        performCommonOnboardingSteps(allowLocation = true, allowNotification = true)

        // Step 8: User should be navigated to home screen
        shopPage.waitForFullHomePageLoad()
        shopPage.verifyShopPageVisible()

        // Step 9: User enters product keyword in search box
        shopPage.clickSearchBar()
        searchPage.enterSearchText("Nike")

        // Step 10: Tap on View All button
        searchPage.clickViewAll()

        // Step 11: User should be directed to the Product listing screen
        dsgProductListPage.waitForPageLoad()

        // Step 12: User select Compare check box for product tile at index 1
        dsgProductListPage.selectCompareCheckboxAtIndex(0)

        // Step 13: User verify Product comparison container is displayed with one product added
        dsgProductListPage.verifyCompareSheetWithOneProduct()

        // Step 14: User select Compare check box for product tile at index 2
        dsgProductListPage.selectCompareCheckboxAtIndex(1)

        // Step 15: User verify Compare product button "enabled"
        dsgProductListPage.verifyCompareButtonState("enabled")

        // Step 16: User tap on close button to remove "2nd" product from comparison container
        dsgProductListPage.removeProductFromCompareAtIndex(1)

        // Step 17: User tap on close button to remove "1st" product from comparison container
        dsgProductListPage.removeProductFromCompareAtIndex(0)

    }


    private fun performCommonOnboardingSteps(allowLocation: Boolean = false, allowNotification: Boolean = false) {
        // Wait for either Onboarding screen or Home screen to appear
        composeTestRule.waitUntil(45000) {
            try {
                composeTestRule.onAllNodesWithTag(OnboardingTags.GUEST, useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty() ||
                composeTestRule.onAllNodesWithTag(ShopTags.SEARCH_BAR, useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()
            } catch (e: Exception) {
                false
            }
        }

        // Check if Onboarding is visible
        val isOnboardingVisible = try {
            composeTestRule.onAllNodesWithTag(OnboardingTags.GUEST, useUnmergedTree = true)
                .fetchSemanticsNodes().isNotEmpty()
        } catch (e: Exception) {
            false
        }

        if (isOnboardingVisible) {
            // User tap on continue as guest button
            onboardingPage.clickContinueAsGuest()

            // Handle location services screen
            onboardingPage.handleLocationScreen(allow = allowLocation)

            // Handle notification services screen
            onboardingPage.handleNotificationScreen(allow = allowNotification)
        }
    }
}
