package com.giftech.terbit.presentation.ui.pages.home

import android.Manifest
import android.os.Build
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.NotificationsActive
import androidx.compose.material.icons.rounded.AccessTime
import androidx.compose.material.icons.rounded.ArrowRight
import androidx.compose.material.icons.rounded.CheckBoxOutlineBlank
import androidx.compose.material.icons.rounded.NotificationsNone
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.giftech.terbit.R
import com.giftech.terbit.domain.model.FillOutAsaq
import com.giftech.terbit.domain.model.FillOutFfq
import com.giftech.terbit.domain.model.ReadArticle
import com.giftech.terbit.domain.util.idFormat
import com.giftech.terbit.presentation.ui.components.enums.KategoriIMTEnum
import com.giftech.terbit.presentation.ui.components.molecules.Alerts
import com.giftech.terbit.presentation.ui.route.Screen
import com.giftech.terbit.presentation.ui.theme.CustomColor2
import com.giftech.terbit.presentation.ui.theme.CustomColor3
import com.giftech.terbit.presentation.ui.theme.light_onCustomColor2
import com.giftech.terbit.presentation.ui.theme.light_onCustomColor3
import com.giftech.terbit.presentation.util.showToast
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@Composable
fun HomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value
    
    if (state.isPreTestDone) {
        HomeContent(
            state = state,
            navController = navController,
            modifier = modifier,
        )
    } else {
        LoadingPageIndicator()
    }
    
    CheckHasCompletedPreTest(
        state = state,
        navController = navController,
    )
}

@Composable
private fun HomeContent(
    state: HomeState,
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        contentPadding = PaddingValues(24.dp),
        modifier = modifier
            .fillMaxWidth(),
    ) {
        item {
            HeaderSection(
                state = state,
                navController = navController,
            )
            
            Spacer(modifier = Modifier.height(32.dp))
            
            InitialConditionsSection(
                state = state,
            )
            
            if (state.isAllWeeklyProgramDone && !state.isPostTestDone) {
                Spacer(modifier = Modifier.height(32.dp))
                
                PostTestSection(
                    state = state,
                    navController = navController,
                )
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            NutritionistAssistanceSection(
                navController = navController,
            )
            
            if (state.nextDayProgramList.isNotEmpty()) {
                Spacer(modifier = Modifier.height(32.dp))
                
                HeaderOfActivityToDoSection()
            }
        }
        
        items(
            items = state.nextDayProgramList,
            key = { it.programId },
        ) { program ->
            ActivityToDoItem(
                programName = when (program) {
                    is FillOutAsaq -> "Pantau Sedenter"
                    is FillOutFfq -> "Pantau Pola Makan"
                    is ReadArticle -> "Baca Artikel"
                },
                dayOfWeek = "Hari ${program.dayOfWeek}",
                isAvailable = state.isNextDayProgramAvailable,
                onClick = if (state.isNextDayProgramAvailable) {
                    {
                        navController.navigate(
                            Screen.MonitoringDetails.createRoute(
                                week = program.week!!,
                            )
                        )
                        navController.navigate(
                            when (program) {
                                is FillOutAsaq -> Screen.WeeklyAsaq.createRoute(
                                    programId = program.programId,
                                )
                                
                                is FillOutFfq -> Screen.FfqMain.createRoute(
                                    programId = program.programId,
                                )
                                
                                is ReadArticle -> Screen.Article.createRoute(
                                    programId = program.programId,
                                    week = program.week!!,
                                    day = program.dayOfWeek!!,
                                )
                            }
                        )
                    }
                } else null,
            )
        }
        
        item {
            Spacer(modifier = Modifier.height(32.dp))
            
            ProgressSection(
                state = state,
            )
        }
    }
    
    CheckNotificationPermission()
}

@Composable
private fun HeaderSection(
    state: HomeState,
    navController: NavController,
) {
    Row {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(end = 16.dp),
        ) {
            Text(
                text = "Halo,",
                style = MaterialTheme.typography.bodyMedium,
            )
            Text(
                text = state.userName,
                style = MaterialTheme.typography.titleLarge,
            )
        }
        IconButton(
            onClick = {
                navController.navigate(Screen.NotificationInbox.route)
            },
        ) {
            Icon(
                imageVector = if (state.isNotificationEmpty) {
                    Icons.Rounded.NotificationsNone
                } else {
                    Icons.Outlined.NotificationsActive
                },
                contentDescription = "Notifikasi",
                tint = MaterialTheme.colorScheme.primary,
            )
        }
    }
}

