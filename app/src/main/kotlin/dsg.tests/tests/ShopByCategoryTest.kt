package dsg.tests.tests

import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import dsg.tests.base.BaseTest
import dsg.tests.pages.OnboardingPage
import dsg.tests.pages.ShopByCategoryPage
import dsg.tests.pages.SearchPage
import dsg.tests.pages.ProductListPage
import dsg.tests.identifiers.ShopByCategoryTags
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import android.util.Log

/**
 * UI Tests for Shop By Category feature.
 * Follows enterprise compliance, BDD scenario steps, and framework conventions.
 */
@RunWith(AndroidJUnit4::class)
class ShopByCategoryTest : BaseTest() {

    private lateinit var onboardingPage: OnboardingPage
    private lateinit var shopByCategoryPage: ShopByCategoryPage
    private lateinit var searchPage: SearchPage
    private lateinit var productListPage: ProductListPage

    private val logTag = "ShopByCategoryTest"

    @Before
    fun setUp() {
        onboardingPage = OnboardingPage(composeTestRule)
        shopByCategoryPage = ShopByCategoryPage(composeTestRule)
        searchPage = SearchPage(composeTestRule)
        productListPage = ProductListPage(composeTestRule)
    }

    /** 
     * Scenario 1: Validate taxo navigation - Women
     * BDD Steps:
     * - Launch DSG Application
     * - Continue as guest
     * - Handle location and notification services
     * - Navigate to Shop By Category
     * - Tap on Women category
     * - Validate Women screen
     * - Tap on Shop All Women's
     * - Navigate back through category hierarchy
     */
    @Test
    fun testValidateTaxoNavigationWomen() {
        Log.i(logTag, "START testValidateTaxoNavigationWomen")
        try {
            // Onboarding flow
            onboardingPage
                .clickContinueAsGuest()
                .handleLocationScreen(allow = false)
                .handleNotificationScreen(allow = false)

            // Navigate to Shop By Category
            shopByCategoryPage
                .verifyShopByCategoryHeader()
                .clickShopByCategoryHeader()
                .verifyCategoryVisible(ShopByCategoryTags.CATEGORY_WOMEN)
                .clickCategory(ShopByCategoryTags.CATEGORY_WOMEN)
                .verifySubCategoryVisible(ShopByCategoryTags.SHOP_ALL_WOMENS)
                .clickSubCategory(ShopByCategoryTags.SHOP_ALL_WOMENS)

            // Verify product list page loaded
            productListPage.waitForPageLoad()
            productListPage.verifyProductListIsDisplayed()

            // Navigate back through hierarchy
            shopByCategoryPage
                .clickNavigationBack()
                .verifySubCategoryVisible(ShopByCategoryTags.SHOP_ALL_WOMENS)
                .clickNavigationBack()
                .verifyCategoryVisible(ShopByCategoryTags.CATEGORY_WOMEN)

            Log.i(logTag, "END testValidateTaxoNavigationWomen - PASSED")
        } catch (e: Throwable) {
            Log.e(logTag, "FAILED testValidateTaxoNavigationWomen", e)
            throw AssertionError("Test failed: testValidateTaxoNavigationWomen - ${e.message}", e)
        }
    }

    /** 
     * Scenario 2: Validate taxo navigation - Sports to Baseball Bats (Deep Navigation)
     * BDD Steps:
     * - Launch DSG Application
     * - Continue as guest
     * - Handle location and notification services
     * - Navigate through: Sports > Baseball > Bats > BBCOR Bats
     * - Navigate back through entire hierarchy
     */
    @Test
    fun testValidateTaxoNavigationSportsToBaseballBats() {
        Log.i(logTag, "START testValidateTaxoNavigationSportsToBaseballBats")
        try {
            // Onboarding flow
            onboardingPage
                .clickContinueAsGuest()
                .handleLocationScreen(allow = false)
                .handleNotificationScreen(allow = false)

            // Deep navigation: Sports > Baseball > Bats > BBCOR Bats
            shopByCategoryPage
                .verifyShopByCategoryHeader()
                .clickShopByCategoryHeader()
                .verifyCategoryVisible(ShopByCategoryTags.CATEGORY_SPORTS)
                .clickCategory(ShopByCategoryTags.CATEGORY_SPORTS)
                .verifySubCategoryVisible(ShopByCategoryTags.BASEBALL_CATEGORY)
                .clickSubCategory(ShopByCategoryTags.BASEBALL_CATEGORY)
                .verifySubCategoryVisible(ShopByCategoryTags.BATS_CATEGORY)
                .clickSubCategory(ShopByCategoryTags.BATS_CATEGORY)
                .verifySubCategoryVisible(ShopByCategoryTags.BBCOR_BATS_CATEGORY)
                .clickSubCategory(ShopByCategoryTags.BBCOR_BATS_CATEGORY)

            // Verify product list
            productListPage.waitForPageLoad()
            productListPage.verifyProductListIsDisplayed()

            // Navigate back through entire hierarchy
            shopByCategoryPage
                .clickNavigationBack()
                .verifySubCategoryVisible(ShopByCategoryTags.BATS_CATEGORY)
                .clickNavigationBack()
                .verifySubCategoryVisible(ShopByCategoryTags.BASEBALL_CATEGORY)
                .clickNavigationBack()
                .verifyCategoryVisible(ShopByCategoryTags.CATEGORY_SPORTS)
                .clickNavigationBack()
                .verifyShopByCategoryHeader()

            Log.i(logTag, "END testValidateTaxoNavigationSportsToBaseballBats - PASSED")
        } catch (e: Throwable) {
            Log.e(logTag, "FAILED testValidateTaxoNavigationSportsToBaseballBats", e)
            throw AssertionError("Test failed: testValidateTaxoNavigationSportsToBaseballBats - ${e.message}", e)
        }
    }

