package data.remote

import domain.repo.MovieRepo

class MovieRepoImpl(
    private val movieDataSource: MovieDataSource = dataSource
): MovieRepo {

    override suspend fun getHomeTitles(): List<Movie> {
        return movieDataSource.getMovies()
    }

    override suspend fun getTitleDetails(movie: Movie): Result<Movie> {
        return movieDataSource.getMovieDetails(movie)
    }
}