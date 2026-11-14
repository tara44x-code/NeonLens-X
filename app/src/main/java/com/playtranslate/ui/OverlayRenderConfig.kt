package com.playtranslate.ui

import com.playtranslate.Prefs
import com.playtranslate.language.stackableTargetScript
import com.playtranslate.language.targetSupportsVerticalText

/**
 * The user settings a [TranslationOverlayView] renders with, fixed when the
 * view is built. One value instead of loose constructor flags so every place
 * that builds an overlay derives it the same way ([from]), and so the live
 * overlay's reuse guard compares all of it at once: a setting added here can't
 * be forgotten at a construction site or silently reused from a stale view.
 *
 * @property verticalTextTarget the target language is written vertically
 *   ([targetSupportsVerticalText]): its vertical boxes stack upright.
 * @property verticalTextStackable the target script can stack as upright cells
 *   ([stackableTargetScript]); gates STACK_UPRIGHT for non-CJK targets.
 * @property verticalGrowEnabled [Prefs.verticalTextGrow].
 * @property minTextSp [Prefs.overlayMinTextSp]; at
 *   [Prefs.OVERLAY_MIN_TEXT_SP_DEFAULT] the layout is exactly the historical one.
 * @property style the user's fixed box colours ([Prefs.overlayCustomStyle]);
 *   null keeps the colours sampled from the screen.
 */
data class OverlayRenderConfig(
    val verticalTextTarget: Boolean = false,
    val verticalTextStackable: Boolean = false,
    val verticalGrowEnabled: Boolean = false,
    val minTextSp: Int = Prefs.OVERLAY_MIN_TEXT_SP_DEFAULT,
    val style: OverlayStyle? = null,
) {
    companion object {
        /** A Latin-like target with every optional behaviour off. */
        val DEFAULT = OverlayRenderConfig()

        fun from(prefs: Prefs) = OverlayRenderConfig(
            verticalTextTarget = targetSupportsVerticalText(prefs.targetLang),
            verticalTextStackable = stackableTargetScript(prefs.targetLang),
            verticalGrowEnabled = prefs.verticalTextGrow,
            minTextSp = prefs.overlayMinTextSp,
            style = OverlayStyle.from(prefs),
        )
    }
}

/**
 * Fixed colours for translation boxes, chosen by the user (NeonLens X).
 * [background] carries the chosen opacity in its alpha channel.
 */
data class OverlayStyle(
    val background: Int,
    val text: Int,
    val bold: Boolean,
) {
    /** Box fill: the chosen colour, made opaque where the renderer needs an
     *  opaque fill (pinhole mode punches its own transparency). */
    fun fill(opaque: Boolean): Int = if (opaque) background or 0xFF000000.toInt() else background

    /** Outline drawn around the letters: the inverse of the text colour, the
     *  same rule the sampled colours use. */
    val outline: Int get() = text xor 0x00FFFFFF

    companion object {
        fun withOpacity(rgb: Int, percent: Int): Int {
            val alpha = (percent.coerceIn(0, 100) * 255 + 50) / 100
            return (alpha shl 24) or (rgb and 0x00FFFFFF)
        }

        fun from(prefs: Prefs): OverlayStyle? =
            if (!prefs.overlayCustomStyle) null
            else OverlayStyle(
                background = withOpacity(prefs.overlayBgColor, prefs.overlayBgOpacity),
                text = prefs.overlayTextColor,
                bold = prefs.overlayBoldText,
            )
    }
}
