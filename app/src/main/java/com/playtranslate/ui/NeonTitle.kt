package com.playtranslate.ui

import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.appbar.MaterialToolbar
import com.playtranslate.R
import com.playtranslate.isNeon

/** NeonLens X: cyan toolbar titles with a soft glow on the Neon theme. */
object NeonTitle {
    fun apply(toolbar: MaterialToolbar) {
        val ctx = toolbar.context
        if (!isNeon(ctx)) return
        val cyan = ContextCompat.getColor(ctx, R.color.nl_neon_cyan)
        toolbar.setTitleTextColor(cyan)
        toolbar.navigationIcon?.setTint(cyan)
        // The title TextView is created lazily by the toolbar once a title is set.
        toolbar.post {
            for (i in 0 until toolbar.childCount) {
                val child = toolbar.getChildAt(i)
                if (child is TextView && child.text == toolbar.title) {
                    child.setShadowLayer(14f * ctx.resources.displayMetrics.density, 0f, 0f, cyan)
                }
            }
        }
    }
}
