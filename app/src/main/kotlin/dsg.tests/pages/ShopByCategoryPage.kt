package dsg.tests.pages

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.ComposeTestRule
import dsg.tests.identifiers.ShopByCategoryTags
import android.util.Log

/**
 * Page Object for the Shop By Category feature.
 * Provides enterprise-grade, maintainable, and secure UI interactions for Shop By Category flows.
 */
class ShopByCategoryPage(private val composeTestRule: ComposeTestRule) {

    private val logTag = "ShopByCategoryPage"

    /** Clicks the Shop By Category header button. */
    fun clickShopByCategoryHeader(): ShopByCategoryPage {
        try {
            Log.i(logTag, "Clicking Shop By Category header")
            composeTestRule.waitUntil(timeoutMillis = 20000) {
                composeTestRule.onAllNodesWithTag(
                    ShopByCategoryTags.SHOP_BY_CATEGORY_HEADER, useUnmergedTree = true
                ).fetchSemanticsNodes().isNotEmpty()
            }
            composeTestRule.onNodeWithTag(
                ShopByCategoryTags.SHOP_BY_CATEGORY_HEADER, useUnmergedTree = true
            ).performClick()
        } catch (e: Throwable) {
            Log.e(logTag, "Failed to click Shop By Category header", e)
            throw AssertionError("Error clicking Shop By Category header: ${e.message}", e)
        }
        return this
    }

    /** Verifies that a category is visible in the category scroller. */
    fun verifyCategoryVisible(categoryTag: String): ShopByCategoryPage {
        try {
            Log.i(logTag, "Verifying category visible: $categoryTag")
            composeTestRule.waitUntil(timeoutMillis = 20000) {
                composeTestRule.onAllNodesWithTag(categoryTag, useUnmergedTree = true)
                    .fetchSemanticsNodes().isNotEmpty()
            }
        } catch (e: Throwable) {
            Log.e(logTag, "Category not visible: $categoryTag", e)
            throw AssertionError("Category not visible: $categoryTag, ${e.message}", e)
        }
        return this
    }

    /** Clicks a top-level category by tag. */
    fun clickCategory(categoryTag: String): ShopByCategoryPage {
        try {
            Log.i(logTag, "Clicking category: $categoryTag")
            composeTestRule.waitUntil(timeoutMillis = 20000) {
                composeTestRule.onAllNodesWithTag(categoryTag, useUnmergedTree = true)
                    .fetchSemanticsNodes().isNotEmpty()
            }
            composeTestRule.onNodeWithTag(categoryTag, useUnmergedTree = true).performClick()
        } catch (e: Throwable) {
            Log.e(logTag, "Failed to click category: $categoryTag", e)
            throw AssertionError("Error clicking category: $categoryTag, ${e.message}", e)
        }
        return this
    }

    /** Clicks a sub-category by tag. */
    fun clickSubCategory(subCategoryTag: String): ShopByCategoryPage {
        try {
            Log.i(logTag, "Clicking sub-category: $subCategoryTag")
            composeTestRule.waitUntil(timeoutMillis = 20000) {
                composeTestRule.onAllNodesWithTag(subCategoryTag, useUnmergedTree = true)
                    .fetchSemanticsNodes().isNotEmpty()
            }
            composeTestRule.onNodeWithTag(subCategoryTag, useUnmergedTree = true).performClick()
        } catch (e: Throwable) {
            Log.e(logTag, "Failed to click sub-category: $subCategoryTag", e)
            throw AssertionError("Error clicking sub-category: $subCategoryTag, ${e.message}", e)
        }
        return this
    }

    /** Scrolls the category scroller horizontally to the right. */
    fun scrollCategoryRight(): ShopByCategoryPage {
        try {
            Log.i(logTag, "Scrolling category scroller right")
            composeTestRule.waitUntil(timeoutMillis = 20000) {
                composeTestRule.onAllNodesWithTag(
                    ShopByCategoryTags.CATEGORY_SCROLLER, useUnmergedTree = true
                ).fetchSemanticsNodes().isNotEmpty()
            }
            composeTestRule.onNodeWithTag(
                ShopByCategoryTags.CATEGORY_SCROLLER, useUnmergedTree = true
            ).performTouchInput { swipeLeft() }
        } catch (e: Throwable) {
            Log.e(logTag, "Failed to scroll category scroller", e)
            throw AssertionError("Error scrolling category scroller: ${e.message}", e)
        }
        return this
    }

    /** Clicks the navigation back button. */
    fun clickNavigationBack(): ShopByCategoryPage {
        try {
            Log.i(logTag, "Clicking navigation back button")
            composeTestRule.waitUntil(timeoutMillis = 20000) {
                composeTestRule.onAllNodesWithTag(
                    ShopByCategoryTags.NAVIGATION_BACK_BUTTON, useUnmergedTree = true
                ).fetchSemanticsNodes().isNotEmpty()
            }
            composeTestRule.onNodeWithTag(
                ShopByCategoryTags.NAVIGATION_BACK_BUTTON, useUnmergedTree = true
            ).performClick()
        } catch (e: Throwable) {
            Log.e(logTag, "Failed to click navigation back", e)
            throw AssertionError("Error clicking navigation back: ${e.message}", e)
        }
        return this
    }

    /** Verifies a sub-category is visible. */
    fun verifySubCategoryVisible(subCategoryTag: String): ShopByCategoryPage {
        try {
            Log.i(logTag, "Verifying sub-category visible: $subCategoryTag")
            composeTestRule.waitUntil(timeoutMillis = 20000) {
                composeTestRule.onAllNodesWithTag(subCategoryTag, useUnmergedTree = true)
                    .fetchSemanticsNodes().isNotEmpty()
            }
        } catch (e: Throwable) {
            Log.e(logTag, "Sub-category not visible: $subCategoryTag", e)
            throw AssertionError("Sub-category not visible: $subCategoryTag, ${e.message}", e)
        }
        return this
    }

    /** Verifies the Shop By Category header is visible. */
    fun verifyShopByCategoryHeader(): ShopByCategoryPage {
        try {
            Log.i(logTag, "Verifying Shop By Category header is visible")
            composeTestRule.waitUntil(timeoutMillis = 20000) {
                composeTestRule.onAllNodesWithTag(
                    ShopByCategoryTags.SHOP_BY_CATEGORY_HEADER, useUnmergedTree = true
                ).fetchSemanticsNodes().isNotEmpty()
            }
        } catch (e: Throwable) {
            Log.e(logTag, "Shop By Category header not visible", e)
            throw AssertionError("Shop By Category header not visible: ${e.message}", e)
        }
        return this
    }
}