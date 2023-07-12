package com.example.suitmediatest.ui

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.example.suitmediatest.databinding.ActivityFirstScreenBinding
import com.example.suitmediatest.ui.SecondScreen.Companion.EXTRA_USER

class FirstScreen : AppCompatActivity() {

    private lateinit var binding: ActivityFirstScreenBinding
    private var isPalindrome = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstScreenBinding.inflate(layoutInflater)

        if (Build.VERSION.SDK_INT < 21) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

        if (Build.VERSION.SDK_INT >= 21) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = Color.TRANSPARENT
        }

        supportActionBar?.hide()
        setContentView(binding.root)

        binding.btnCheck.setOnClickListener() {
            val text = binding.etPalindrome.text.toString().trim()

            if (text.isEmpty()) {
                binding.etPalindrome.error = "Please fill this form"
            } else {
                if(text == text.reversed()) {
                    isPalindrome = true

                    Toast.makeText(this, "isPalindrome", Toast.LENGTH_SHORT).show()
                } else {
                    isPalindrome = false

                    Toast.makeText(this, "notPalindrome", Toast.LENGTH_SHORT).show()
                    binding.etPalindrome.error = "notPalindrome"

                }
            }
        }

        binding.btnNext.setOnClickListener() {
            val name = binding.etName.text.toString()

            if (name.isEmpty()) {
                binding.etName.error = "Please fill this form"
            } else if (!isPalindrome) {
                binding.etPalindrome.error = "notPalindrome"
            } else {
                val intent = Intent(this, SecondScreen::class.java)
                    .putExtra(EXTRA_USER, name)
                startActivity(intent)

                !isPalindrome
            }
        }
    }

}