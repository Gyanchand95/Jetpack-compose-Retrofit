package com.gp.mynewsproject.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.gp.mynewsproject.R

@Composable
private fun TextDefineFontFamilySnippet() {
    // [START android_compose_text_multiple_fonts_styles]
    val firaSansFamily = FontFamily(
        Font(R.font.gotham_bold, FontWeight.Bold),
        Font(R.font.gotham_medium, FontWeight.Medium),
    )
    // [END android_compose_text_multiple_fonts_styles]
}