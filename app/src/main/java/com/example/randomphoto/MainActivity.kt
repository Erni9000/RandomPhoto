package com.example.randomphoto

import android.app.WallpaperManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.VmPolicy
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.randomphoto.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getPhoto(binding)
        binding.randomPhotoButton.setOnClickListener{
            getPhoto(binding)
        }
        binding.wallpaperButton.setOnClickListener{
            val bitmap: Bitmap? = (binding.imageView.drawable as BitmapDrawable?)?.bitmap

            if (bitmap != null) {
                val wallpaperManager = WallpaperManager.getInstance(
                    applicationContext
                )
                wallpaperManager.setBitmap(bitmap)
            } else {
                Toast.makeText(applicationContext,"No image loaded.", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun getPhoto(binding: ActivityMainBinding) {
        Glide.with(binding.imageView)
            .load("https://source.unsplash.com/random/640x960")
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .into(binding.imageView)

    }
}