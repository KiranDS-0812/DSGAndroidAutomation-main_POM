package dsg.tests.base

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import org.junit.Before
import org.junit.Rule
import android.util.Log

/**
 * Base Test Class
 * Provides common setup and utilities for all test classes
 */
abstract class BaseTest {
    private val TAG = "BaseTest"

    /**
     * Activity Scenario Rule for launching the main activity
     * Note: Replace MainActivity::class.java with your actual main activity class
     */
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    /**
     * UiDevice instance for UI automation
     */
    protected lateinit var device: UiDevice

    /**
     * Setup method executed before each test
     */
    @Before
    fun setUp() {
        Log.d(TAG, "Setting up test environment")
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        Log.d(TAG, "UiDevice initialized successfully")
    }
}
