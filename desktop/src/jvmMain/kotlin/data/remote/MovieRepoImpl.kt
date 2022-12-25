package data.remote

import domain.repo.MovieRepo
import domain.usecase.GetTitlesUseCase

class MovieRepoImpl(
    private val movieDataSource: MovieDataSource = dataSource
): MovieRepo {

    override suspend fun getHomeTitles(): List<Movie> {
        return movieDataSource.getMovies()
    }

    override suspend fun getMovie(titleOptions: GetTitlesUseCase.TitleOptions, page: Int): List<Movie> {
        return when(titleOptions){
            GetTitlesUseCase.TitleOptions.HOLLYWOOD ->
                movieDataSource.getMovieByOption("videos/movies/page/$page")
            GetTitlesUseCase.TitleOptions.HOME -> movieDataSource.getMovies(page)
            GetTitlesUseCase.TitleOptions.NOLLYWOOD ->
                movieDataSource.getMovieByOption("videos/nollywood/page/$page")
            GetTitlesUseCase.TitleOptions.TVSERIES ->
                movieDataSource.getMovieByOption("videos/series/page/$page")
            GetTitlesUseCase.TitleOptions.YOLLYWOOD ->
                movieDataSource.getMovieByOption("videos/yoruba/page/$page")
        }
    }

    override suspend fun getTitleDetails(movie: Movie): Result<Movie> {
        return movieDataSource.getMovieDetails(movie)
    }

    override suspend fun getStreamUrl(url: String): String {
        return movieDataSource.getDownloadUrl(url)
    }

    override suspend fun getSeriesDetails(url: String): List<Series> {
        return movieDataSource.getSeriesDetails(url)
    }


}