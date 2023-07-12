package com.example.suitmediatest.ui

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.contract.ActivityResultContracts
import com.example.suitmediatest.databinding.ActivitySecondScreenBinding
import com.example.suitmediatest.ui.third_screen.ThirdScreen

class SecondScreen : AppCompatActivity() {
    private lateinit var binding: ActivitySecondScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondScreenBinding.inflate(layoutInflater)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        setContentView(binding.root)

        val name = intent.getStringExtra(EXTRA_USER)
        binding.tvUsername.text = name

        val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data

                val user = data?.getStringExtra("user")
                binding.tvChooseUser.text = user

            }
        }

        binding.btnChooseUser.setOnClickListener {
            val intent = Intent(this, ThirdScreen::class.java)
            resultLauncher.launch(intent)

        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finish()
            }
        })

    }

    companion object {
        const val EXTRA_USER = "extra_user"
    }
}

