package dsg.tests.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withTagValue
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.assertion.ViewAssertions.matches
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.equalTo
import com.dcsg.onboard.R
import android.util.Log

/**
 * PageObject for the Onboarding Location Screen.
 * Enterprise security: Input validation, error handling, audit logging.
 * AuditLog: All actions are logged for traceability.
 */
class OnboardingLocationPage {

    // UI Elements mapped by testTag
    private val continueButton = onView(withTagValue(equalTo("onboardingContinueButton")))
    private val notNowButton = onView(withTagValue(equalTo("onboardingNotNowButton")))
    // Info text and icons are not directly accessible by testTag; use withText for visible text.
    // Example for the info text:
    // val infoText = onView(withText(R.string.ob_location_not_now_button))

    /**
     * Clicks the Continue button.
     * Handles errors and logs the action.
     */
    fun clickContinue() {
        try {
            continueButton.check(matches(isDisplayed()))
            continueButton.perform(click())
            Log.i("AuditLog", "Clicked Onboarding Continue Button")
        } catch (e: Exception) {
            Log.e("AuditLog", "Failed to click Continue: ${e.message}")
            throw AssertionError("Continue button interaction failed", e)
        }
    }

    /**
     * Clicks the Not Now button.
     * Handles errors and logs the action.
     */
    fun clickNotNow() {
        try {
            notNowButton.check(matches(isDisplayed()))
            notNowButton.perform(click())
            Log.i("AuditLog", "Clicked Onboarding Not Now Button")
        } catch (e: Exception) {
            Log.e("AuditLog", "Failed to click Not Now: ${e.message}")
            throw AssertionError("Not Now button interaction failed", e)
        }
    }
}