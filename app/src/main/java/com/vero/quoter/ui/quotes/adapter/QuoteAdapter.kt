package com.vero.quoter.ui.quotes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vero.quoter.R
import com.vero.quoter.data.db.entity.Quote
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.quote_item.*

class QuoteAdapter(private var quotesList: List<Quote>, private val listener: OnLikeClickListener) :
    RecyclerView.Adapter<QuoteAdapter.QuoteHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        QuoteHolder(LayoutInflater.from(parent.context).inflate(R.layout.quote_item, parent, false))

    override fun getItemCount() = quotesList.size

    override fun onBindViewHolder(holder: QuoteHolder, position: Int) {
        holder.bind(quotesList[position])
    }

    inner class QuoteHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(item: Quote) {
            quote_author.text = item.author
            quote_body.text = item.quote
            if (item.liked) {
                like_image_btn.setImageResource(R.drawable.ic_like_full)
            } else {
                like_image_btn.setImageResource(R.drawable.ic_like)
            }
            like_image_btn.setOnClickListener {
                listener.onLikeClick(item)
            }
        }
    }

    interface OnLikeClickListener {
        fun onLikeClick(quote: Quote)
    }
}