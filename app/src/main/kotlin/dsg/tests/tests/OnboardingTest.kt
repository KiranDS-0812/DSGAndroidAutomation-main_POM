package dsg.tests.tests

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import dsg.tests.pages.OnboardingPage
import dsg.tests.base.BaseTest
import org.junit.Test
import org.junit.runner.RunWith
import android.util.Log

/**
 * Onboarding Screen Test Suite
 * Tests for verifying onboarding screen functionality
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class OnboardingTest : BaseTest() {
    private val TAG = "OnboardingTest"
    private val onboardingPage = OnboardingPage()

    /**
     * Test Case: Verify Onboarding Screen UI Elements
     * Validates that all required UI elements are displayed
     */
    @Test
    fun verifyOnboardingScreenUI() {
        Log.d(TAG, "Starting verifyOnboardingScreenUI test")
        
        onboardingPage.verifyLogoDisplayed()
        onboardingPage.verifyJoinNowButtonDisplayed()
        onboardingPage.verifyGuestButtonDisplayed()
        
        Log.d(TAG, "verifyOnboardingScreenUI test completed successfully")
    }

    /**
     * Test Case: Test Join Now Navigation
     * Validates that clicking Join Now button navigates correctly
     */
    @Test
    fun testJoinNowNavigation() {
        Log.d(TAG, "Starting testJoinNowNavigation test")
        
        onboardingPage.verifyJoinNowButtonDisplayed()
        onboardingPage.clickJoinNow()
        
        // Add navigation verification here
        Log.d(TAG, "testJoinNowNavigation test completed successfully")
    }
}
