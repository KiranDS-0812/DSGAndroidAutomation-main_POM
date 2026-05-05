package dsg.tests.pages

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.ComposeTestRule
import dsg.tests.identifiers.HomeScreenTestTags

class HomeScreenPage(private val composeTestRule: ComposeTestRule) {

    private fun waitForNode(tag: String, timeout: Long = 10_000) {
        composeTestRule.waitUntil(timeout) {
            try {
                composeTestRule.onAllNodesWithTag(tag, useUnmergedTree = true)
                    .fetchSemanticsNodes().isNotEmpty()
            } catch (e: Throwable) { false }
        }
    }

    fun verifyLandingScreenVisible(): HomeScreenPage {
        waitForNode(HomeScreenTestTags.LANDING_SCREEN)
        composeTestRule.onNodeWithTag(HomeScreenTestTags.LANDING_SCREEN)
            .assertIsDisplayed()
        return this
    }

    fun tapContinueAsGuest(): HomeScreenPage {
        waitForNode(HomeScreenTestTags.CONTINUE_AS_GUEST_BUTTON)
        composeTestRule.onNodeWithTag(HomeScreenTestTags.CONTINUE_AS_GUEST_BUTTON)
            .performClick()
        return this
    }

    fun tapSignInOnLanding(): HomeScreenPage {
        waitForNode(HomeScreenTestTags.SIGN_IN_BUTTON)
        composeTestRule.onNodeWithTag(HomeScreenTestTags.SIGN_IN_BUTTON)
            .performClick()
        return this
    }

    fun verifyLocationServicesScreen(): HomeScreenPage {
        waitForNode(HomeScreenTestTags.LOCATION_SERVICES_SCREEN)
        composeTestRule.onNodeWithTag(HomeScreenTestTags.LOCATION_SERVICES_SCREEN)
            .assertIsDisplayed()
        return this
    }

    fun tapLocationNextAndAllow(): HomeScreenPage {
        waitForNode(HomeScreenTestTags.LOCATION_NEXT_BUTTON)
        composeTestRule.onNodeWithTag(HomeScreenTestTags.LOCATION_NEXT_BUTTON)
            .performClick()
        waitForNode(HomeScreenTestTags.ALLOW_LOCATION_BUTTON)
        composeTestRule.onNodeWithTag(HomeScreenTestTags.ALLOW_LOCATION_BUTTON)
            .performClick()
        return this
    }

    fun verifyNotificationServicesScreen(): HomeScreenPage {
        waitForNode(HomeScreenTestTags.NOTIFICATION_SERVICES_SCREEN)
        composeTestRule.onNodeWithTag(HomeScreenTestTags.NOTIFICATION_SERVICES_SCREEN)
            .assertIsDisplayed()
        return this
    }

    fun tapNotificationNext(): HomeScreenPage {
        waitForNode(HomeScreenTestTags.NOTIFICATION_NEXT_BUTTON)
        composeTestRule.onNodeWithTag(HomeScreenTestTags.NOTIFICATION_NEXT_BUTTON)
            .performClick()
        return this
    }

    fun verifySignInScreen(): HomeScreenPage {
        waitForNode(HomeScreenTestTags.SIGN_IN_SCREEN)
        composeTestRule.onNodeWithTag(HomeScreenTestTags.SIGN_IN_SCREEN)
            .assertIsDisplayed()
        return this
    }

    fun enterUsername(username: String): HomeScreenPage {
        waitForNode(HomeScreenTestTags.USERNAME_FIELD)
        composeTestRule.onNodeWithTag(HomeScreenTestTags.USERNAME_FIELD, useUnmergedTree = true)
            .performTextInput(username)
        return this
    }

    fun enterPassword(password: String): HomeScreenPage {
        waitForNode(HomeScreenTestTags.PASSWORD_FIELD)
        composeTestRule.onNodeWithTag(HomeScreenTestTags.PASSWORD_FIELD, useUnmergedTree = true)
            .performTextInput(password)
        return this
    }

