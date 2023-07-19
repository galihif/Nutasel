package com.giftech.nutasel.ui.components.atoms

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

@ExperimentalMaterial3Api
@Composable
fun MyOutlinedTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label:String = "",
    supportingText: String = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(
        keyboardType = KeyboardType.Text
    )
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        supportingText = {
            if (supportingText.isNotBlank()) {
                Text(
                    text = supportingText
                )
            }
        },
        modifier = modifier.fillMaxWidth(),
        label = {
            if (label.isNotBlank()) {
                Text(
                    text = label
                )
            }
        },
        keyboardOptions = keyboardOptions
    )
}