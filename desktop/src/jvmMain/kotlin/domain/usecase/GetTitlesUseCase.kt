package domain.usecase

import data.remote.Movie
import data.remote.MovieType
import domain.repo.MovieRepo
import presentation.home.SidePaneOptions

class GetTitlesUseCase(private val movieRepo: MovieRepo) {

    suspend operator fun invoke(titleOptions: TitleOptions): Map<String, List<Movie>> {
       return when(titleOptions){
            TitleOptions.HOLLYWOOD -> {
                val response = mutableListOf<Movie>()
                repeat(2){
                    val list = movieRepo.getMovie(TitleOptions.HOLLYWOOD,it+1)
                    response.addAll(list)
                }
                mapOf(
                    "Hollywood" to response,
                )
            }
            TitleOptions.HOME -> {
                val result = movieRepo.getHomeTitles()
                val hollywood = result.filter { movie -> movie.movieType == MovieType.HOLLYWOOD }
                val nollywood = result.filter { movie -> movie.movieType == MovieType.NOLLYWOOD }
                val yollywood = result.filter { movie -> movie.movieType == MovieType.YORUBA }
                val series = result.filter { movie -> movie.movieType == MovieType.SERIES }
                mapOf(
                    "Hollywood" to hollywood,
                    "Nollywood" to nollywood,
                    "Yollywood" to yollywood,
                    "Tv Series" to series
                )
            }
            TitleOptions.NOLLYWOOD -> {
                val response = mutableListOf<Movie>()
                repeat(2){
                    val list = movieRepo.getMovie(TitleOptions.NOLLYWOOD,it+1)
                    response.addAll(list)
                }
                mapOf(
                    "Nollywood" to response,
                )
            }
            TitleOptions.TVSERIES -> {
                val response = mutableListOf<Movie>()
                repeat(2){
                    val list = movieRepo.getMovie(TitleOptions.TVSERIES,it+1)
                    response.addAll(list)
                }
                mapOf(
                    "Tv Series" to response,
                )
            }
            TitleOptions.YOLLYWOOD -> {
                val response = mutableListOf<Movie>()
                repeat(2){
                    val list = movieRepo.getMovie(TitleOptions.YOLLYWOOD,it+1)
                    response.addAll(list)
                }
                mapOf(
                    "Yollywood" to response,
                )
            }
        }

    }


    sealed interface TitleOptions{
        object HOME: TitleOptions
        object HOLLYWOOD: TitleOptions
        object NOLLYWOOD: TitleOptions
        object TVSERIES: TitleOptions
        object YOLLYWOOD: TitleOptions
    }
}