package com.abid.userapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.abid.userapp.databinding.ActivitySecondScreenBinding

class SecondScreen : AppCompatActivity() {
    private lateinit var binding: ActivitySecondScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.show()
        setTitle(R.string.second_screen)

        val name = intent.getStringExtra("username")
        binding.tvUsername.text = name

        binding.btnChoose.setOnClickListener {
            val intent = Intent(this@SecondScreen, ThirdScreen::class.java)
            startActivityForResult(intent, 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode != Activity.RESULT_OK) return
        when(requestCode){
            1 -> {
                binding.tvSelectUsername.text = data?.getStringExtra("username").toString()
                Log.d("second screen", data?.getStringExtra("username").toString())
            }
        }
    }
}