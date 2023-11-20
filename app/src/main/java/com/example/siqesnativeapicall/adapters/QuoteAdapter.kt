package com.example.siqesnativeapicall.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.siqesnativeapicall.R
import com.example.siqesnativeapicall.models.Result

class QuoteAdapter(private var quotes: List<Result>) :
    RecyclerView.Adapter<QuoteAdapter.QuoteViewHolder>() {

    fun updateQuotes(newQuotes: List<Result>) {
        quotes = newQuotes
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_quote, parent, false)
        return QuoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        val quote = quotes[position]
        holder.bind(quote)
    }

    override fun getItemCount(): Int = quotes.size

    class QuoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textAuthor: TextView = itemView.findViewById(R.id.textAuthor)
        private val textContent: TextView = itemView.findViewById(R.id.textContent)

        fun bind(quote: Result) {
            textAuthor.text = quote.author
            textContent.text = quote.content
        }
    }
}
