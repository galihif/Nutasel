package com.giftech.terbit.ui.pages.ffq.dialog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.giftech.terbit.R
import com.giftech.terbit.ui.components.atoms.MyOutlinedTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FfqAddFoodDialog(
    setShow: (Boolean) -> Unit,
    onSubmitNewFood: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    
    val foodName = remember { mutableStateOf("") }
    
    Dialog(
        onDismissRequest = { setShow(false) },
    ) {
        Surface(
            shape = MaterialTheme.shapes.large,
            modifier = modifier,
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp),
            ) {
                Text(
                    text = stringResource(R.string.txt_title_addfooddialog),
                    style = MaterialTheme.typography.headlineSmall,
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                MyOutlinedTextField(
                    value = foodName.value,
                    onValueChange = {
                        foodName.value = it
                    },
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    text = stringResource(R.string.btn_add_addfooddialog),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .clip(MaterialTheme.shapes.medium)
                        .clickable {
                            onSubmitNewFood(foodName.value)
                            setShow(false)
                        }
                        .padding(12.dp)
                        .align(Alignment.End),
                )
            }
        }
    }
    
}