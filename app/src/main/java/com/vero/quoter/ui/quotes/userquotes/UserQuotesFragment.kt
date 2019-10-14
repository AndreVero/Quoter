package com.vero.quoter.ui.quotes.userquotes


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
import kotlinx.android.synthetic.main.fragment_user_quotes.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class UserQuotesFragment : Fragment(), KodeinAware, QuoteAdapter.OnLikeClickListener {

    override val kodein: Kodein by closestKodein()
    private val userQuotesViewModelFactory: UserQuotesViewModelFactory by instance()

    private lateinit var userQuotesViewModel: UserQuotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_quotes, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUi()
    }

    private fun initUi() {
        userQuotesViewModel = ViewModelProviders.of(
            this, userQuotesViewModelFactory).get(UserQuotesViewModel::class.java)
        userQuotesViewModel.getUserQuotes()
        userQuotesViewModel.userQuotes.observe(this, Observer {
            val adapter = QuoteAdapter(it, this)
            adapter.notifyDataSetChanged()
            recycle_view_user_quotes.adapter = adapter
        })
    }

    override fun onLikeClick(quote: Quote) {
    }
}
