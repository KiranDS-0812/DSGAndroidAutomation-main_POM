package com.dcsg.espresso_uitests.pages

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.ComposeTestRule
import com.dcsg.espresso_uitests.identifiers.OnboardingTags
import com.dcsg.espresso_uitests.helpers.SystemPermissionHelper

class OnboardingPage(private val composeTestRule: ComposeTestRule) {

    /**
     * Reverted to a simpler wait strategy that targets the Guest button directly,
     * as the Logo might be causing intermittent timeout issues due to animations.
     */
    fun verifyOnboardingScreenVisible(): OnboardingPage {
        composeTestRule.waitUntil(40000) {
            try {
                // Primary check for the Guest button which is the next action
                composeTestRule.onAllNodesWithTag(OnboardingTags.GUEST, useUnmergedTree = true)
                    .fetchSemanticsNodes().isNotEmpty()
            } catch (e: Exception) { false }
        }
        return this
    }

    fun clickJoinNow() {
        composeTestRule.onNodeWithTag(OnboardingTags.JOIN_NOW, useUnmergedTree = true).performClick()
    }

    fun clickSignIn() {
        composeTestRule.onNodeWithTag(OnboardingTags.SIGN_IN, useUnmergedTree = true).performClick()
    }

    fun clickContinueAsGuest() {
        composeTestRule.onNodeWithTag(OnboardingTags.GUEST, useUnmergedTree = true).performClick()
    }

    fun handleLocationScreen(allow: Boolean = false) {
        val tag = if (allow) OnboardingTags.CONTINUE else OnboardingTags.NOT_NOW
        waitForTag(tag)
        composeTestRule.onNodeWithTag(tag, useUnmergedTree = true).performClick()
        
        if (allow) {
            // Handle the system permission dialog after clicking Continue
            SystemPermissionHelper.handlePermission(true)
        }
    }

    fun handleNotificationScreen(allow: Boolean = false) {
        val tag = if (allow) OnboardingTags.CONTINUE else OnboardingTags.NOT_NOW
        waitForTag(tag)
        composeTestRule.onNodeWithTag(tag, useUnmergedTree = true).performClick()
        
        if (allow) {
            // Handle the system permission dialog after clicking Continue
            SystemPermissionHelper.handlePermission(true)
        }
    }

    private fun waitForTag(tag: String, timeout: Long = 20000) {
        composeTestRule.waitUntil(timeout) {
            try {
                composeTestRule.onAllNodesWithTag(tag, useUnmergedTree = true)
                    .fetchSemanticsNodes().isNotEmpty()
            } catch (e: Exception) { false }
        }
    }
}
