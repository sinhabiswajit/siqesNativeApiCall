package com.example.siqesnativeapicall.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.siqesnativeapicall.api.QuoteService
import com.example.siqesnativeapicall.db.QuoteDatabase
import com.example.siqesnativeapicall.models.QuoteList
import com.example.siqesnativeapicall.utils.NetworkUtils

class QuoteRepository(
    private val quoteService: QuoteService,
    private val quoteDatabase: QuoteDatabase,
    private val applicationContext: Context
) {

    private val quotesLiveData = MutableLiveData<QuoteList>()

    val quotes: LiveData<QuoteList>
        get() = quotesLiveData

    suspend fun getQuotes(page: Int) {
        if (NetworkUtils.isInternetAvailable(applicationContext)) {
            val result = quoteService.getQuotes(page)
            if (result.body() != null) {
                val quotesFromApi = result.body()!!.results
                // Check if the quotes from the API already exist in the database
                val existingQuotes = quoteDatabase.quoteDao().getQuotes()
                val newQuotes = quotesFromApi.filterNot { apiQuote ->
                    existingQuotes.any { existingQuote ->
                        apiQuote._id == existingQuote._id
                    }
                }
                // Add only new quotes to the database
                if (newQuotes.isNotEmpty()) {
                    quoteDatabase.quoteDao().addQuotes(newQuotes)
                }
                //quoteDatabase.quoteDao().addQuotes(result.body()!!.results)
                quotesLiveData.postValue(result.body())
            }
        } else {
            val quotes = quoteDatabase.quoteDao().getQuotes()
            val quoteList = QuoteList(1, 1, 1, quotes, 1, 1)
            quotesLiveData.postValue(quoteList)
        }

    }

}