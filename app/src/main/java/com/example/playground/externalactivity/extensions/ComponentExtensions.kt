package com.example.playground.externalactivity.extensions

import android.graphics.BlurMaskFilter
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

fun Modifier.canvasDepth1(
    shape: CornerBasedShape, zIndex: Float = 1f, invertShadowDirection: Boolean = false,
): Modifier {
    return customShadow(
        zIndex = zIndex,
        offsetX = 0.dp,
        offsetX2 = 0.dp,
        offsetY = 1.dp,
        offsetY2 = 2.dp,
        blurRadius = 3.dp,
        blurRadius2 = 4.dp,
        shadowColor = Color.Black.copy(alpha = 0.7f),
        shadowAlpha = 30,
        shadowAlpha2 = 20,
        shape = shape,
        invertShadowDirection = invertShadowDirection,
    )
}

private fun Modifier.customShadow(
    zIndex: Float,
    offsetX: Dp,
    offsetX2: Dp,
    offsetY: Dp,
    offsetY2: Dp,
    blurRadius: Dp,
    blurRadius2: Dp,
    shadowColor: Color,
    shadowAlpha: Int,
    shadowAlpha2: Int,
    shape: CornerBasedShape,
    invertShadowDirection: Boolean,
): Modifier {
    val finalOffsetY = if (invertShadowDirection) {
        offsetY * -1
    } else {
        offsetY
    }
    val finalOffsetY2 = if (invertShadowDirection) {
        offsetY2 * -1
    } else {
        offsetY2
    }
    return drawBehind {
        this.drawIntoCanvas {
            drawShadow(offsetX, finalOffsetY, blurRadius, shadowColor, shadowAlpha, it, shape)
            drawShadow(offsetX2, finalOffsetY2, blurRadius2, shadowColor, shadowAlpha2, it, shape)
        }
    }.zIndex(zIndex)
}

private fun DrawScope.drawShadow(
    offsetX: Dp,
    offsetY: Dp,
    blurRadius: Dp,
    shadowColor: Color,
    shadowAlpha: Int,
    canvas: Canvas,
    shape: CornerBasedShape
) {
    val paint = Paint()
    val frameworkPaint = paint.asFrameworkPaint()
    val leftPixel = offsetX.toPx()
    val topPixel = offsetY.toPx()
    val rightPixel = this.size.width + offsetX.toPx()
    val bottomPixel = this.size.height + offsetY.toPx()
    if (blurRadius != 0.dp) {
        frameworkPaint.maskFilter =
            (BlurMaskFilter(blurRadius.toPx(), BlurMaskFilter.Blur.NORMAL))
    }
    frameworkPaint.color = shadowColor.toArgb()
    frameworkPaint.alpha = shadowAlpha
    canvas.drawRoundRect(
        left = leftPixel,
        top = topPixel,
        right = rightPixel,
        bottom = bottomPixel,
        radiusX = shape.bottomEnd.toPx(size, Density(density)),
        radiusY = shape.bottomEnd.toPx(size, Density(density)),
        paint = paint
    )
}