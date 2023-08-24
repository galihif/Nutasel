package com.giftech.terbit.ui.pages.ffq.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.giftech.terbit.R
import com.giftech.terbit.ui.components.molecules.FfqFoodItem
import com.giftech.terbit.ui.pages.ffq.dialog.FfqAddFoodDialog
import com.giftech.terbit.ui.pages.ffq.dialog.FfqResponseBottomSheet

@Composable
fun FfqListScreen(
    programId: Int,
    foodCategoryId: Int,
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: FfqListViewModel = hiltViewModel(),
) {
    viewModel.onEvent(
        FfqListEvent.Init(
            programId = programId,
            foodCategoryId = foodCategoryId,
        )
    )
    val state = viewModel.state.collectAsState()
    
    FfqListContent(
        state = state.value,
        viewModel = viewModel,
        navController = navController,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FfqListContent(
    state: FfqListState,
    viewModel: FfqListViewModel,
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    val showAddFoodDialog = remember { mutableStateOf(false) }
    if (showAddFoodDialog.value) {
        FfqAddFoodDialog(
            setShow = {
                showAddFoodDialog.value = it
            },
            onSubmitNewFood = { foodName ->
                state.selectedFoodCategory?.let { foodCategory ->
                    viewModel.onEvent(
                        FfqListEvent.AddNewFood(
                            foodName = foodName,
                            foodCategoryId = foodCategory.foodCategoryId,
                        )
                    )
                }
            }
        )
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = state.selectedFoodCategory?.name.toString(),
                        style = MaterialTheme.typography.titleLarge,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = navController::popBackStack) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.btn_cd_back_ffqlist),
                        )
                    }
                },
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {
                    showAddFoodDialog.value = true
                },
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = null,
                    )
                },
                text = {
                    Text(
                        text = stringResource(R.string.btn_add_food_ffqlist),
                        style = MaterialTheme.typography.labelLarge,
                    )
                },
            )
        },
        floatingActionButtonPosition = FabPosition.Center,
        modifier = modifier,
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding),
        ) {
            LazyColumn(
                contentPadding = PaddingValues(
                    start = 24.dp,
                    end = 24.dp,
                    top = 24.dp,
                    bottom = 100.dp,
                ),
            ) {
                items(
                    items = state.questionListBySelectedFoodCategory,
                    key = { it.foodId },
                ) { item ->
                    val showResponseBottomSheet = remember { mutableStateOf(false) }
    
                    FfqFoodItem(
                        name = item.foodName,
                        isChecked = item.freq != null,
                        onClick = {
                            showResponseBottomSheet.value = true
                        },
                    )
                    
                    if (showResponseBottomSheet.value) {
                        FfqResponseBottomSheet(
                            setShow = {
                                showResponseBottomSheet.value = it
                            },
                            selectedResponse = item.freq,
                            onResponseSelected = {
                                viewModel.onEvent(
                                    FfqListEvent.SubmitResponse(
                                        programId = item.programId,
                                        foodId = item.foodId,
                                        freq = it,
                                    )
                                )
                            },
                        )
                    }
                }
            }
        }
    }
}