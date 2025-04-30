package com.example.juicetracker_interop.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.juicetracker_interop.AppViewModelProvider
import com.example.juicetracker_interop.R
import com.example.juicetracker_interop.ui.bottomsheet.EntryBottomSheet
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val homeViewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.Hidden,
            skipHiddenState = false
        )
    )

    val scope = rememberCoroutineScope()
    val homeScreenUiState = homeViewModel.homeScreenUiState.collectAsStateWithLifecycle().value

    EntryBottomSheet(
        homeViewModel = homeViewModel,
        sheetScaffoldState = bottomSheetScaffoldState,
        onCancelClick = {
            scope.launch {
                bottomSheetScaffoldState.bottomSheetState.hide()
            }
        },
        onSaveClick = {
            homeViewModel.saveJuice()
            scope.launch {
                bottomSheetScaffoldState.bottomSheetState.hide()
            }
        }
    ) {
        Scaffold(
            topBar = { TopBar() },
            floatingActionButton = {
                FAB(
                    onClick = {
                        homeViewModel.resetCurrentJuice()
                        scope.launch { bottomSheetScaffoldState.bottomSheetState.expand() }
                    }
                )
            }
        ) { innerPadding ->

            Column(
                modifier = Modifier
                    .padding(innerPadding)
            ) {

                AdBanner(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = dimensionResource(R.dimen.padding_medium),
                            bottom = dimensionResource(R.dimen.padding_small)
                        )
                )

                when (homeScreenUiState) {
                    is HomeScreenUi.Empty -> {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text("Empty List")
                        }
                    }

                    is HomeScreenUi.Success -> {
                        JuiceTrackerList(
                            juices = homeScreenUiState.juiceListStream,
                            modifier = Modifier.fillMaxWidth(),
                            contentPaddingValues = PaddingValues(
                                horizontal = dimensionResource(R.dimen.padding_medium),
                                vertical = dimensionResource(R.dimen.padding_medium)
                            ),
                            onDelete = { homeViewModel.deleteJuice(it) },
                            onUpdate = {
                                homeViewModel.updateCurrentJuice(juice = it)
                                scope.launch {
                                    bottomSheetScaffoldState.bottomSheetState.expand()
                                }
                            }
                        )
                    }
                }
            }

        }
    }
}