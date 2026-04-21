package com.dcsg.espresso_uitests.tests

import com.dcsg.onboard.model.OnboardingCarouselItem
import com.dcsg.onboard.ui.SignUpSignInScreen
import com.dcsg.espresso_uitests.base.BaseTest
import com.dcsg.espresso_uitests.pages.OnboardingPage
import org.junit.Before
import org.junit.Test

class OnboardingTest : BaseTest() {

    private lateinit var onboardingPage: OnboardingPage

    @Before
    fun setup() {
        onboardingPage = OnboardingPage(composeTestRule)

        composeTestRule.setContent {
            SignUpSignInScreen(
                carouselItems = getCarouselItems(),
                onEventListener = {}
            )
        }
    }

    @Test
    fun verifyOnboardingScreenUI() {
        onboardingPage.verifyOnboardingScreenVisible()
    }

    @Test
    fun testJoinNowNavigation() {
        onboardingPage.clickJoinNow()
        // Add further assertions here when navigation is implemented in POM
    }

    private fun getCarouselItems(): List<OnboardingCarouselItem> =
        listOf(
            OnboardingCarouselItem(
                title = "Welcome",
                description = "Welcome to the app"
            )
        )
}
