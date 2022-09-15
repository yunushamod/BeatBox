package com.yunushamod.android.beatbox.models

class Sound(val assetPath: String, var soundId: Int? = null) {
    companion object{
        private const val WAV = ".wav"
    }
    val name = assetPath.split('/').last().removeSuffix(WAV)

}