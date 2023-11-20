package com.example.siqesnativeapicall

import android.app.Application
import com.example.siqesnativeapicall.api.QuoteService
import com.example.siqesnativeapicall.api.RetrofitHelper
import com.example.siqesnativeapicall.db.QuoteDatabase
import com.example.siqesnativeapicall.repository.QuoteRepository

class QuoteApplication : Application() {

    lateinit var quoteRepository: QuoteRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val quoteService = RetrofitHelper.getInstance().create(QuoteService::class.java)
        val database = QuoteDatabase.getDatabase(applicationContext)
        quoteRepository = QuoteRepository(quoteService, database, applicationContext)
    }
}