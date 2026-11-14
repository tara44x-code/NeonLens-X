package com.playtranslate.ui

import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.os.Build
import android.view.View

/**
 * NeonLens X: the neon card look. A gradient ring (magenta to blue) drawn as
 * an outer rounded rectangle with the card fill inset inside it, plus a
 * coloured elevation shadow that reads as a soft glow around the card.
 */
object NeonBorder {
    private const val BORDER_DP = 1.6f
    private const val GLOW_ELEVATION_DP = 10f

    fun drawable(ctx: Context, fill: Int, start: Int, end: Int, radius: Float): Drawable {
        val border = (BORDER_DP * ctx.resources.displayMetrics.density).toInt().coerceAtLeast(1)
        val ring = GradientDrawable(GradientDrawable.Orientation.TR_BL, intArrayOf(start, end)).apply {
            cornerRadius = radius
        }
        val inner = GradientDrawable().apply {
            setColor(fill)
            cornerRadius = (radius - border).coerceAtLeast(0f)
        }
        return LayerDrawable(arrayOf(ring, inner)).apply {
            setLayerInset(1, border, border, border, border)
        }
    }

    /** Coloured shadow (API 28+) so the card seems to glow; a no-op below. */
    fun glow(view: View, color: Int) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) return
        view.elevation = GLOW_ELEVATION_DP * view.resources.displayMetrics.density
        view.outlineAmbientShadowColor = color
        view.outlineSpotShadowColor = color
    }
}
