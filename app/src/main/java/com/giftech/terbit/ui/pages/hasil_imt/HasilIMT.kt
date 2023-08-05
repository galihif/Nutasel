@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.giftech.terbit.ui.pages.hasil_imt

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Badge
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.giftech.terbit.data.model.User
import com.giftech.terbit.ui.components.atoms.PrimaryButton
import com.giftech.terbit.ui.components.enums.HeroEnum
import com.giftech.terbit.ui.components.molecules.HeroColumn
import com.giftech.terbit.ui.utils.toFormattedString

@Composable
fun HasilIMTScreen(
    onNext: () -> Unit = {},
    onBack: () -> Unit = {},
    user: User,
    viewModel: HasilIMTViewModel = hiltViewModel()
) {
    LaunchedEffect(user){
        viewModel.setUser(user)
    }
    val skorIMT by viewModel.skorIMT.collectAsState()
    val kategoriIMT by viewModel.kategoriIMT.collectAsState()
    BackHandler() {
        onBack()
    }
    Scaffold(
        containerColor = kategoriIMT.color,
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = kategoriIMT.color,
                    navigationIconContentColor = Color.White
                )
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            HeroColumn(hero = HeroEnum.HasilIMT, textColor = Color.White, imageHeight = 200)
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.onSurface
                ),
                shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(24.dp),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = user.nama,
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        Divider(thickness = 1.dp)
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 24.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text(
                                    text = "Skor IMT",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Text(
                                    text = skorIMT.toFormattedString(),
                                    style = MaterialTheme.typography.labelLarge
                                )
                            }
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text(
                                    text = "Kategori IMT",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Badge(
                                    containerColor = kategoriIMT.color
                                ) {
                                    Text(
                                        text = kategoriIMT.title,
                                        color = Color.White,
                                        style = MaterialTheme.typography.labelLarge,
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                    )
                                }
                            }
                        }
                        Divider(thickness = 1.dp)
                    }
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                    ){
                        Text(
                            text = "Penjelasan",
                            style = MaterialTheme.typography.labelLarge,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Text(
                            text = kategoriIMT.desc,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    PrimaryButton(
                        text = "Cek Status Gizi",
                        onClick = onNext,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}