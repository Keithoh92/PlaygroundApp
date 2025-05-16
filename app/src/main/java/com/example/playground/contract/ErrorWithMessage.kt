package com.example.playground.contract

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.playground.R
import com.example.playground.ui.theme.PlaygroundTheme

@Composable
fun ErrorWithMessage(
    icon: ImageVector = Icons.Filled.Error,
    errorMessage: String,
    modifier: Modifier = Modifier,
    animationSize: Dp = 180.dp,
    onRetry: (() -> Unit)? = null
) {
    Surface(modifier = modifier) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(imageVector = icon, contentDescription = "error icon")
            Text(
                text = errorMessage,
                modifier = Modifier.padding(horizontal = 24.dp),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge
            )
            onRetry?.run {
                Spacer(modifier = Modifier.height(24.dp))
                Button(onClick = this) {
                    Text(text = stringResource(id = R.string.retry))
                }
            }
        }
    }
}

@Preview
@Composable
private fun ErrorWithMessagePreview() {
    PlaygroundTheme {
        ErrorWithMessage(
            icon = Icons.Filled.Error,
            errorMessage = stringResource(id = R.string.something_went_wrong),
            modifier = Modifier.fillMaxSize(),
            onRetry = {}
        )
    }
}