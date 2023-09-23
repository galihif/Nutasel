package com.giftech.terbit.ui.pages.notificationinbox

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.giftech.terbit.R

@Composable
fun NotificationInboxScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: NotificationInboxViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value
    
    NotificationInboxContent(
        state = state,
        viewModel = viewModel,
        navController = navController,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NotificationInboxContent(
    state: NotificationInboxState,
    viewModel: NotificationInboxViewModel,
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Notifikasi",
                        style = MaterialTheme.typography.titleLarge,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = navController::popBackStack) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.btn_cd_back),
                        )
                    }
                },
            )
        },
        modifier = modifier,
    ) { contentPadding ->
        LazyColumn(
            contentPadding = PaddingValues(vertical = 16.dp),
            modifier = modifier
                .padding(contentPadding),
        ) {
            if (state.isEmpty) {
                item {
                    Text(
                        text = "Tidak ada notifikasi",
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 32.dp),
                    )
                }
            }
            
            items(
                items = state.notificationList,
                key = { it.reminderId }
            ) { notification ->
                NotificationItem(
                    message = notification.message,
                    readStatus = notification.readStatus,
                    onClick = {
                        viewModel.onEvent(
                            NotificationInboxEvent.MarkAsRead(
                                notificationId = notification.notificationId,
                                idLink = notification.idLink,
                            )
                        )
                        notification.deepLink?.toUri()?.let {
                            navController.navigate(it)
                        }
                    },
                )
            }
        }
    }
}

@Composable
private fun NotificationItem(
    message: String,
    readStatus: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = if (readStatus) {
                    MaterialTheme.colorScheme.surface
                } else {
                    MaterialTheme.colorScheme.outlineVariant.copy(0.2f)
                },
            )
            .clickable { onClick() }
            .padding(horizontal = 16.dp),
    ) {
        Row(
            modifier = Modifier
                .padding(
                    horizontal = 16.dp,
                    vertical = 12.dp,
                )
        ) {
            Icon(
                imageVector = Icons.Outlined.Info,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = message,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .weight(1f),
            )
        }
        
        Divider(
            thickness = 1.dp,
            modifier = Modifier
                .fillMaxWidth(),
        )
    }
}