    /** 
     * Scenario 3: Validate taxo navigation - Search with keyword 'Asics'
     * BDD Steps:
     * - Launch DSG Application
     * - Navigate to home as guest
     * - Open search and search for 'Asics'
     * - Select search suggestion
     * - Verify search results
     * - Navigate back and verify recent search
     */
    @Test
    fun testValidateTaxoNavigationSearch() {
        Log.i(logTag, "START testValidateTaxoNavigationSearch")
        try {
            // Onboarding flow
            onboardingPage
                .clickContinueAsGuest()
                .handleLocationScreen(allow = false)
                .handleNotificationScreen(allow = false)

            // Open search and enter keyword (input validated)
            searchPage
                .clickSearchBar()
                .enterSearchText("Asics")
                .selectSuggestionByText("Asics")

            // Verify product list
            productListPage.waitForPageLoad()
            productListPage.verifyProductListIsDisplayed()

            // Navigate back to home
            shopByCategoryPage.clickNavigationBack()

            // Open Shop By Category and verify recent search
            shopByCategoryPage
                .clickShopByCategoryHeader()
                .verifyShopByCategoryHeader()

            // Open search again and verify recent search appears
            searchPage
                .clickSearchBar()
                .verifyRecentSearchVisible("Asics")
                .selectRecentSearch("Asics")

            // Verify product list again
            productListPage.waitForPageLoad()
            productListPage.verifyProductListIsDisplayed()

            Log.i(logTag, "END testValidateTaxoNavigationSearch - PASSED")
        } catch (e: Throwable) {
            Log.e(logTag, "FAILED testValidateTaxoNavigationSearch", e)
            throw AssertionError("Test failed: testValidateTaxoNavigationSearch - ${e.message}", e)
        }
    }

    /** 
     * Scenario 4: Validate Shop By Category Scroller
     * BDD Steps:
     * - Launch DSG Application
     * - Navigate to home as guest
     * - Verify multiple categories are visible
     * - Scroll category scroller to the right
     * - Verify additional categories become visible
     */
    @Test
    fun testValidateShopByCategoryScroller() {
        Log.i(logTag, "START testValidateShopByCategoryScroller")
        try {
            // Onboarding flow
            onboardingPage
                .clickContinueAsGuest()
                .handleLocationScreen(allow = false)
                .handleNotificationScreen(allow = false)

            // Navigate to Shop By Category
            shopByCategoryPage
                .verifyShopByCategoryHeader()
                .clickShopByCategoryHeader()

            // Verify initial categories visible
            shopByCategoryPage
                .verifyCategoryVisible(ShopByCategoryTags.CATEGORY_SPORTS)
                .clickCategory(ShopByCategoryTags.CATEGORY_SPORTS)
                .clickNavigationBack()
                .verifyCategoryVisible(ShopByCategoryTags.CATEGORY_MEN)
                .clickCategory(ShopByCategoryTags.CATEGORY_MEN)
                .clickNavigationBack()
                .verifyCategoryVisible(ShopByCategoryTags.CATEGORY_WOMEN)
                .clickCategory(ShopByCategoryTags.CATEGORY_WOMEN)
                .clickNavigationBack()

            // Scroll category scroller to the right
            shopByCategoryPage
                .scrollCategoryRight()
                .verifyCategoryVisible(ShopByCategoryTags.CATEGORY_OUTDOOR)
                .clickCategory(ShopByCategoryTags.CATEGORY_OUTDOOR)
                .clickNavigationBack()
                .verifyShopByCategoryHeader()

            Log.i(logTag, "END testValidateShopByCategoryScroller - PASSED")
        } catch (e: Throwable) {
            Log.e(logTag, "FAILED testValidateShopByCategoryScroller", e)
            throw AssertionError("Test failed: testValidateShopByCategoryScroller - ${e.message}", e)
        }
    }
}