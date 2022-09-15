package com.yunushamod.android.beatbox.viewmodels

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.yunushamod.android.beatbox.models.Sound
import com.yunushamod.android.beatbox.services.BeatBox

//@BindingAdapter("app:SoundName")
//fun bindSoundName(button: Button, soundName: String){
//    button.text = soundName
//}
//
//@BindingAdapter("app:isGone")
//fun bindIsGone(view: View, isGone: Boolean){
//    view.visibility = if(isGone) View.GONE else View.VISIBLE
//}

class SoundViewModel(private val beatBox: BeatBox) : BaseObservable() {
    fun onButtonClicked() {
        sound?.let {
            beatBox.play(it)
        }
    }

    var sound: Sound? = null
    set(sound){
        field = sound
        notifyChange()
    }
    @get:Bindable
    val title: String?
    get() = sound?.name
}