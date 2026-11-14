package com.playtranslate.tts

import android.content.Context
import android.widget.Toast
import com.playtranslate.Prefs
import com.playtranslate.R
import com.playtranslate.language.SourceLangId
import com.playtranslate.ui.TextBox

/**
 * NeonLens X: reads the latest translation aloud in the user's own
 * language, on demand (the "Read translation aloud" floating-icon action).
 *
 * The last translation drawn over the game, or shown in a result panel, is
 * kept here as plain text. Speaking uses the phone's text-to-speech voice
 * for the target language, so an Arabic voice must be installed for
 * Arabic.
 */
object SpokenTranslation {

    @Volatile
    private var latest: String = ""

    /** Keep [text] as the latest translation. Blank text is ignored, so a
     *  cleared overlay does not wipe what the user may still want to hear. */
    fun record(text: String) {
        val trimmed = text.trim()
        if (trimmed.isNotEmpty()) latest = trimmed
    }

    /** Keep the translated boxes of an overlay, read top to bottom, then
     *  right to left or left to right as the screen lays them out. Reading
     *  hints (furigana) are not translations and are left out. */
    fun recordBoxes(boxes: List<TextBox>) {
        val text = boxes
            .filter { !it.isFurigana && it.translatedText.isNotBlank() }
            .sortedWith(compareBy({ it.bounds.top }, { it.bounds.left }))
            .joinToString("\n") { it.translatedText.trim() }
        record(text)
    }

    /** Speak the latest translation, or say why it can't. */
    suspend fun speakLatest(context: Context) {
        val text = latest
        if (text.isEmpty()) {
            toast(context, R.string.nl_speak_nothing)
            return
        }
        val lang = SourceLangId.fromCode(Prefs(context).targetLang)
        if (lang == null) {
            toast(context, R.string.nl_speak_no_voice)
            return
        }
        when (TtsEngine.speak(context, text, lang)) {
            TtsEngine.SpeakResult.Spoken -> Unit
            else -> toast(context, R.string.nl_speak_no_voice)
        }
    }

    private fun toast(context: Context, res: Int) {
        Toast.makeText(context.applicationContext, res, Toast.LENGTH_LONG).show()
    }
}
