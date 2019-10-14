package com.vero.quoter.data.db

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.vero.quoter.data.db.dao.QuotesDao
import com.vero.quoter.data.db.entity.Quote
import com.vero.quoter.data.network.JsonQuotesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Database(
    entities = [Quote::class],
    version = 1,
    exportSchema = false
)
abstract class QuotesDatabase: RoomDatabase() {
    abstract fun quotesDao(): QuotesDao

    companion object {
        @Volatile private var instance: QuotesDatabase? = null
        private var LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(
                LOCK
            ) {
                instance
                    ?: buildDatabase(context).also{ instance = it
                }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                QuotesDatabase::class.java, "quote.db")
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        GlobalScope.launch {
                            Log.d(this::class.java.toString(), "DB init")
                            val postResponse = JsonQuotesApi.getApi().getQuotes()
                            if (postResponse.isSuccessful) {
                                val listOfQuotes = postResponse.body() ?: listOf()
                                instance!!.quotesDao().insertAll(listOfQuotes)
                            }
                        }
                    }
                })
                .build()

    }
}