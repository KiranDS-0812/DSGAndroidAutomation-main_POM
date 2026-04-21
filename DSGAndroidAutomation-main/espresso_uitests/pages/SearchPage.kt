package com.dcsg.espresso_uitests.pages

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matchers.allOf

class SearchPage(private val composeTestRule: ComposeTestRule) {

    /**
     * Enters text into the search bar.
     */
    fun enterSearchText(text: String) {
        val inputMatcher = allOf(withId(com.dcsg.shop.R.id.search_input), isDisplayed())

        composeTestRule.waitForIdle()
        
        onView(inputMatcher)
            .perform(click(), replaceText(text))

        // Give suggestions time to appear
        composeTestRule.waitForIdle()
    }

    /**
     * Selects the first search suggestion.
     */
    fun selectFirstSuggestion() {
        composeTestRule.waitUntil(30000) {
            try {
                // Find clickable suggestion items (usually at index 2+ in the list)
                composeTestRule.onAllNodes(hasClickAction() and !hasSetTextAction(), useUnmergedTree = true)
                    .fetchSemanticsNodes().size > 2
            } catch (e: Throwable) { false }
        }

        // Click the first valid suggestion item
        composeTestRule.onAllNodes(hasClickAction() and !hasSetTextAction(), useUnmergedTree = true)[2].performClick()

        // Wait for the navigation to PLP to begin
        composeTestRule.waitForIdle()
    }

    fun selectSuggestionByText(text: String) {
        composeTestRule.waitUntil(30000) {
            try {
                composeTestRule.onAllNodesWithText(text, substring = true, ignoreCase = true)
                    .fetchSemanticsNodes().isNotEmpty()
            } catch (e: Throwable) { false }
        }
        composeTestRule.onAllNodesWithText(text, substring = true, ignoreCase = true).onFirst().performClick()
    }

    /**
     * Taps on the "VIEW ALL" button in the search suggestions.
     */
    fun clickViewAll() {
        composeTestRule.waitUntil(20000) {
            try {
                composeTestRule.onAllNodesWithText("VIEW ALL", ignoreCase = true)
                    .fetchSemanticsNodes().isNotEmpty()
            } catch (e: Throwable) { false }
        }
        composeTestRule.onAllNodesWithText("VIEW ALL", ignoreCase = true).onFirst().performClick()
        composeTestRule.waitForIdle()
    }

    /**
     * Validates that search suggestions or categories are displayed.
     */
    fun validateSuggestionsDisplayed() {
        composeTestRule.waitUntil(20000) {
            try {
                // Check for any suggestion text or the "VIEW ALL" button
                composeTestRule.onAllNodes(hasClickAction() and !hasSetTextAction(), useUnmergedTree = true)
                    .fetchSemanticsNodes().size > 2
            } catch (e: Throwable) { false }
        }
    }

    /**
     * Taps on the first brand suggestion.
     */
    fun selectFirstBrandSuggestion() {
        // Brands list is usually displayed after typing a brand name like "Nike"
        composeTestRule.waitUntil(30000) {
            try {
                composeTestRule.onAllNodesWithText("BRANDS", ignoreCase = true)
                    .fetchSemanticsNodes().isNotEmpty()
            } catch (e: Throwable) { false }
        }
        
        // Find clickable nodes that are likely brand names
        // Heuristic: brand names follow the "BRANDS" heading and are clickable
        val clickables = composeTestRule.onAllNodes(hasClickAction() and !hasSetTextAction(), useUnmergedTree = true)
        val count = clickables.fetchSemanticsNodes().size
        
        // Target the last item in the list which is typically where brands are displayed when "Nike" is entered
        if (count > 0) {
            clickables[count - 1].performClick()
        }
        
        composeTestRule.waitForIdle()
    }
}
