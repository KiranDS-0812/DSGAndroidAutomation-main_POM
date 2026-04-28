package dsg.tests.tests

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import dsg.tests.pages.DSGProductListPage
import org.junit.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DSGProductListPageTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    private lateinit var plp: DSGProductListPage

    @Before
    fun setUp() {
        plp = DSGProductListPage()
        plp.waitForPageLoad()
    }

    @Test
    fun testProductCardDetails() {
        plp.verifyProductCardDetails(0)
        plp.validateProductCardElements(0)
    }

    @Test
    fun testColorSwatchSwitching() {
        plp.tapOnColorSwatch(0, 1)
        plp.verifyProductImageUpdates(0)
    }

    @Test
    fun testWishlistFavoriteFunctionality() {
        // Add your favorite icon click and validation here
    }

    @Test
    fun testPromotionalMessageDisplayed() {
        plp.verifyPromotionalMessageDisplayed(0)
    }

    @Test
    fun testNavigateToPDPFromPLP() {
        plp.selectFirstProduct()
        plp.clickBackButton()
    }

    @Test
    fun testQuickViewAddToCart() {
        plp.clickAddToCartOnPLP(0)
        plp.verifyQuickViewDisplayed()
        plp.selectAllAttributesInQuickView()
        plp.clickAddToCartInQuickView()
    }

    @Test
    fun testProductComparisonFlow() {
        plp.validateCompareCheckboxesVisible()
        plp.selectCompareCheckboxAtIndex(0)
        plp.verifyCompareSheetWithOneProduct()
        plp.verifyCompareButtonState(enabled = false)
        plp.selectCompareCheckboxAtIndex(1)
        plp.verifyCompareButtonState(enabled = true)
        plp.clickComparePrimaryButton()
        plp.verifyComparisonScreenDisplayed()
    }

    @Test
    fun testPinnedContentCards() {
        plp.validatePinnedContentCards()
    }
}