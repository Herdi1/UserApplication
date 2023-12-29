package com.abid.userapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.abid.userapp.R
import androidx.recyclerview.widget.LinearLayoutManager
import com.abid.userapp.databinding.ActivityThirdScreenBinding

class ThirdScreen : AppCompatActivity() {
    private lateinit var binding: ActivityThirdScreenBinding
    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.show()
        setTitle(R.string.third_screen)

        binding.rvUsers.layoutManager = LinearLayoutManager(this)

        getData()
    }

    private fun getData(){
        val adapter = UserAdapter(this)
        binding.rvUsers.adapter = adapter
        viewModel.users.observe(this, {
            adapter.submitData(lifecycle, it)
        })
    }
}