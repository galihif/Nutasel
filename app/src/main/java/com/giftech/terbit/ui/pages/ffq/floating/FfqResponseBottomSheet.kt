package com.giftech.terbit.ui.pages.ffq.floating

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.giftech.terbit.R
import com.giftech.terbit.domain.enums.FfqFrequency
import com.giftech.terbit.ui.components.atoms.MyFilterChips
import com.giftech.terbit.ui.components.atoms.PrimaryButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FfqResponseBottomSheet(
    setShow: (Boolean) -> Unit,
    selectedResponse: FfqFrequency?,
    onResponseSelected: (FfqFrequency) -> Unit,
    modifier: Modifier = Modifier,
) {
    val selectedResponseState = remember { mutableStateOf(selectedResponse) }
    
    ModalBottomSheet(
        sheetState = rememberModalBottomSheetState(
            skipPartiallyExpanded = true
        ),
        onDismissRequest = { setShow(false) },
        modifier = modifier,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
        ) {
            FfqFrequency.values().forEach { ffqFrequency ->
                MyFilterChips(
                    selected = ffqFrequency == selectedResponseState.value,
                    onSelectedChange = {
                        selectedResponseState.value = ffqFrequency
                    },
                    text = when (ffqFrequency) {
                        FfqFrequency.DAY_3 -> stringResource(R.string.ffq_freq_day_3)
                        FfqFrequency.DAY_1 -> stringResource(R.string.ffq_freq_day_1)
                        FfqFrequency.WEEK_3_6 -> stringResource(R.string.ffq_freq_week_3_6)
                        FfqFrequency.WEEK_1_2 -> stringResource(R.string.ffq_freq_week_1_2)
                        FfqFrequency.MONTH_2 -> stringResource(R.string.ffq_freq_month_2)
                        FfqFrequency.NEVER -> stringResource(R.string.ffq_freq_never)
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                )
            }
            PrimaryButton(
                text = stringResource(R.string.btn_next_ffqresponsebottomsheet),
                onClick = {
                    onResponseSelected(selectedResponseState.value!!)
                    setShow(false)
                },
                enabled = selectedResponseState.value != null,
                modifier = Modifier
                    .fillMaxWidth(),
            )
        }
    }
}