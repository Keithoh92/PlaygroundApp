package com.example.playground.contract

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.playground.ui.theme.halfSpacing
import com.example.playground.ui.theme.spacing24

@Composable
fun Loading(
    modifier: Modifier = Modifier,
    title: String? = null,
    subTitle: String? = null,
) {
    Surface(modifier = modifier) {
        Column(
            verticalArrangement = Arrangement.spacedBy(halfSpacing, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            title?.run {
                Text(
                    text = this,
                    modifier = Modifier.padding(horizontal = spacing24),
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge
                )
            }
            subTitle?.run {
                Text(
                    text = this,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                    modifier = Modifier.padding(horizontal = spacing24),
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }
    }
}
