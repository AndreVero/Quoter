package com.vero.quoter.ui.quotes.authorquotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.vero.quoter.R
import com.vero.quoter.data.db.entity.Quote
import com.vero.quoter.ui.quotes.adapter.QuoteAdapter
import kotlinx.android.synthetic.main.fragment_author_quotes.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class AuthorQuotesFragment : Fragment(), KodeinAware, QuoteAdapter.OnLikeClickListener {

    override val kodein: Kodein by closestKodein()
    private val authorQuotesViewModelFactory: AuthorQuotesViewModelFactory by instance()

    private lateinit var authorQuotesViewModel: AuthorQuotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_author_quotes, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUi()
    }

    private fun initUi() {
        authorQuotesViewModel = ViewModelProviders.of(this, authorQuotesViewModelFactory)
            .get(AuthorQuotesViewModel::class.java)
        authorQuotesViewModel.getQuotes()
        authorQuotesViewModel.quotes.observe(this, Observer {
            val adapter = QuoteAdapter(it, this)
            adapter.notifyDataSetChanged()
            recycle_view_author_quotes.adapter = adapter
        })
    }

    override fun onLikeClick(quote: Quote) {
        authorQuotesViewModel.onLikeClick(quote)
    }


}
