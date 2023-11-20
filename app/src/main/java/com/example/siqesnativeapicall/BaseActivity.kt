package com.example.siqesnativeapicall

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import android.widget.Toast

open class BaseActivity : AppCompatActivity() {
    private var doubleBackToExitPressedOnce = false

    private lateinit var mProgressDialog: Dialog

    fun showProgressDialog(text: String) {
        if (!::mProgressDialog.isInitialized || !mProgressDialog.isShowing) {
            mProgressDialog = Dialog(this)
            mProgressDialog.setContentView(R.layout.dialog_progress)
            mProgressDialog.findViewById<TextView>(R.id.tv_progress_text).text = text
            mProgressDialog.setCancelable(false)
            mProgressDialog.setCanceledOnTouchOutside(false)
            mProgressDialog.show()
        }
    }

    fun hideProgressDialog() {
        if (::mProgressDialog.isInitialized && mProgressDialog.isShowing) {
            mProgressDialog.dismiss()
        }
    }

    fun doubleBackToExit() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }
        this.doubleBackToExitPressedOnce = true
        Toast.makeText(
            this,
            resources.getString(R.string.please_click_back_again_to_exit),
            Toast.LENGTH_LONG
        ).show()
        @Suppress("DEPRECATION")
        Handler(Looper.getMainLooper()).postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
    }

    fun showToast(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}