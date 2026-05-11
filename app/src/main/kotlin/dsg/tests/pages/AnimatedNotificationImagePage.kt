package dsg.tests.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import com.dcsg.test.audit.AuditLogger
import com.dcsg.test.util.RetryHandler
import org.hamcrest.Matchers.allOf

/**
 * PageObject for AnimatedNotificationImage composable.
 * Enterprise-grade: Secure, maintainable, with audit logging and error handling.
 */
class AnimatedNotificationImagePage {

    // Selectors (using testTag and contentDescription where available)
    private val notificationBackground = onView(
        allOf(
            withTagValue(`is`("ob_notification_background")),
            isDisplayed()
        )
    )
    private val notificationIcon = onView(
        allOf(
            withTagValue(`is`("ob_notification_icon")),
            isDisplayed()
        )
    )
    private val notificationContent = onView(
        allOf(
            withTagValue(`is`("ob_notification_content")),
            isDisplayed()
        )
    )

    init {
        AuditLogger.log("AnimatedNotificationImagePage initialized")
    }

    fun assertNotificationVisible() {
        RetryHandler.retry {
            notificationBackground.check(matches(isDisplayed()))
            notificationIcon.check(matches(isDisplayed()))
            notificationContent.check(matches(isDisplayed()))
        }
        AuditLogger.log("Notification visibility asserted")
    }

    fun clickNotificationContent() {
        RetryHandler.retry {
            notificationContent.perform(click())
        }
        AuditLogger.log("Notification content clicked")
    }
}