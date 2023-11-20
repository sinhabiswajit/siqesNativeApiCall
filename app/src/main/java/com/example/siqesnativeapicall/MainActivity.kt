package com.example.siqesnativeapicall

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.siqesnativeapicall.adapters.QuoteAdapter
import com.example.siqesnativeapicall.api.QuoteService
import com.example.siqesnativeapicall.api.RetrofitHelper
import com.example.siqesnativeapicall.repository.QuoteRepository
import com.example.siqesnativeapicall.viewmodels.MainViewModel
import com.example.siqesnativeapicall.viewmodels.MainViewModelFactory

class MainActivity : BaseActivity() {

    lateinit var mainViewModel: MainViewModel
    private lateinit var quoteAdapter: QuoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set OnClickListener for the exit app ImageView
        findViewById<ImageView>(R.id.iv_exit_app).setOnClickListener {
            showExitConfirmationDialog()
        }

        // Initialize RecyclerView and Adapter
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        quoteAdapter = QuoteAdapter(emptyList())
        recyclerView.adapter = quoteAdapter

        showProgressDialog(resources.getString(R.string.please_wait))

        val repository = (application as QuoteApplication).quoteRepository
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository))[MainViewModel::class.java]

        mainViewModel.quotes.observe(this, Observer {
            Log.d("SIQES_QUOTES", it.results.toString())
            hideProgressDialog()
            //Toast.makeText(this@MainActivity, "Quotes shown: " +it.results.size.toString(), Toast.LENGTH_LONG).show()
            showToast("Quotes shown: " +it.results.size.toString())
            quoteAdapter.updateQuotes(it.results)
        })
    }

    private fun showExitConfirmationDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Exit App")
        builder.setMessage("Are you sure you want to exit the app?")
        builder.setPositiveButton("Yes") { _, _ ->
            // User clicked "Yes"
            finish() // Close the app
        }
        builder.setNegativeButton("No") { dialog, _ ->
            // User clicked "No"
            dialog.dismiss() // Dismiss the dialog
        }
        builder.show()
    }

    @Deprecated("", ReplaceWith("doubleBackToExit()"))
    override fun onBackPressed() {
        doubleBackToExit()
    }
}