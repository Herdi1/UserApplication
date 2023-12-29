package com.abid.userapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment

class PalindromeFragment: DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView: View = inflater.inflate(R.layout.fragment_palindrome, container, false)

        val bundle: Bundle? = arguments
        val isPalindrome = bundle?.getBoolean("isPalindrome", false)

        val palindromeText = rootView.findViewById<TextView>(R.id.palindrome_text)
        if(isPalindrome == true){
            palindromeText.text = getString(R.string.isPalindrome)
        }else{
            palindromeText.text = getString(R.string.notPalindrome)
        }

        val btnOk = rootView.findViewById<Button>(R.id.btn_ok)
        btnOk.setOnClickListener {
            dismiss()
        }

        return rootView
    }
}