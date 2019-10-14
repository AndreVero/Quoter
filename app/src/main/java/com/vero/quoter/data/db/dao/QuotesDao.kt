package com.vero.quoter.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vero.quoter.data.db.entity.Quote

@Dao
interface QuotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(quotesList: List<Quote>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(quote: Quote)

    @Query("select * from quotes")
    fun getQuotes(): List<Quote>

    @Query("select * from quotes where id = :id")
    fun getQuoteById(id: Int?): Quote

    @Query("select * from quotes where liked = 1")
    fun getLikedQuotes(): List<Quote>

    @Query("select * from quotes where author = 'user'")
    fun getUserQuotes(): List<Quote>

}