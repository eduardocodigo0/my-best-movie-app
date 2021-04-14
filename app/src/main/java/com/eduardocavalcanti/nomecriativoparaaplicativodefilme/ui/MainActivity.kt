package com.eduardocavalcanti.nomecriativoparaaplicativodefilme.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.VideoView
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.eduardocavalcanti.nomecriativoparaaplicativodefilme.R
import com.eduardocavalcanti.nomecriativoparaaplicativodefilme.config.EduUtils
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var video: VideoView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        configureNavigation()

    }


    override fun onResume() {
        super.onResume()

        video = findViewById(R.id.vv_background_video)
        video.setVideoURI(EduUtils.getBackgroundVideoURI(packageName))
        video.start()
        video.setOnCompletionListener {
            video.start()
        }

    }

    private fun configureNavigation() {
        val bottomNavigationBar: BottomNavigationView = findViewById(R.id.bnv_bottom_menu)
        val navigationController = findNavController(R.id.f_host_fragment)
        bottomNavigationBar.setupWithNavController(navigationController)
    }

}