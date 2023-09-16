package com.giftech.terbit.ui.pages.ffq.result

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.giftech.terbit.R
import com.giftech.terbit.ui.components.atoms.PrimaryButton
import com.giftech.terbit.ui.route.Screen

@Composable
fun FfqResultScreen(
    programId: Int,
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: FfqResultViewModel = hiltViewModel(),
) {
    LaunchedEffect(programId) {
        viewModel.onEvent(
            FfqResultEvent.Init(
                programId = programId,
            )
        )
    }
    val state = viewModel.state.value
    
    FfqResultContent(
        state = state,
        navController = navController,
        modifier = modifier,
    )
}

@Composable
private fun FfqResultContent(
    state: FfqResultState,
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
    ) {
        Spacer(modifier = Modifier.height(48.dp))
        Image(
            painter = painterResource(R.drawable.vector_ffq_result),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .heightIn(max = 228.dp)
                .fillMaxHeight(),
        )
        
        Spacer(modifier = Modifier.height(48.dp))
        
        Text(
            text = stringResource(R.string.txt_title_ffqresult),
            style = MaterialTheme.typography.titleLarge,
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = stringResource(R.string.txt_desc_ffqresult),
            style = MaterialTheme.typography.bodySmall,
        )
        
        Spacer(modifier = Modifier.height(48.dp))
        
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(R.string.txt_subtitle_ffqresult),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(percent = 100),
                    )
                    .padding(horizontal = 24.dp, vertical = 4.dp),
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = state.result.toString(),
                style = MaterialTheme.typography.headlineMedium,
            )
        }
        
        Spacer(
            modifier = Modifier
                .height(48.dp)
                .weight(1f)
        )
        
        PrimaryButton(
            text = stringResource(R.string.btn_next_ffqresult),
            onClick = {
                navController.apply {
                    navigate(Screen.OnboardingTingkatPemantauan.route)
                }
            },
            modifier = Modifier
                .fillMaxWidth(),
        )
    }
}