@Composable
private fun InitialConditionsSection(
    state: HomeState,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(
            space = 12.dp,
            alignment = Alignment.CenterHorizontally,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = MaterialTheme.shapes.medium,
            )
            .padding(8.dp),
    ) {
        val bmi = KategoriIMTEnum.fromTitle(state.bmiCategory)
        InitialConditionContainer(
            title = "Kategori",
            value = state.bmiCategory,
            valueTextColor = bmi.accentColor,
            valueBackgroundColor = bmi.bgColor,
            modifier = Modifier
                .weight(1f),
        )
        InitialConditionContainer(
            title = "IMT",
            value = state.bmiValue.idFormat(),
            modifier = Modifier
                .weight(1f),
        )
        InitialConditionContainer(
            title = "Sedenter",
            value = state.monitoringLevel,
            modifier = Modifier
                .weight(1f),
        )
    }
}

@Composable
private fun PostTestSection(
    state: HomeState,
    navController: NavController,
) {
    if (!state.isPostTestAvailable) {
        Alerts(
            text = "Post test dibuka mulai tanggal ${state.postTestOpeningDate}",
            icon = Icons.Rounded.AccessTime,
        )
        
        Spacer(modifier = Modifier.height(24.dp))
    }
    
    Text(
        text = "Jangan lupa post test ya",
        style = MaterialTheme.typography.titleMedium,
    )
    
    Spacer(modifier = Modifier.height(24.dp))
    
    GeneralContainer(
        title = "Post test",
        desc = "Pantau pola hidup barumu",
        imageRes = R.drawable.img_posttest_icon_1080,
        nextIcon = if (state.isPostTestAvailable) {
            Icons.Rounded.ArrowRight
        } else {
            Icons.Outlined.Lock
        },
        onClick = if (state.isPostTestAvailable) {
            {
                navController.navigate(
                    Screen.OnboardingPosttest.route,
                )
            }
        } else {
            null
        },
    )
}

@Composable
private fun NutritionistAssistanceSection(
    navController: NavController,
) {
    Text(
        text = "Selalu ada buat kamu",
        style = MaterialTheme.typography.titleMedium,
    )
    
    Spacer(modifier = Modifier.height(24.dp))
    
    GeneralContainer(
        title = "Butuh bantuan?",
        desc = "Konsultasi dengan ahli gizi di sini",
        imageRes = R.drawable.img_nutritionist_assistance_168,
        nextIcon = Icons.Rounded.ArrowRight,
        onClick = {
            navController.navigate(Screen.Profesional.route)
        },
    )
}

@Composable
private fun HeaderOfActivityToDoSection() {
    Text(
        text = "Aktivitas mingguan kamu",
        style = MaterialTheme.typography.titleMedium,
    )
    
    Spacer(modifier = Modifier.height(24.dp))
}

@Composable
private fun ProgressSection(
    state: HomeState,
) {
    Text(
        text = "Perjalananmu sejauh ini",
        style = MaterialTheme.typography.titleMedium,
    )
    
    Spacer(modifier = Modifier.height(24.dp))
    
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        ProgressContainer(
            title = "Program Selesai",
            value = state.totalCompletedProgram.toString(),
            desc = "${state.totalCompletedProgram} / ${state.totalProgram} Program",
            backgroundColor = CustomColor3,
            textColor = light_onCustomColor3,
            modifier = Modifier
                .weight(1f),
        )
        ProgressContainer(
            title = "Hari Ke",
            value = state.totalCompletedDaysInWeek.toString(),
            desc = "Minggu ke-${state.currentWeek}",
            backgroundColor = MaterialTheme.colorScheme.tertiary,
            textColor = MaterialTheme.colorScheme.onTertiary,
            modifier = Modifier
                .weight(1f),
        )
    }
    
    Spacer(modifier = Modifier.height(8.dp))
    
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        ProgressContainer(
            title = "Persentase Program",
            value = "${state.programProgressPercentage}%",
            desc = "${state.totalCompletedProgram} / ${state.totalProgram} Program",
            backgroundColor = MaterialTheme.colorScheme.primary,
            textColor = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .weight(1f),
        )
        ProgressContainer(
            title = "Program Mingguan",
            value = state.totalCompletedWeek.toString(),
            desc = "${state.totalCompletedWeek} / ${state.totalWeek} Program Selesai",
            backgroundColor = CustomColor2,
            textColor = light_onCustomColor2,
            modifier = Modifier
                .weight(1f),
        )
    }
}

