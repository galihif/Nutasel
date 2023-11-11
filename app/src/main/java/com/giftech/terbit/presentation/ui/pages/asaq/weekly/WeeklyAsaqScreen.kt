package com.giftech.terbit.presentation.ui.pages.asaq.weekly

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.giftech.terbit.presentation.ui.components.atoms.MyOutlinedTextField
import com.giftech.terbit.presentation.ui.components.atoms.PrimaryButton
import com.giftech.terbit.presentation.ui.components.molecules.AppBar
import com.giftech.terbit.presentation.ui.components.molecules.HeroColumn
import com.giftech.terbit.presentation.ui.route.Screen

@Composable
fun WeeklyAsaqScreen(
    programId: Int,
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: WeeklyAsaqViewModel = hiltViewModel(),
) {
    LaunchedEffect(programId) {
        viewModel.onEvent(
            WeeklyAsaqEvent.Init(
                programId = programId,
            )
        )
    }
    val state = viewModel.state.value
    
    WeeklyAsaqContent(
        state = state,
        navController = navController,
        viewModel = viewModel,
        modifier = modifier,
    )
}

@Composable
fun WeeklyAsaqContent(
    state: WeeklyAsaqState,
    navController: NavController,
    viewModel: WeeklyAsaqViewModel,
    modifier: Modifier = Modifier,
) {
    val onBack = {
        if (state.isFirstQuestion) {
            navController.popBackStack()
        } else {
            viewModel.onEvent(
                WeeklyAsaqEvent.PreviousQuestion(
                    currentQuestion = state.currentQuestion,
                )
            )
        }
    }
    BackHandler { onBack() }
    
    Scaffold(
        topBar = {
            AppBar(
                title = "${state.currentQuestion.questionId}/12",
                onBack = { onBack() },
            )
        },
        modifier = modifier,
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .padding(24.dp),
        ) {
            QuestionSection(
                state = state,
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            ResponseSection(
                state = state,
                viewModel = viewModel,
            )
            
            Spacer(
                modifier = Modifier
                    .heightIn(min = 24.dp)
                    .weight(1f)
            )
            
            NavigationSection(
                state = state,
                viewModel = viewModel,
                navController = navController,
            )
        }
    }
}

@Composable
private fun QuestionSection(
    state: WeeklyAsaqState,
) {
    HeroColumn(
        title = state.currentQuestion.title,
        description = state.currentQuestion.question,
        imageRes = state.currentQuestion.imageRes,
        imageHeight = 200,
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun ResponseSection(
    state: WeeklyAsaqState,
    viewModel: WeeklyAsaqViewModel,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        MyOutlinedTextField(
            label = "Jam",
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next,
            ),
            value = state.hoursFreq?.toString().orEmpty(),
            onValueChange = {
                viewModel.onEvent(
                    WeeklyAsaqEvent.EnteredHoursFreq(it)
                )
            },
            modifier = Modifier
                .weight(1f)
        )
        MyOutlinedTextField(
            label = "Menit",
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done,
            ),
            value = state.minutesFreq?.toString().orEmpty(),
            onValueChange = {
                viewModel.onEvent(
                    WeeklyAsaqEvent.EnteredMinutesFreq(it)
                )
            },
            modifier = Modifier
                .weight(1f)
        )
    }
}

@Composable
private fun NavigationSection(
    state: WeeklyAsaqState,
    viewModel: WeeklyAsaqViewModel,
    navController: NavController,
) {
    val focusManager = LocalFocusManager.current
    PrimaryButton(
        text = if (state.isLastQuestion.not()) "Selanjutnya"
        else "Selesai",
        onClick = {
            focusManager.clearFocus(true)
            viewModel.onEvent(
                WeeklyAsaqEvent.SubmitResponse(
                    programId = state.programId,
                    currentQuestion = state.currentQuestion,
                    hoursFreq = state.hoursFreq,
                    minutesFreq = state.minutesFreq,
                )
            )
            
            if (state.isLastQuestion.not()) {
                viewModel.onEvent(
                    WeeklyAsaqEvent.NextQuestion(
                        currentQuestion = state.currentQuestion,
                    )
                )
            } else {
                viewModel.onEvent(
                    WeeklyAsaqEvent.CompleteAsaq(
                        programId = state.programId,
                    )
                )
                navController.popBackStack()
                navController.navigate(
                    Screen.WeeklyAsaqComplete.createRoute(
                        sedentaryAverageHours = state.sedentaryAverageHours,
                    )
                )
            }
        },
        modifier = Modifier
            .fillMaxWidth(),
    )
}