package dsg.tests.pages

import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.*
import androidx.compose.ui.semantics.SemanticsProperties

class SearchPage(private val composeRule: ComposeTestRule) {

    fun enterSearchText(query: String) {
        try {
            composeRule.waitUntil(timeoutMillis = 8000) {
                composeRule.onAllNodesWithText("Search", useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()
            }
            composeRule.onNodeWithText("Search", useUnmergedTree = true)
                .performTextInput(query)
        } catch (e: Exception) {
            throw AssertionError("Failed to enter search text: ${e.message}")
        }
    }

    fun selectFirstSuggestion() {
        try {
            Thread.sleep(1000)
            composeRule.onAllNodes(hasTestTag("SearchSuggestionItem"), useUnmergedTree = true)
                .onFirst()
                .performClick()
        } catch (e: Exception) {
            try {
                composeRule.onAllNodes(SemanticsMatcher.expectValue(SemanticsProperties.Text, listOf("nike")), useUnmergedTree = true)
                    .onFirst()
                    .performClick()
            } catch (e2: Exception) {
                throw AssertionError("Failed to select first suggestion: ${e2.message}")
            }
        }
    }
}