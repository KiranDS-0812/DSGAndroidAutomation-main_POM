package dsg.tests.tests

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import dsg.tests.pages.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AddToCartTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    private val onboardingPage by lazy { OnboardingPage(composeRule) }
    private val shopPage by lazy { ShopPage(composeRule) }
    private val searchPage by lazy { SearchPage(composeRule) }
    private val productListPage by lazy { ProductListPage(composeRule) }
    private val productPage by lazy { ProductPage(composeRule) }
    private val cartPage by lazy { CartPage(composeRule) }

    @Test
    fun testAddProductToCartFromSearch() {
        onboardingPage.clickContinueAsGuest()
        onboardingPage.handleLocationScreen(allow = false)
        onboardingPage.handleNotificationScreen(allow = false)
        shopPage.waitForFullHomePageLoad()
        shopPage.clickSearchBar()
        searchPage.enterSearchText("nike")
        searchPage.selectFirstSuggestion()
        productListPage.addFirstProductToCart()
        productPage.selectSizeIfPresent("M")
        productPage.clickAddToCart()
        productPage.clickViewCart()
        cartPage.verifyCartVisible()
        cartPage.verifyItemInCart()
    }
}