package com.giftech.terbit.presentation.ui.pages.profesional

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.giftech.terbit.R
import com.giftech.terbit.presentation.ui.components.enums.ProfMenuEnum
import com.giftech.terbit.presentation.ui.components.molecules.KTRDialog
import com.giftech.terbit.presentation.ui.components.molecules.ProfInfoColumn
import com.giftech.terbit.presentation.ui.components.molecules.ProfKredColumn
import com.giftech.terbit.presentation.ui.components.molecules.SegmentedButtonRow

@ExperimentalMaterial3Api
@Composable
fun ProfesionalScreen(
    onBack: () -> Unit,
    viewModel: ProfesionalViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    var selectedMenu by remember {
        mutableStateOf(ProfMenuEnum.INFO)
    }
    val scrollState = rememberScrollState()
    var openDialog by remember {
        mutableStateOf(false)
    }
    if (openDialog) {
        KTRDialog {
            openDialog = false
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Profesional", style = MaterialTheme.typography.headlineSmall)
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                    }
                },
            )
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(it)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(18.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profesional),
                    contentDescription = "Profesional",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                )
                Text(
                    text = "Inneke Orrhyza Agriana",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Nutritionis",
                    style = MaterialTheme.typography.bodySmall
                )
            }
            SegmentedButtonRow(
                selected = selectedMenu,
                onSelected = { menu ->
                    selectedMenu = menu
                }
            )
            when (selectedMenu) {
                ProfMenuEnum.INFO -> {
                    ProfInfoColumn(
                        onContactClick = {
                            viewModel.contactProfesionalByWhatsapp(context)
                        }
                    )
                }

                ProfMenuEnum.KREDIBILITAS -> {
                    ProfKredColumn(
                        onKTRClick = {
                            openDialog = true
                        }
                    )
                }
            }
        }
    }
}