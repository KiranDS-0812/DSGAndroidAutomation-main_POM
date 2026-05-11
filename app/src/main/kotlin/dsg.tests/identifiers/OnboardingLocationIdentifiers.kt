package dsg.tests.identifiers

/**
 * Identifier Mapping for Onboarding Location Screen
 * Enterprise compliance: Centralized identifier management for maintainability and traceability
 * AuditLog: All identifiers documented for audit and compliance
 */
object OnboardingLocationIdentifiers {

    /**
     * UI Element Identifiers
     * Mapping Source: testTag values used in Compose UI
     */
    object TestTags {
        const val ONBOARDING_CONTINUE_BUTTON = "onboardingContinueButton"
        const val ONBOARDING_NOT_NOW_BUTTON = "onboardingNotNowButton"
    }

    /**
     * UI Element Descriptions
     * For documentation and traceability
     */
    object Descriptions {
        const val CONTINUE_BUTTON_DESC = "Continue primary button at bottom of screen"
        const val NOT_NOW_BUTTON_DESC = "Not Now secondary button below Continue"
        const val INFO_TEXT_DESC = "Not Now text label (R.string.ob_location_not_now_button)"
    }

    /**
     * Identifier Mapping Table
     * 
     * | Tag/Identifier                | UI Element Description                              | Mapping Source/TestTag                |
     * |-------------------------------|-----------------------------------------------------|---------------------------------------|
     * | onboardingContinueButton      | "Continue" primary button at bottom of screen       | testTag("onboardingContinueButton")   |
     * | onboardingNotNowButton        | "Not Now" secondary button below Continue           | testTag("onboardingNotNowButton")     |
     * | infoText (optional)           | "Not Now" text label (R.string.ob_location_not_now_button) | withText() matcher (if needed)        |
     */
}