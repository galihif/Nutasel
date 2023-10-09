package com.giftech.terbit.presentation.util

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.graphics.Typeface
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontSynthesis
import androidx.compose.ui.text.font.FontWeight

@Composable
fun LockScreenOrientation(orientation: Int) {
    fun Context.findActivity(): Activity? {
        return when (this) {
            is Activity -> this
            is ContextWrapper -> baseContext.findActivity()
            else -> null
        }
    }
    
    val context = LocalContext.current
    DisposableEffect(orientation) {
        val activity = context.findActivity() ?: return@DisposableEffect onDispose {}
        val originalOrientation = activity.requestedOrientation
        activity.requestedOrientation = orientation
        onDispose {
            activity.requestedOrientation = originalOrientation
        }
    }
}

data class TypefaceTypography(
    val displayLarge: Typeface,
    val displayMedium: Typeface,
    val displaySmall: Typeface,
    val headlineLarge: Typeface,
    val headlineMedium: Typeface,
    val headlineSmall: Typeface,
    val titleLarge: Typeface,
    val titleMedium: Typeface,
    val titleSmall: Typeface,
    val labelLarge: Typeface,
    val labelMedium: Typeface,
    val labelSmall: Typeface,
    val bodyLarge: Typeface,
    val bodyMedium: Typeface,
    val bodySmall: Typeface,
)

@Composable
fun TextStyle.toTypeface(): Typeface {
    val resolver: FontFamily.Resolver = LocalFontFamilyResolver.current
    return remember(resolver, this) {
        resolver.resolve(
            fontFamily = this.fontFamily,
            fontWeight = this.fontWeight ?: FontWeight.Normal,
            fontStyle = this.fontStyle ?: FontStyle.Normal,
            fontSynthesis = this.fontSynthesis ?: FontSynthesis.All,
        )
    }.value as Typeface
}

@Composable
fun Typography.toTypeface(): TypefaceTypography {
    return TypefaceTypography(
        displayLarge = this.displayLarge.toTypeface(),
        displayMedium = this.displayMedium.toTypeface(),
        displaySmall = this.displaySmall.toTypeface(),
        headlineLarge = this.headlineLarge.toTypeface(),
        headlineMedium = this.headlineMedium.toTypeface(),
        headlineSmall = this.headlineSmall.toTypeface(),
        titleLarge = this.titleLarge.toTypeface(),
        titleMedium = this.titleMedium.toTypeface(),
        titleSmall = this.titleSmall.toTypeface(),
        labelLarge = this.labelLarge.toTypeface(),
        labelMedium = this.labelMedium.toTypeface(),
        labelSmall = this.labelSmall.toTypeface(),
        bodyLarge = this.bodyLarge.toTypeface(),
        bodyMedium = this.bodyMedium.toTypeface(),
        bodySmall = this.bodySmall.toTypeface(),
    )
}