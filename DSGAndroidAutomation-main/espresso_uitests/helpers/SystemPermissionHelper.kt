package com.dcsg.espresso_uitests.helpers

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector

object SystemPermissionHelper {
    private val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    /**
     * Handles system permission dialogs by searching for "Allow" or "While using the app".
     */
    fun handlePermission(allow: Boolean = true) {
        val textMatcher = if (allow) "(?i)Allow|While using the app|Only this time" else "(?i)Don't allow|Deny"
        val button = device.findObject(UiSelector().textMatches(textMatcher))
        if (button.waitForExists(5000)) {
            button.click()
        }
    }
}
