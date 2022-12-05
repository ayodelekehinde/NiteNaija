package github.cherrio.api

interface MovieDao {
    //Todo: Series
    fun getMovies(type: MovieType): List<Movie>
    fun getMovie(url: String): Movie
}