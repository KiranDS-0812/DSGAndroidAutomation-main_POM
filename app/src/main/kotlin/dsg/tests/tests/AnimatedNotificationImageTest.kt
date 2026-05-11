package dsg.tests.tests

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import dsg.tests.pages.AnimatedNotificationImagePage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AnimatedNotificationImageTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    private val notificationPage = AnimatedNotificationImagePage()

    @Test
    fun testNotificationVisibleAndClickable() {
        notificationPage.assertNotificationVisible()
        notificationPage.clickNotificationContent()
        // Add assertion for expected behavior after click if applicable
    }
}