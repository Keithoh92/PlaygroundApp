@file:JvmName("SnackbarUIContentKt")

package com.example.playground.external.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.example.playground.external.extensions.canvasDepth1

@Composable
fun SnackbarUIContent(
    modifier: Modifier = Modifier,
    message: String,
    onDismiss: () -> Unit = {},
){
    Row(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .padding(bottom = 16.dp)
            .canvasDepth1(
                shape = RoundedCornerShape(16.dp)
            )
            .clip(RoundedCornerShape(16.dp))
            .background(color = Color.Gray.copy(alpha = 0.7f))
            .pointerInput(Unit) {
                detectVerticalDragGestures { change, dragAmount ->
                    change.consume()
                    if (dragAmount > 0) {
                        onDismiss()
                    }
                }
            }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = message,
            color = Color.White,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .padding(end = 12.dp)
                .weight(1f)
        )
        Icon(
            modifier = Modifier
                .clickable {
                    onDismiss()
                },
            imageVector = Icons.Filled.Close,
            contentDescription = "close",
            tint = Color.White
        )
    }
}