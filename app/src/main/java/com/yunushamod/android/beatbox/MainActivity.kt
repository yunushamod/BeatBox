package com.yunushamod.android.beatbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yunushamod.android.beatbox.databinding.ActivityMainBinding
import com.yunushamod.android.beatbox.databinding.ListItemSoundBinding
import com.yunushamod.android.beatbox.models.Sound
import com.yunushamod.android.beatbox.services.BeatBox
import com.yunushamod.android.beatbox.viewmodels.SoundViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var beatBox: BeatBox
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        beatBox = BeatBox(assets)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.recyclerView.apply{
            layoutManager = GridLayoutManager(context, 3)
            adapter = SoundAdapter(beatBox.sounds)
        }
    }

    private inner class SoundViewHolder(private val binding: ListItemSoundBinding) : RecyclerView.ViewHolder(binding.root){
        init{
            binding.viewModel = SoundViewModel(beatBox)
        }
        fun bind(sound: Sound){
            binding.apply {
                viewModel?.sound = sound
                executePendingBindings()
            }
        }
    }

    private inner class SoundAdapter(private val sounds: List<Sound>) : RecyclerView.Adapter<SoundViewHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoundViewHolder {
            val binding = DataBindingUtil.inflate<ListItemSoundBinding>(layoutInflater, R.layout.list_item_sound, parent, false)
            return SoundViewHolder(binding)
        }

        override fun onBindViewHolder(holder: SoundViewHolder, position: Int) {
            val sound = sounds[position]
            holder.bind(sound)
        }

        override fun getItemCount(): Int = sounds.size

    }

    override fun onDestroy() {
        super.onDestroy()
        beatBox.release()
    }
}