package com.dcsg.espresso_uitests.base

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.dcsg.dickssportinggoods.HomeActivity
import org.junit.Rule

abstract class BaseTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<HomeActivity>()
}
