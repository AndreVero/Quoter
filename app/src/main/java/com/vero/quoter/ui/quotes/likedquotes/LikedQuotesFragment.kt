package com.vero.quoter.ui.quotes.likedquotes


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
import kotlinx.android.synthetic.main.fragment_liked_quotes.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class LikedQuotesFragment : Fragment(), KodeinAware, QuoteAdapter.OnLikeClickListener {

    override val kodein: Kodein by closestKodein()
    private val likedQuotesViewModelFactory: LikedQuotesViewModelFactory by instance()

    private lateinit var likedQuotesViewModel: LikedQuotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_liked_quotes, container, false)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUi()
    }

    private fun initUi() {
        likedQuotesViewModel = ViewModelProviders.of(this, likedQuotesViewModelFactory)
            .get(LikedQuotesViewModel::class.java)
        likedQuotesViewModel.getLikedQuotes()
        likedQuotesViewModel.quotes.observe(this, Observer {
            val adapter = QuoteAdapter(it, this)
            adapter.notifyDataSetChanged()
            recycle_view_liked_quotes.adapter = adapter
        })
    }

    override fun onLikeClick(quote: Quote) {
        likedQuotesViewModel.removeFromLiked(quote)
    }
}
