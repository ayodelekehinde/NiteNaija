package domain.repo

import data.remote.Movie
import data.remote.Series
import domain.usecase.GetTitlesUseCase

interface MovieRepo {

    suspend fun getHomeTitles(): List<Movie>
    suspend fun getMovie(titleOptions: GetTitlesUseCase.TitleOptions, page: Int): List<Movie>
    suspend fun getTitleDetails(movie: Movie): Result<Movie>
    suspend fun getStreamUrl(url: String): String

    suspend fun getSeriesDetails(url: String): List<Series>

}