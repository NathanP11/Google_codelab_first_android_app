/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.lunchtray

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lunchtray.ui.OrderViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lunchtray.datasource.DataSource
import com.example.lunchtray.ui.AccompanimentMenuScreen
import com.example.lunchtray.ui.BaseMenuScreen
import com.example.lunchtray.ui.CheckoutScreen
import com.example.lunchtray.ui.EntreeMenuScreen
import com.example.lunchtray.ui.SideDishMenuScreen
import com.example.lunchtray.ui.StartOrderScreen

// TODO: Screen enum
enum class ScreenName (@StringRes val title: Int) {
    Start(title = R.string.start_order),
    Entree_menu(title = R.string.choose_entree),
    Side_dish_menu(title = R.string.choose_side_dish),
    Accompaniment_menu(title = R.string.choose_accompaniment),
    Checkout(title = R.string.order_checkout)
}
// TODO: AppBar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LunchTrayAppBar(
    currentScreen: ScreenName,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LunchTrayApp(
    viewModel: OrderViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = ScreenName.valueOf(
        backStackEntry?.destination?.route ?: ScreenName.Start.name    )

    Scaffold(
        topBar = {
            LunchTrayAppBar(
                currentScreen = currentScreen,
                canNavigateBack =  navController.previousBackStackEntry != null,
                navigateUp = {  navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()
        NavHost(
            navController = navController,
            startDestination = ScreenName.Start.name,
            modifier = Modifier.padding(innerPadding)
        ) {

            composable(route = ScreenName.Start.name) {
                StartOrderScreen(
                    onStartOrderButtonClicked = {navController.navigate(ScreenName.Entree_menu.name)},
                    modifier = Modifier.fillMaxSize().padding(dimensionResource(R.dimen.padding_medium))
                )
            }

            composable(route = ScreenName.Entree_menu.name) {
                EntreeMenuScreen(
                    options = DataSource.entreeMenuItems,
                    onNextButtonClicked = {
                        navController.navigate(ScreenName.Side_dish_menu.name)
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(R.dimen.padding_medium)),
                    onSelectionChanged = {viewModel.updateEntree(it)},
                    onCancelButtonClicked = {cancelOrderAndNavigateToStart(viewModel, navController)}
                )
            }

            composable(route = ScreenName.Side_dish_menu.name) {
                SideDishMenuScreen(
                    onCancelButtonClicked = {cancelOrderAndNavigateToStart(viewModel, navController)},
                    onNextButtonClicked = {
                        navController.navigate(ScreenName.Accompaniment_menu.name) },
                    options = DataSource.sideDishMenuItems,
                    onSelectionChanged = { viewModel.updateSideDish(it) },
                    modifier = Modifier.fillMaxHeight()
                )
            }
            composable(route = ScreenName.Accompaniment_menu.name){
                AccompanimentMenuScreen(
                    onNextButtonClicked = { navController.navigate(ScreenName.Checkout.name) },
                    onCancelButtonClicked = {cancelOrderAndNavigateToStart(viewModel, navController)},
                    options = DataSource.accompanimentMenuItems,
                    onSelectionChanged = {viewModel.updateAccompaniment(it)},
                    modifier = Modifier.fillMaxHeight())
            }

            composable(route = ScreenName.Checkout.name) {
                CheckoutScreen(
                    orderUiState = uiState,
                    onCancelButtonClicked = {
                        cancelOrderAndNavigateToStart(viewModel, navController)},
                    onNextButtonClicked = { /*place order*/ },
                    modifier = Modifier.fillMaxHeight())
            }

        }
    }
}

private fun cancelOrderAndNavigateToStart(
    viewModel: OrderViewModel,
    navController: NavHostController
) {
    viewModel.resetOrder()
    navController.popBackStack(ScreenName.Start.name, inclusive = false)

}