package com.giftech.terbit.presentation.ui.pages.ffq.main

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.giftech.terbit.R
import com.giftech.terbit.presentation.ui.components.atoms.PrimaryButton
import com.giftech.terbit.presentation.ui.route.Screen

@Composable
fun FfqMainScreen(
    programId: Int,
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: FfqMainViewModel = hiltViewModel(),
) {
    LaunchedEffect(programId) {
        viewModel.onEvent(
            FfqMainEvent.Init(
                programId = programId,
            )
        )
    }
    val state = viewModel.state.value
    
    FfqMainContent(
        state = state,
        viewModel = viewModel,
        navController = navController,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FfqMainContent(
    state: FfqMainState,
    viewModel: FfqMainViewModel,
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.txt_title_ffqmain),
                        style = MaterialTheme.typography.titleLarge,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = navController::popBackStack) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.btn_cd_back_ffqmain),
                        )
                    }
                }
            )
        },
        modifier = modifier,
    ) { contentPadding ->
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(contentPadding),
        ) {
            LazyVerticalGrid(
                contentPadding = PaddingValues(24.dp),
                columns = GridCells.Adaptive(150.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp),
                horizontalArrangement = Arrangement.spacedBy(24.dp),
            ) {
                items(
                    items = state.foodCategoryList,
                    key = { it.foodCategoryId },
                ) { item ->
                    FfqFoodCategoryItem(
                        title = item.name,
                        image = item.imageRes,
                        onClick = {
                            navController.navigate(
                                Screen.FfqList.createRoute(
                                    programId = state.programId,
                                    foodCategoryId = item.foodCategoryId,
                                )
                            )
                        },
                    )
                }
                item(
                    span = { GridItemSpan(maxLineSpan) },
                ) {
                    PrimaryButton(
                        text = stringResource(R.string.btn_complete_ffqmain),
                        onClick = {
                            viewModel.onEvent(
                                FfqMainEvent.CompleteFfq(
                                    programId = state.programId,
                                )
                            )
                            navController.navigate(
                                Screen.FfqResult.createRoute(programId = state.programId)
                            )
                        },
                        enabled = state.isAllAnswered,
                        modifier = Modifier
                            .fillMaxWidth(),
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FfqFoodCategoryItem(
    title: String,
    @DrawableRes image: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                elevation = 12.dp,
                shape = RoundedCornerShape(12.dp),
            ),
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth(),
        )
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .padding(16.dp),
        )
    }
}