package dsg.tests.pages

import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.*

class OnboardingPage(private val composeRule: ComposeTestRule) {

    fun clickContinueAsGuest() {
        try {
            composeRule.waitUntil(timeoutMillis = 8000) {
                composeRule.onAllNodesWithTag("onboardingContinueButton", useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()
            }
            composeRule.onNodeWithTag("onboardingContinueButton", useUnmergedTree = true)
                .assertIsDisplayed()
                .performClick()
        } catch (e: Exception) {
            throw AssertionError("Failed to click Continue as Guest: ${e.message}")
        }
    }

    fun handleLocationScreen(allow: Boolean) {
        try {
            composeRule.waitUntil(timeoutMillis = 8000) {
                composeRule.onAllNodesWithTag("onboardingNotNowButton", useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()
            }
            if (allow) {
                composeRule.onNodeWithTag("onboardingContinueButton", useUnmergedTree = true)
                    .assertIsDisplayed()
                    .performClick()
            } else {
                composeRule.onNodeWithTag("onboardingNotNowButton", useUnmergedTree = true)
                    .assertIsDisplayed()
                    .performClick()
            }
        } catch (e: Exception) {
            throw AssertionError("Failed to handle location screen: ${e.message}")
        }
    }

    fun handleNotificationScreen(allow: Boolean) {
        try {
            composeRule.waitUntil(timeoutMillis = 8000) {
                composeRule.onAllNodesWithTag("onboardingNotNowButton", useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()
            }
            if (allow) {
                composeRule.onNodeWithTag("onboardingContinueButton", useUnmergedTree = true)
                    .assertIsDisplayed()
                    .performClick()
            } else {
                composeRule.onNodeWithTag("onboardingNotNowButton", useUnmergedTree = true)
                    .assertIsDisplayed()
                    .performClick()
            }
        } catch (e: Exception) {
            throw AssertionError("Failed to handle notification screen: ${e.message}")
        }
    }
}