    fun tapSubmitSignIn(): HomeScreenPage {
        waitForNode(HomeScreenTestTags.SUBMIT_SIGN_IN_BUTTON)
        composeTestRule.onNodeWithTag(HomeScreenTestTags.SUBMIT_SIGN_IN_BUTTON)
            .performClick()
        return this
    }

    fun verifyHomeScreen(): HomeScreenPage {
        waitForNode(HomeScreenTestTags.HOME_SCREEN)
        composeTestRule.onNodeWithTag(HomeScreenTestTags.HOME_SCREEN)
            .assertIsDisplayed()
        return this
    }

    fun verifySignedInHomeScreen(): HomeScreenPage {
        waitForNode(HomeScreenTestTags.SIGNED_IN_HOME_SCREEN)
        composeTestRule.onNodeWithTag(HomeScreenTestTags.SIGNED_IN_HOME_SCREEN)
            .assertIsDisplayed()
        return this
    }

    fun verifyFullStoryHomeRComponent(): HomeScreenPage {
        waitForNode(HomeScreenTestTags.FULLSTORY_HOMER_COMPONENT)
        composeTestRule.onNodeWithTag(HomeScreenTestTags.FULLSTORY_HOMER_COMPONENT)
            .assertIsDisplayed()
        return this
    }

    fun tapFullStoryHomeRButton(index: Int): HomeScreenPage {
        val tag = HomeScreenTestTags.FULLSTORY_HOMER_BUTTON_PREFIX + index
        waitForNode(tag)
        composeTestRule.onNodeWithTag(tag)
            .performClick()
        waitForNode(HomeScreenTestTags.PRODUCT_DETAIL_PAGE)
        composeTestRule.onNodeWithTag(HomeScreenTestTags.PRODUCT_DETAIL_PAGE)
            .assertIsDisplayed()
        return this
    }

    fun enterSearchKeyword(keyword: String): HomeScreenPage {
        waitForNode(HomeScreenTestTags.SEARCH_BAR)
        composeTestRule.onNodeWithTag(HomeScreenTestTags.SEARCH_BAR)
            .performClick()
        waitForNode(HomeScreenTestTags.SEARCH_INPUT)
        composeTestRule.onNodeWithTag(HomeScreenTestTags.SEARCH_INPUT, useUnmergedTree = true)
            .performTextInput(keyword)
        return this
    }

    fun tapViewAll(): HomeScreenPage {
        waitForNode(HomeScreenTestTags.VIEW_ALL_BUTTON)
        composeTestRule.onNodeWithTag(HomeScreenTestTags.VIEW_ALL_BUTTON)
            .performClick()
        return this
    }

    fun tapBarcodeScanner(): HomeScreenPage {
        waitForNode(HomeScreenTestTags.BARCODE_SCANNER_BUTTON)
        composeTestRule.onNodeWithTag(HomeScreenTestTags.BARCODE_SCANNER_BUTTON)
            .performClick()
        return this
    }

    fun verifySneakerReleasesCarousel(): HomeScreenPage {
        waitForNode(HomeScreenTestTags.SNEAKER_RELEASES_CAROUSEL)
        composeTestRule.onNodeWithTag(HomeScreenTestTags.SNEAKER_RELEASES_CAROUSEL)
            .assertIsDisplayed()
        return this
    }

    fun tapSneakerCarouselItem(index: Int): HomeScreenPage {
        val tag = HomeScreenTestTags.PRODUCT_CARD_PREFIX + index
        waitForNode(tag)
        composeTestRule.onNodeWithTag(tag)
            .performClick()
        waitForNode(HomeScreenTestTags.PRODUCT_DETAIL_PAGE)
        composeTestRule.onNodeWithTag(HomeScreenTestTags.PRODUCT_DETAIL_PAGE)
            .assertIsDisplayed()
        return this
    }

