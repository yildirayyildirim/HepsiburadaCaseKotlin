package com.app.hepsiburada.core.repository

import com.app.hepsiburada.core.network.ITunesService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TunesRepository
@Inject
constructor(private val apiService: ITunesService) {

    suspend fun getAllTunesModelByTermAndEntityAndLimit(
        term: String,
        entity: String,
        pageNumber: Int,
    ) =
        apiService.getAllTunesModelByTermAndEntityAndLimit(term, entity, pageNumber)

    suspend fun getAllTunesModelByTerm(term: String, entity: String) =
        apiService.getAllTunesModelByTerm(term, entity)

    suspend fun getTunesModelById(id: Int) = apiService.getTunesModelById(id)
}