@Composable
private fun InitialConditionContainer(
    title: String,
    value: String,
    modifier: Modifier = Modifier,
    valueTextColor: Color = MaterialTheme.colorScheme.onSurface,
    valueBackgroundColor: Color = MaterialTheme.colorScheme.secondaryContainer,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .clip(MaterialTheme.shapes.small)
            .background(MaterialTheme.colorScheme.surface)
            .padding(6.dp),
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = value,
            style = MaterialTheme.typography.labelMedium,
            color = valueTextColor,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .background(
                    color = valueBackgroundColor,
                    shape = RoundedCornerShape(percent = 100),
                )
                .padding(4.dp)
                .fillMaxWidth(),
        )
    }
}

@Composable
private fun GeneralContainer(
    title: String,
    desc: String,
    @DrawableRes imageRes: Int,
    nextIcon: ImageVector,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clip(MaterialTheme.shapes.medium)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outlineVariant,
                shape = MaterialTheme.shapes.medium,
            )
            .clickable(enabled = onClick != null) { onClick!!() }
            .padding(
                horizontal = 16.dp,
                vertical = 12.dp,
            ),
    ) {
        Image(
            painter = painterResource(imageRes),
            contentDescription = null,
            modifier = Modifier
                .clip(MaterialTheme.shapes.small)
                .size(56.dp),
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
            )
            Text(
                text = desc,
                style = MaterialTheme.typography.bodySmall,
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Icon(
            imageVector = nextIcon,
            contentDescription = null,
        )
    }
}

@Composable
private fun ActivityToDoItem(
    programName: String,
    dayOfWeek: String,
    isAvailable: Boolean,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clip(MaterialTheme.shapes.medium)
            .clickable(enabled = onClick != null) { onClick!!() }
            .padding(8.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
        ) {
            Text(
                text = programName,
                style = MaterialTheme.typography.bodyLarge,
            )
            Text(
                text = dayOfWeek,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Icon(
            imageVector = if (isAvailable) Icons.Rounded.CheckBoxOutlineBlank
            else Icons.Outlined.Lock,
            contentDescription = if (isAvailable) "Belum dikerjakan"
            else "Belum tersedia",
        )
    }
}

@Composable
private fun ProgressContainer(
    title: String,
    value: String,
    desc: String,
    backgroundColor: Color,
    textColor: Color,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .clip(MaterialTheme.shapes.large)
            .background(backgroundColor)
            .padding(12.dp),
    ) {
        Text(
            text = title,
            color = textColor,
            style = MaterialTheme.typography.labelLarge,
        )
        Text(
            text = value,
            color = textColor,
            style = MaterialTheme.typography.displayMedium,
        )
        Text(
            text = desc,
            color = textColor,
            style = MaterialTheme.typography.bodySmall,
        )
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CheckNotificationPermission() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        val context = LocalContext.current
        
        val notificationPermisionState = rememberPermissionState(
            Manifest.permission.POST_NOTIFICATIONS,
        )
        
        if (notificationPermisionState.status.isGranted.not()) {
            SideEffect {
                showToast(
                    context = context,
                    message = "Aplikasi membutuhkan izin untuk menampilkan notifikasi.",
                )
                notificationPermisionState.launchPermissionRequest()
            }
        }
    }
}

@Composable
private fun CheckHasCompletedPreTest(
    state: HomeState,
    navController: NavController,
) {
    LaunchedEffect(state.isPreTestDone) {
        if (state.isPreTestDone.not()) {
            navController.navigate(
                Screen.AppOnboarding.route,
            ) {
                popUpTo(Screen.Home.route) {
                    inclusive = true
                }
            }
        }
    }
}

@Composable
private fun LoadingPageIndicator() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize(),
    ) {
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth(0.35f)
                .clip(RoundedCornerShape(100)),
        )
    }
}