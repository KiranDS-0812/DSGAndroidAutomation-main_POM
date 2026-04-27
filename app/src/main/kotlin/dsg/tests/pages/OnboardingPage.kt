package dsg.tests.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import dsg.tests.identifiers.TestTags
import android.util.Log

/**
 * Page Object for Onboarding Screen
 * Provides methods to interact with onboarding UI elements
 */
class OnboardingPage {
    private val TAG = "OnboardingPage"
    private val MAX_RETRIES = 3
    private val RETRY_DELAY_MS = 1000L

    /**
     * Verify that the onboarding logo is displayed
     */
    fun verifyLogoDisplayed() {
        executeWithRetry {
            onView(withContentDescription(TestTags.LOGO))
                .check(matches(isDisplayed()))
            Log.d(TAG, "Logo verified successfully")
        }
    }

    /**
     * Verify that the Join Now button is displayed
     */
    fun verifyJoinNowButtonDisplayed() {
        executeWithRetry {
            onView(withContentDescription(TestTags.JOIN_NOW))
                .check(matches(isDisplayed()))
            Log.d(TAG, "Join Now button verified successfully")
        }
    }

    /**
     * Verify that the Guest button is displayed
     */
    fun verifyGuestButtonDisplayed() {
        executeWithRetry {
            onView(withContentDescription(TestTags.GUEST))
                .check(matches(isDisplayed()))
            Log.d(TAG, "Guest button verified successfully")
        }
    }

    /**
     * Click the Join Now button
     */
    fun clickJoinNow() {
        executeWithRetry {
            onView(withContentDescription(TestTags.JOIN_NOW))
                .perform(click())
            Log.d(TAG, "Join Now button clicked successfully")
        }
    }

    /**
     * Click the Guest button
     */
    fun clickGuest() {
        executeWithRetry {
            onView(withContentDescription(TestTags.GUEST))
                .perform(click())
            Log.d(TAG, "Guest button clicked successfully")
        }
    }

    /**
     * Execute action with retry logic
     */
    private fun executeWithRetry(action: () -> Unit) {
        var attempts = 0
        var lastException: Exception? = null

        while (attempts < MAX_RETRIES) {
            try {
                action()
                return
            } catch (e: Exception) {
                lastException = e
                attempts++
                Log.w(TAG, "Attempt $attempts failed: ${e.message}")
                if (attempts < MAX_RETRIES) {
                    Thread.sleep(RETRY_DELAY_MS * attempts)
                }
            }
        }

        Log.e(TAG, "All $MAX_RETRIES attempts failed")
        throw lastException ?: Exception("Unknown error occurred")
    }
}
