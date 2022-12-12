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

    override suspend fun getStreamUrl(url: String): String {
        return movieDataSource.getDownloadUrl(url)
    }


}