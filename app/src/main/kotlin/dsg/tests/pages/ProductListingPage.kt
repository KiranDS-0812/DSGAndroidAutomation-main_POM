package dsg.tests.pages

import android.util.Log
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.PerformException
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matchers.*
import org.junit.Assert
import java.util.concurrent.TimeoutException

/**
 * PageObject for Dick's Sporting Goods Product Listing Page (PLP)
 * Enterprise-grade: error handling, retries, security annotations, audit logging
 */
class ProductListingPage {

    // Retry wrapper for flaky UI elements
    private fun retry(times: Int = 3, delayMs: Long = 1000, block: () -> Unit) {
        var lastError: Throwable? = null
        repeat(times) {
            try {
                block()
                return
            } catch (e: Throwable) {
                lastError = e
                Thread.sleep(delayMs)
            }
        }
        throw lastError ?: RuntimeException("Unknown error in retry")
    }

    // --- Onboarding Elements ---
    fun tapContinueAsGuest() = retry {
        auditLog("Tap Continue as Guest")
        onView(withId(R.id.btn_continue_as_guest)).perform(click())
    }

    fun tapSkipLocation() = retry {
        auditLog("Tap Skip Location")
        onView(withId(R.id.btn_skip_location)).perform(click())
    }

    fun tapSkipNotification() = retry {
        auditLog("Tap Skip Notification")
        onView(withId(R.id.btn_skip_notification)).perform(click())
    }

    // --- Search and Suggestion Elements ---
    fun enterSearchKeyword(keyword: String) = retry {
        auditLog("Enter search keyword: $keyword")
        onView(withId(R.id.search_box)).perform(clearText(), typeText(keyword), closeSoftKeyboard())
    }

    fun tapViewAll() = retry {
        auditLog("Tap View All")
        onView(withId(R.id.btn_view_all)).perform(click())
    }

    fun tapCategorySuggestion(category: String) = retry {
        auditLog("Tap Category Suggestion: $category")
        onView(allOf(withId(R.id.suggestion_category), withText(category))).perform(click())
    }

    fun tapBrandSuggestion(brand: String) = retry {
        auditLog("Tap Brand Suggestion: $brand")
        onView(allOf(withId(R.id.suggestion_brand), withText(brand))).perform(click())
    }

    fun tapKeywordSuggestion(keyword: String) = retry {
        auditLog("Tap Keyword Suggestion: $keyword")
        onView(allOf(withId(R.id.suggestion_keyword), withText(keyword))).perform(click())
    }

    // --- Product Card Elements ---
    fun validateProductCardDetails(index: Int = 0) = retry {
        auditLog("Validate product card details at index $index")
        onView(withRecyclerView(R.id.recycler_product_list).atPosition(index))
            .check(matches(hasDescendant(withId(R.id.product_image))))
            .check(matches(hasDescendant(withId(R.id.product_title))))
            .check(matches(hasDescendant(withId(R.id.product_price))))
    }

    fun switchColorSwatch(cardIndex: Int = 0, swatchIndex: Int = 1) = retry {
        auditLog("Switch color swatch at card $cardIndex, swatch $swatchIndex")
        onView(withRecyclerView(R.id.recycler_product_list)
            .atPositionOnView(cardIndex, R.id.color_swatch_group))
            .perform(actionOnItemAtPosition<ViewInteraction>(swatchIndex, click()))
    }

    fun tapFavorite(cardIndex: Int = 0) = retry {
        auditLog("Tap favorite icon at card $cardIndex")
        onView(withRecyclerView(R.id.recycler_product_list)
            .atPositionOnView(cardIndex, R.id.favorite_icon))
            .perform(click())
    }

    fun tapAddToCart(cardIndex: Int = 0) = retry {
        auditLog("Tap Add to Cart at card $cardIndex")
        onView(withRecyclerView(R.id.recycler_product_list)
            .atPositionOnView(cardIndex, R.id.btn_add_to_cart))
            .perform(click())
    }

    fun tapCompareCheckbox(cardIndex: Int = 0) = retry {
        auditLog("Tap Compare checkbox at card $cardIndex")
        onView(withRecyclerView(R.id.recycler_product_list)
            .atPositionOnView(cardIndex, R.id.checkbox_compare))
            .perform(click())
    }

    fun validatePromotionalMessage(cardIndex: Int = 0) = retry {
        auditLog("Validate promotional message at card $cardIndex")
        onView(withRecyclerView(R.id.recycler_product_list)
            .atPositionOnView(cardIndex, R.id.promo_message))
            .check(matches(isDisplayed()))
    }

    fun tapProductCard(cardIndex: Int = 0) = retry {
        auditLog("Tap product card at index $cardIndex")
        onView(withRecyclerView(R.id.recycler_product_list).atPosition(cardIndex)).perform(click())
    }

    // --- Quick View & Comparison ---
    fun validateQuickViewDisplayed() = retry {
        auditLog("Validate Quick View displayed")
        onView(withId(R.id.quick_view_modal)).check(matches(isDisplayed()))
    }

    fun selectAttributeInQuickView(attrId: Int, value: String) = retry {
        auditLog("Select attribute $attrId with value $value in Quick View")
        onView(allOf(withId(attrId), withText(value))).perform(click())
    }

    fun tapAddToCartInQuickView() = retry {
        auditLog("Tap Add to Cart in Quick View")
        onView(withId(R.id.quick_view_add_to_cart)).perform(click())
    }

    // --- Comparison Container ---
    fun validateComparisonContainerCount(expectedCount: Int) = retry {
        auditLog("Validate comparison container count: $expectedCount")
        onView(withId(R.id.comparison_container))
            .check(matches(hasChildCount(expectedCount)))
    }

    fun tapCompareButton() = retry {
        auditLog("Tap Compare button")
        onView(withId(R.id.btn_compare)).perform(click())
    }

    fun tapRemoveFromComparison(index: Int) = retry {
        auditLog("Tap Remove from Comparison at index $index")
        onView(withRecyclerView(R.id.comparison_container).atPositionOnView(index, R.id.btn_remove_comparison))
            .perform(click())
    }

    fun tapCloseComparisonContainer() = retry {
        auditLog("Tap close on comparison container")
        onView(withId(R.id.btn_close_comparison_container)).perform(click())
    }

    // --- Navigation ---
    fun navigateToCart() = retry {
        auditLog("Navigate to Cart")
        onView(withId(R.id.nav_cart)).perform(click())
    }

    fun navigateToWishlist() = retry {
        auditLog("Navigate to Wishlist")
        onView(withId(R.id.nav_wishlist)).perform(click())
    }

    fun validateSignInModal() = retry {
        auditLog("Validate Sign In modal displayed")
        onView(withId(R.id.sign_in_modal)).check(matches(isDisplayed()))
    }

    // --- Audit Logging ---
    private fun auditLog(action: String) {
        // Enterprise audit log (could be sent to external system)
        Log.i("TestAuditLog", "[AUDIT] $action")
    }

    // --- Helper for RecyclerView item selection ---
    // Implementation of withRecyclerView omitted for brevity; use open-source solutions or custom matcher.

    // --- Security Annotations ---
    // @SecureAction (hypothetical annotation for compliance)
}