    fun verifyDropsCarousel(): HomeScreenPage {
        waitForNode(HomeScreenTestTags.DROPS_CAROUSEL)
        composeTestRule.onNodeWithTag(HomeScreenTestTags.DROPS_CAROUSEL)
            .assertIsDisplayed()
        return this
    }

    fun verifyLatestLaunchLineUpCarousel(): HomeScreenPage {
        waitForNode(HomeScreenTestTags.LATEST_LAUNCH_LINEUP_CAROUSEL)
        composeTestRule.onNodeWithTag(HomeScreenTestTags.LATEST_LAUNCH_LINEUP_CAROUSEL)
            .assertIsDisplayed()
        return this
    }

    fun tapFooterShop(): HomeScreenPage {
        waitForNode(HomeScreenTestTags.FOOTER_SHOP_BUTTON)
        composeTestRule.onNodeWithTag(HomeScreenTestTags.FOOTER_SHOP_BUTTON)
            .performClick()
        return this
    }

    fun tapFooterCart(): HomeScreenPage {
        waitForNode(HomeScreenTestTags.FOOTER_CART_BUTTON)
        composeTestRule.onNodeWithTag(HomeScreenTestTags.FOOTER_CART_BUTTON)
            .performClick()
        return this
    }

    fun tapFooterAccount(): HomeScreenPage {
        waitForNode(HomeScreenTestTags.FOOTER_ACCOUNT_BUTTON)
        composeTestRule.onNodeWithTag(HomeScreenTestTags.FOOTER_ACCOUNT_BUTTON)
            .performClick()
        return this
    }

    fun tapFooterMoveTracker(): HomeScreenPage {
        waitForNode(HomeScreenTestTags.FOOTER_MOVE_TRACKER_BUTTON)
        composeTestRule.onNodeWithTag(HomeScreenTestTags.FOOTER_MOVE_TRACKER_BUTTON)
            .performClick()
        return this
    }

    fun tapFooterAddShortcut(): HomeScreenPage {
        waitForNode(HomeScreenTestTags.FOOTER_ADD_SHORTCUT_BUTTON)
        composeTestRule.onNodeWithTag(HomeScreenTestTags.FOOTER_ADD_SHORTCUT_BUTTON)
            .performClick()
        return this
    }

    fun tapFooterSneakerReleases(): HomeScreenPage {
        waitForNode(HomeScreenTestTags.FOOTER_SNEAKER_RELEASES_BUTTON)
        composeTestRule.onNodeWithTag(HomeScreenTestTags.FOOTER_SNEAKER_RELEASES_BUTTON)
            .performClick()
        return this
    }

    fun tapFooterFavorite(): HomeScreenPage {
        waitForNode(HomeScreenTestTags.FOOTER_FAVORITE_BUTTON)
        composeTestRule.onNodeWithTag(HomeScreenTestTags.FOOTER_FAVORITE_BUTTON)
            .performClick()
        return this
    }

    fun verifyManageShortcutsScreen(): HomeScreenPage {
        waitForNode(HomeScreenTestTags.MANAGE_SHORTCUTS_SCREEN)
        composeTestRule.onNodeWithTag(HomeScreenTestTags.MANAGE_SHORTCUTS_SCREEN)
            .assertIsDisplayed()
        return this
    }

    fun toggleMoveOption(): HomeScreenPage {
        waitForNode(HomeScreenTestTags.TOGGLE_MOVE_OPTION)
        composeTestRule.onNodeWithTag(HomeScreenTestTags.TOGGLE_MOVE_OPTION)
            .performClick()
        return this
    }

    fun toggleSneakerOption(): HomeScreenPage {
        waitForNode(HomeScreenTestTags.TOGGLE_SNEAKER_OPTION)
        composeTestRule.onNodeWithTag(HomeScreenTestTags.TOGGLE_SNEAKER_OPTION)
            .performClick()
        return this
    }

    fun toggleFavoriteOption(): HomeScreenPage {
        waitForNode(HomeScreenTestTags.TOGGLE_FAVORITE_OPTION)
        composeTestRule.onNodeWithTag(HomeScreenTestTags.TOGGLE_FAVORITE_OPTION)
            .performClick()
        return this
    }

