package com.vero.quoter

import android.app.Application
import com.vero.quoter.data.db.QuotesDatabase
import com.vero.quoter.data.network.repository.QuoteRepository
import com.vero.quoter.data.network.repository.QuoteRepositoryImpl
import com.vero.quoter.ui.quotes.authorquotes.AuthorQuotesViewModelFactory
import com.vero.quoter.ui.quotes.likedquotes.LikedQuotesViewModelFactory
import com.vero.quoter.ui.quotes.userquotes.UserQuotesViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class QuoteApplication: Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@QuoteApplication))

        bind() from singleton { QuotesDatabase(instance()) }
        bind() from singleton { instance<QuotesDatabase>().quotesDao() }
        bind<QuoteRepository>() with singleton { QuoteRepositoryImpl(instance()) }
        bind() from provider { AuthorQuotesViewModelFactory(instance()) }
        bind() from provider { LikedQuotesViewModelFactory(instance())}
        bind() from provider { UserQuotesViewModelFactory(instance()) }
    }
}