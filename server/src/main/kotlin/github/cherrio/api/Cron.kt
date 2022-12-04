package github.cherrio.api

suspend fun getMovies(){
    val movies = movieDataSource.getMovies().map { movieDataSource.getMovieDetails(it) }
    movies.forEach {
        println(it)
        println("---------------------------------")
    }
}