package com.giftech.terbit.ui.pages.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.hilt.navigation.compose.hiltViewModel
import com.giftech.terbit.ui.components.atoms.MyOutlinedTextField
import com.giftech.terbit.ui.components.atoms.PrimaryButton
import com.giftech.terbit.ui.components.molecules.MyDatePickerDialog

@ExperimentalMaterial3Api
@Composable
fun EditProfileScreen(
    onBack: () -> Unit,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val nama by remember { viewModel.nama }.collectAsState()
    val tinggi by remember { viewModel.tinggi }.collectAsState()
    val berat by remember { viewModel.berat }.collectAsState()
    val tglLahir by remember { viewModel.tglLahir }.collectAsState()

    var showDatePicker by remember {
        mutableStateOf(false)
    }

    if (showDatePicker) {
        MyDatePickerDialog(
            initialDate = tglLahir,
            onDateSelected = { viewModel.tglLahir.value = it },
            onDismiss = { showDatePicker = false }
        )
    }

    var isValid by remember { mutableStateOf(false) }
    LaunchedEffect(nama, tinggi, berat, tglLahir) {
        isValid =
            nama.isNotBlank() && tinggi.isNotBlank() && berat.isNotBlank() && tglLahir.isNotBlank()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Edit")
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(24.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "Informasi Personal",
                    style = MaterialTheme.typography.titleMedium
                )
                MyOutlinedTextField(
                    value = nama,
                    onValueChange = { newVal -> viewModel.nama.value = newVal },
                    label = "Nama",
                    supportingText = "Nama Lengkap Kamu",
                )
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    MyOutlinedTextField(
                        value = berat,
                        onValueChange = {newVal ->
                            if (newVal.isDigitsOnly()) {
                                viewModel.berat.value = newVal
                            }
                        },
                        label = "Berat",
                        supportingText = "Kilogram (Kg)",
                        modifier = Modifier.weight(1f),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number
                        )
                    )
                    Spacer(Modifier.width(24.dp))
                    MyOutlinedTextField(
                        value = tinggi,
                        onValueChange = {newVal ->
                            if (newVal.isDigitsOnly()) {
                                viewModel.tinggi.value = newVal
                            }
                        },
                        label = "Tinggi",
                        supportingText = "Centimeter (Cm)",
                        modifier = Modifier.weight(1f),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number
                        )
                    )
                }
                MyOutlinedTextField(
                    value = tglLahir,
                    onValueChange = {},
                    readOnly = true,
                    label = "Tanggal Lahir",
                    supportingText = "Tanggal/Bulan/Tahun",
                    trailingIcon = {
                        IconButton(onClick = {
                            showDatePicker = true
                        }) {
                            Icon(imageVector = Icons.Default.CalendarMonth, contentDescription = "")
                        }
                    }
                )
            }
            PrimaryButton(
                text = "Selesai",
                onClick = {
                    viewModel.saveUser()
                    onBack()
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = isValid
            )
        }
    }
}