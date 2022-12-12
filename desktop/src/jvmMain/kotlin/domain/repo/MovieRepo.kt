package domain.repo

import data.remote.Movie

interface MovieRepo {

    suspend fun getHomeTitles(): List<Movie>
    suspend fun getTitleDetails(movie: Movie): Result<Movie>
    suspend fun getStreamUrl(url: String): String

}