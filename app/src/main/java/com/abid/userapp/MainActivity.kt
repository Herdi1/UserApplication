package com.abid.userapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.abid.userapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.btnCheck.setOnClickListener {
            val dialog = PalindromeFragment()
            val bundle = Bundle()
            bundle.putBoolean("isPalindrome", palindromeCheck(binding.edPlindrome.text.toString()))
            dialog.arguments = bundle
            dialog.show(supportFragmentManager, "fragmentPalindrome")
        }

        binding.btnNext.setOnClickListener {
            val intent = Intent(this@MainActivity, SecondScreen::class.java)
            intent.putExtra("username", binding.edName.text.toString())
            startActivity(intent)
        }
    }

    fun palindromeCheck(text: String): Boolean{
        val reversedText = text.reversed()
        return text == reversedText
    }
}