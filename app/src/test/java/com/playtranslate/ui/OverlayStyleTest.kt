package com.playtranslate.ui

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

/** NeonLens X: the fixed translation-box colours. */
class OverlayStyleTest {

    @Test fun opacityGoesIntoTheAlphaChannel() {
        assertEquals(0xFF000000.toInt(), OverlayStyle.withOpacity(0x000000, 100))
        assertEquals(0xD9000000.toInt(), OverlayStyle.withOpacity(0xFF000000.toInt(), 85))
        assertEquals(0x4D123456, OverlayStyle.withOpacity(0x123456, 30))
        // Out-of-range values are clamped, not wrapped.
        assertEquals(0xFFABCDEF.toInt(), OverlayStyle.withOpacity(0xABCDEF, 250))
    }

    @Test fun pinholeFillIsOpaqueOtherwiseTheChosenOpacity() {
        val style = OverlayStyle(background = 0x80000000.toInt(), text = 0xFFFFFFFF.toInt(), bold = false)
        assertEquals(0x80000000.toInt(), style.fill(opaque = false))
        assertEquals(0xFF000000.toInt(), style.fill(opaque = true))
    }

    @Test fun outlineIsTheInverseOfTheTextColour() {
        assertEquals(0xFF000000.toInt(), OverlayStyle(0, 0xFFFFFFFF.toInt(), false).outline)
        assertEquals(0xFF00142C.toInt(), OverlayStyle(0, 0xFFFFEBD3.toInt(), false).outline)
    }

    @Test fun defaultConfigKeepsSampledColours() {
        assertNull(OverlayRenderConfig.DEFAULT.style)
    }
}