    fun verifyProductListingScreen(): HomeScreenPage {
        waitForNode(HomeScreenTestTags.PRODUCT_LISTING_SCREEN)
        composeTestRule.onNodeWithTag(HomeScreenTestTags.PRODUCT_LISTING_SCREEN)
            .assertIsDisplayed()
        return this
    }

    fun tapProductCard(index: Int): HomeScreenPage {
        val tag = HomeScreenTestTags.PRODUCT_CARD_PREFIX + index
        waitForNode(tag)
        composeTestRule.onNodeWithTag(tag)
            .performClick()
        return this
    }

    fun verifyProductDetailPage(): HomeScreenPage {
        waitForNode(HomeScreenTestTags.PRODUCT_DETAIL_PAGE)
        composeTestRule.onNodeWithTag(HomeScreenTestTags.PRODUCT_DETAIL_PAGE)
            .assertIsDisplayed()
        return this
    }

    fun verifyAccountScreen(): HomeScreenPage {
        waitForNode(HomeScreenTestTags.ACCOUNT_SCREEN)
        composeTestRule.onNodeWithTag(HomeScreenTestTags.ACCOUNT_SCREEN)
            .assertIsDisplayed()
        return this
    }

    fun verifyCartScreen(): HomeScreenPage {
        waitForNode(HomeScreenTestTags.CART_SCREEN)
        composeTestRule.onNodeWithTag(HomeScreenTestTags.CART_SCREEN)
            .assertIsDisplayed()
        return this
    }

    fun verifyMoveTrackerScreen(): HomeScreenPage {
        waitForNode(HomeScreenTestTags.MOVE_TRACKER_SCREEN)
        composeTestRule.onNodeWithTag(HomeScreenTestTags.MOVE_TRACKER_SCREEN)
            .assertIsDisplayed()
        return this
    }

    fun verifyHeader(): HomeScreenPage {
        waitForNode(HomeScreenTestTags.HEADER)
        composeTestRule.onNodeWithTag(HomeScreenTestTags.HEADER)
            .assertIsDisplayed()
        return this
    }

    fun verifyFooter(): HomeScreenPage {
        waitForNode(HomeScreenTestTags.FOOTER)
        composeTestRule.onNodeWithTag(HomeScreenTestTags.FOOTER)
            .assertIsDisplayed()
        return this
    }

    fun verifyHeaderCollapsed(): HomeScreenPage {
        waitForNode(HomeScreenTestTags.HEADER)
        composeTestRule.onNodeWithTag(HomeScreenTestTags.HEADER)
            .assertIsNotEnabled()
        return this
    }

    fun scrollDown(): HomeScreenPage {
        composeTestRule.onRoot().performScrollTo()
        return this
    }

    fun scrollUp(): HomeScreenPage {
        composeTestRule.onRoot().performScrollTo()
        return this
    }

    fun waitFor(seconds: Int): HomeScreenPage {
        Thread.sleep(seconds * 1000L)
        return this
    }

    fun tapVersionApp(): HomeScreenPage {
        waitForNode(HomeScreenTestTags.VERSION_APP)
        composeTestRule.onNodeWithTag(HomeScreenTestTags.VERSION_APP)
            .performClick()
        return this
    }

    fun selectFutureDateInDatePicker(): HomeScreenPage {
        waitForNode(HomeScreenTestTags.DATE_PICKER)
        composeTestRule.onNodeWithTag(HomeScreenTestTags.DATE_PICKER)
            .performClick()
        return this
    }

    fun verifyPreviewMessage(): HomeScreenPage {
        waitForNode(HomeScreenTestTags.PREVIEW_MESSAGE)
        composeTestRule.onNodeWithTag(HomeScreenTestTags.PREVIEW_MESSAGE)
            .assertIsDisplayed()
        return this
    }
}
