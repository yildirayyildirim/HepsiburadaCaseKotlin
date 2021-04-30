package com.app.hepsiburada.core.network

import com.app.hepsiburada.core.base.BaseModel
import com.app.hepsiburada.model.TunesModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ITunesService {

    /**
     * @param term :The URL-encoded text string you want to search *required
     * @param entity: The type of results you want returned, relative to the specified media type *required
     * @param limit : The number of search results you want the iTunes Store to return  *optional
     * @return the List<TunesModel> at the specified URL
     * @see List<>
     */
    @GET("search?")
    suspend fun getAllTunesModelByTermAndEntityAndLimit(
        @Query("term") term: String = "all",
        @Query("entity") entity: String,
        @Query("limit") limit: Int?,
    ): Response<BaseModel<TunesModel>>


    /**
     * @param term :The URL-encoded text string you want to search *required
     * @param entity: The type of results you want returned, relative to the specified media type *required
     * @return the List<TunesModel> at the specified URL
     * @see List<>
     */
    @GET("search?")
    suspend fun getAllTunesModelByTerm(
        @Query("term") term: String,
        @Query("entity") entity: String,
        @Query("limit") limit: Int = 200,
    ): Response<BaseModel<TunesModel>>

    /**
     * @param id :The URL-encoded int you want to search *required
     * @return the TunesModel at the specified URL
     * @see Object
     */
    @GET("lookup?")
    suspend fun getTunesModelById(
        @Query("id") id: Int,
    ): Response<BaseModel<TunesModel>>
}