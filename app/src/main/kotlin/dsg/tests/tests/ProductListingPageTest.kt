package dsg.tests.tests

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import dsg.tests.pages.ProductListingPage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ProductListingPageTest {

    private val plp = ProductListingPage()

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testValidateProductCardDetails() {
        plp.tapContinueAsGuest()
        plp.tapSkipLocation()
        plp.tapSkipNotification()
        plp.enterSearchKeyword("shoes")
        plp.tapViewAll()
        plp.validateProductCardDetails(0)
    }

    @Test
    fun testSwitchProductColorSwatches() {
        plp.tapContinueAsGuest()
        plp.tapSkipLocation()
        plp.tapSkipNotification()
        plp.enterSearchKeyword("jacket")
        plp.tapViewAll()
        plp.switchColorSwatch(0, 1)
    }

    @Test
    fun testAddRemoveFromWishlist() {
        plp.tapContinueAsGuest()
        plp.tapSkipLocation()
        plp.tapSkipNotification()
        plp.enterSearchKeyword("backpack")
        plp.tapViewAll()
        plp.tapFavorite(0)
        plp.validateSignInModal()
        // Assume sign-in handled in another method
        plp.tapFavorite(0) // Remove from wishlist
        plp.navigateToWishlist()
        // Validate removal (not shown here)
    }

    @Test
    fun testValidatePromotionalMessage() {
        plp.tapContinueAsGuest()
        plp.tapSkipLocation()
        plp.tapSkipNotification()
        plp.enterSearchKeyword("tent")
        plp.tapViewAll()
        plp.validatePromotionalMessage(0)
    }

    @Test
    fun testNavigateFromPLPToPDP() {
        plp.tapContinueAsGuest()
        plp.tapSkipLocation()
        plp.tapSkipNotification()
        plp.enterSearchKeyword("bicycle")
        plp.tapViewAll()
        plp.tapProductCard(0)
        // Validate navigation (not shown)
    }

    @Test
    fun testAddToCartViaQuickView() {
        plp.tapContinueAsGuest()
        plp.tapSkipLocation()
        plp.tapSkipNotification()
        plp.enterSearchKeyword("socks")
        plp.tapViewAll()
        plp.tapAddToCart(0)
        plp.validateQuickViewDisplayed()
        plp.selectAttributeInQuickView(R.id.size_selector, "M")
        plp.tapAddToCartInQuickView()
        plp.navigateToCart()
        // Validate cart (not shown)
    }

    @Test
    fun testProductComparisonFunctionality() {
        plp.tapContinueAsGuest()
        plp.tapSkipLocation()
        plp.tapSkipNotification()
        plp.enterSearchKeyword("golf club")
        plp.tapViewAll()
        plp.tapCompareCheckbox(0)
        plp.tapCompareCheckbox(1)
        plp.validateComparisonContainerCount(2)
        plp.tapCompareButton()
        // Validate comparison screen (not shown)
    }

    @Test
    fun testNavigationViaSuggestions() {
        plp.tapContinueAsGuest()
        plp.tapSkipLocation()
        plp.tapSkipNotification()
        plp.enterSearchKeyword("nike")
        plp.tapCategorySuggestion("Men's Shoes")
        // Validate navigation (not shown)
        plp.tapBrandSuggestion("Nike")
        // Validate navigation (not shown)
        plp.tapKeywordSuggestion("Running Shoes")
        // Validate navigation (not shown)
    }
}