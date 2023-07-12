package com.example.suitmediatest.ui.third_screen

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.suitmediatest.adapter.LoadingStateAdapter
import com.example.suitmediatest.adapter.OnItemClickListener
import com.example.suitmediatest.adapter.UserListAdapter
import com.example.suitmediatest.databinding.ActivityThirdScreenBinding
import androidx.activity.addCallback


class ThirdScreen : AppCompatActivity(), OnItemClickListener {

    private lateinit var binding: ActivityThirdScreenBinding
    private lateinit var viewModel: ThirdScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdScreenBinding.inflate(layoutInflater)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

        setContentView(binding.root)


        val factory = ViewModelFactory.createFactory(this, application)
        viewModel = ViewModelProvider(this, factory)[ThirdScreenViewModel::class.java]

        binding.rvUser.layoutManager = LinearLayoutManager(this)
        val adapter = UserListAdapter(this)

        binding.rvUser.adapter = adapter.withLoadStateFooter(
            footer = LoadingStateAdapter {
                adapter.retry()
            }
        )

        viewModel.users.observe(this) {
            adapter.submitData(lifecycle, it)
        }

        binding.swLayout.setOnRefreshListener {
            adapter.refresh()

            binding.swLayout.isRefreshing = false
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finish()
            }
        })
    }

    override fun itemClick(data: String) {
        setResult(
            RESULT_OK,
            Intent().putExtra("user", data))
        finish()
    }


}