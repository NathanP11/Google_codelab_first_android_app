package com.example.cupcake.test

import android.icu.util.Calendar
import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.cupcake.CupcakeApp
import com.example.cupcake.CupcakeScreen
import com.example.cupcake.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.Locale


class CupcakeScreenNavigationTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    private lateinit var navController: TestNavHostController

    @Before
    fun setupCupcakeNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            CupcakeApp(navController = navController)
        }
    }

    @Test
    fun cupcakeNavHost_verifyStartDestination() {
        navController.assertCurrentRouteName(CupcakeScreen.Start.name)
    }

    @Test
    fun cupcakeNavHost_verifyBackNavigationNotShownOnStartOrderScreen() {
        val backText = composeTestRule.activity.getString(R.string.back_button)
        composeTestRule.onNodeWithContentDescription(backText).assertDoesNotExist()
    }

    @Test
    fun cupcakeNavHost_clickOneCupcake_navigatesToSelectFlavorScreen() {
        composeTestRule.onNodeWithStringId(R.string.one_cupcake)
            .performClick()
        navController.assertCurrentRouteName(CupcakeScreen.Flavor.name)
    }

    private fun navigateToFlavorScreen() {
        composeTestRule.onNodeWithStringId(R.string.one_cupcake)
            .performClick()
    }

    private fun getFormattedDate(): String {
        val calendar = Calendar.getInstance()
        calendar.add(java.util.Calendar.DATE, 1)
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        return formatter.format(calendar.time)
    }

    private fun navigateToPickupScreen() {
        navigateToFlavorScreen()
        composeTestRule.onNodeWithStringId(R.string.next)
            .performClick()
    }

    private fun navigateToSummaryScreen() {
        navigateToPickupScreen()
        composeTestRule.onNodeWithText(getFormattedDate())
            .performClick()
        composeTestRule.onNodeWithStringId(R.string.next)
            .performClick()
    }

    private fun performNavigateUp() {
        val backText = composeTestRule.activity.getString(R.string.back_button)
        composeTestRule.onNodeWithContentDescription(backText).performClick()
    }

    @Test
    fun cupcakeNavHost_clickNextOnFlavorScreen_navigatesToPickupScreen() {
        composeTestRule.onNodeWithStringId(R.string.one_cupcake).performClick()
        composeTestRule.onNodeWithStringId(R.string.back_button).performClick()
        navController.assertCurrentRouteName(CupcakeScreen.Start.name)
    }

    @Test
    fun flavorScreen_verifyNextEnabled() {
        navigateToFlavorScreen()
        composeTestRule.onNodeWithStringId(R.string.chocolate).assertIsDisplayed()
        composeTestRule.onNodeWithStringId(R.string.chocolate).performClick()
        composeTestRule.onNodeWithStringId(R.string.next).assertIsEnabled()

    }

    @Test
    fun verifyStartContent() {
        navController.assertCurrentRouteName(CupcakeScreen.Start.name)
        composeTestRule.onNodeWithStringId(R.string.order_cupcakes).assertIsDisplayed()
        composeTestRule.onNodeWithStringId(R.string.one_cupcake).assertIsDisplayed()
        composeTestRule.onNodeWithStringId(R.string.six_cupcakes).assertIsDisplayed()
        composeTestRule.onNodeWithStringId(R.string.twelve_cupcakes).assertIsDisplayed()
        composeTestRule.onNodeWithStringId(R.string.twelve_cupcakes).performClick()
        //composeTestRule.onNodeWithStringId(R.string.next).assertIsEnabled()
    }

    @Test
    fun verifySummaryContent() {
        navigateToSummaryScreen()
        composeTestRule.onNodeWithStringId(R.string.quantity).assertIsDisplayed()
        composeTestRule.onNodeWithStringId(R.string.flavor).assertIsDisplayed()
        composeTestRule.onNodeWithStringId(R.string.pickup_date).assertIsDisplayed()
        composeTestRule.onNodeWithStringId(R.string.subtotal_price).assertIsDisplayed()
        composeTestRule.onNodeWithStringId(R.string.send).assertIsDisplayed()
        composeTestRule.onNodeWithStringId(R.string.cancel).assertIsDisplayed()
        composeTestRule.onNodeWithStringId(R.string.send).assertIsEnabled()
        composeTestRule.onNodeWithStringId(R.string.cancel).assertIsEnabled()
    }

}