package dsg.tests.tests

import dsg.tests.pages.HomeScreenPage
import dsg.tests.base.BaseTest
import org.junit.Before
import org.junit.Test

class HomeScreenTest : BaseTest() {

    private lateinit var homeScreenPage: HomeScreenPage

    private val validUsername = System.getenv("DSG_TEST_USERNAME") ?: "testuser"
    private val validPassword = System.getenv("DSG_TEST_PASSWORD") ?: "testpass"

    @Before
    fun setUp() {
        homeScreenPage = HomeScreenPage(composeTestRule)
    }

    @Test
    fun verifyUserShouldBeAbleToSeeHomeScreen() {
        homeScreenPage
            .verifyLandingScreenVisible()
            .tapContinueAsGuest()
            .verifyLocationServicesScreen()
            .tapLocationNextAndAllow()
            .verifyNotificationServicesScreen()
            .tapNotificationNext()
            .verifyHomeScreen()
    }

    @Test
    fun validateGuestUserCanSignInFromHomeScreen() {
        homeScreenPage
            .verifyLandingScreenVisible()
            .tapContinueAsGuest()
            .verifyLocationServicesScreen()
            .tapLocationNextAndAllow()
            .verifyNotificationServicesScreen()
            .tapNotificationNext()
            .verifyHomeScreen()
            .tapSignInOnLanding()
            .verifySignInScreen()
            .enterUsername(validUsername)
            .enterPassword(validPassword)
            .tapSubmitSignIn()
            .verifyHomeScreen()
            .verifySignedInHomeScreen()
    }

    @Test
    fun validateFullStoryHomeRForSignedInUser() {
        homeScreenPage
            .verifyLandingScreenVisible()
            .tapSignInOnLanding()
            .verifySignInScreen()
            .enterUsername(validUsername)
            .enterPassword(validPassword)
            .tapSubmitSignIn()
            .verifyLocationServicesScreen()
            .tapLocationNextAndAllow()
            .verifyNotificationServicesScreen()
            .tapNotificationNext()
            .verifyHomeScreen()
            .verifySignedInHomeScreen()
            .verifyFullStoryHomeRComponent()
            .tapFullStoryHomeRButton(0)
            .tapFullStoryHomeRButton(1)
    }

    @Test
    fun validateNavigationFromSRLPToHome() {
        homeScreenPage
            .verifyHomeScreen()
            .enterSearchKeyword("shoes")
            .tapViewAll()
            .verifyProductListingScreen()
            .tapFooterShop()
            .verifyHomeScreen()
    }

    @Test
    fun validateNavigationFromPDPToHome() {
        homeScreenPage
            .verifyHomeScreen()
            .enterSearchKeyword("shoes")
            .tapViewAll()
            .verifyProductListingScreen()
            .tapProductCard(0)
            .verifyProductDetailPage()
            .tapFooterShop()
            .verifyHomeScreen()
    }

    @Test
    fun validateNavigationFromCertonaPDPToHome() {
        homeScreenPage
            .verifyHomeScreen()
            .tapProductCard(0)
            .verifyProductDetailPage()
            .tapFooterShop()
            .verifyHomeScreen()
    }

    @Test
    fun validateNavigationFromCategoryPDPToHome() {
        homeScreenPage
            .verifyHomeScreen()
            .tapFooterShop()
            .verifyProductListingScreen()
            .tapProductCard(0)
            .tapFooterShop()
            .verifyHomeScreen()
    }

    @Test
    fun validateNavigationFromSneakerCarouselToHome() {
        homeScreenPage
            .verifyHomeScreen()
            .verifySneakerReleasesCarousel()
            .tapSneakerCarouselItem(1)
            .tapFooterShop()
            .waitFor(7)
            .verifySneakerReleasesCarousel()
    }

    @Test
    fun validateNavigationFromSneakerReleasesLaunchedToHome() {
        homeScreenPage
            .verifyHomeScreen()
            .verifySneakerReleasesCarousel()
            .tapViewAll()
            .verifyProductListingScreen()
            .tapFooterShop()
            .verifyHomeScreen()
    }

    @Test
    fun validateNavigationFromCartToHome() {
        homeScreenPage
            .verifyHomeScreen()
            .tapFooterCart()
            .verifyCartScreen()
            .tapFooterShop()
            .verifyHomeScreen()
    }

    @Test
    fun validateNavigationFromAccountToHome() {
        homeScreenPage
            .verifyHomeScreen()
            .tapFooterAccount()
            .verifyAccountScreen()
            .tapFooterShop()
            .verifyHomeScreen()
    }

    @Test
    fun validateNavigationFromMoveTabToHome() {
        homeScreenPage
            .verifyHomeScreen()
            .tapFooterMoveTracker()
            .verifyMoveTrackerScreen()
            .tapFooterShop()
            .verifyHomeScreen()
    }

    @Test
    fun validateSneakerShortcutInFooter() {
        homeScreenPage
            .verifyHomeScreen()
            .tapFooterAddShortcut()
            .verifyManageShortcutsScreen()
            .toggleMoveOption()
            .tapFooterAddShortcut()
            .toggleSneakerOption()
            .tapFooterSneakerReleases()
            .tapFooterShop()
            .verifyHomeScreen()
    }

    @Test
    fun validateFavoriteShortcutInFooter() {
        homeScreenPage
            .verifyHomeScreen()
            .tapFooterAddShortcut()
            .verifyManageShortcutsScreen()
            .toggleMoveOption()
            .tapFooterAddShortcut()
            .toggleFavoriteOption()
            .tapFooterFavorite()
            .tapFooterShop()
            .verifyHomeScreen()
    }

    @Test
    fun validateMaintainScrollAndReset() {
        homeScreenPage
            .verifyHomeScreen()
            .verifySneakerReleasesCarousel()
            .tapFooterAccount()
            .tapFooterShop()
            .waitFor(5)
            .tapFooterShop()
            .verifySneakerReleasesCarousel()
    }

    @Test
    fun validateDropsCarousel() {
        homeScreenPage
            .verifyHomeScreen()
            .scrollDown()
            .verifyDropsCarousel()
            .tapViewAll()
            .verifyProductListingScreen()
    }

    @Test
    fun validateSearchBarAndBarcodeScanner() {
        homeScreenPage
            .verifyHomeScreen()
            .verifyHeader()
            .tapBarcodeScanner()
            .verifyHomeScreen()
    }

    @Test
    fun validateHeaderAndFooterBehavior() {
        homeScreenPage
            .verifyHomeScreen()
            .verifyHeader()
            .scrollDown()
            .verifyFooter()
            .verifyHeaderCollapsed()
            .scrollUp()
            .verifyHeader()
    }

    @Test
    fun validateMarketingPreviewDateTime() {
        homeScreenPage
            .verifyHomeScreen()
            .verifyHeader()
            .tapFooterAccount()
            .scrollDown()
            .tapVersionApp()
            .selectFutureDateInDatePicker()
            .tapFooterShop()
            .verifyPreviewMessage()
    }

    @Test
    fun validateDropsAndLatestLineUpCarousel() {
        homeScreenPage
            .verifyHomeScreen()
            .scrollDown()
            .verifyDropsCarousel()
            .verifyLatestLaunchLineUpCarousel()
            .tapViewAll()
            .verifyProductListingScreen()
    }
}
