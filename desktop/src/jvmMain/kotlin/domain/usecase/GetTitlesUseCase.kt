package domain.usecase

import data.remote.Movie
import data.remote.MovieType
import domain.repo.MovieRepo

class GetTitlesUseCase(private val movieRepo: MovieRepo) {

    suspend operator fun invoke(titleOptions: TitleOptions, page: Int = 1): Map<String, List<Movie>> {
       return when(titleOptions){
            TitleOptions.HOLLYWOOD -> {
                val list = movieRepo.getMovie(TitleOptions.HOLLYWOOD, page)
                mapOf(
                    TitleOptions.HOLLYWOOD.toString() to list,
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
                mapOf(
                    TitleOptions.NOLLYWOOD.toString() to movieRepo.getMovie(TitleOptions.NOLLYWOOD, page),
                )
            }
            TitleOptions.TVSERIES -> {
                val list = movieRepo.getMovie(TitleOptions.TVSERIES, page)
                mapOf(
                    TitleOptions.TVSERIES.toString() to list,
                )
            }
            TitleOptions.YOLLYWOOD -> {
                mapOf(
                    TitleOptions.YOLLYWOOD.toString() to movieRepo.getMovie(TitleOptions.YOLLYWOOD, page),
                )
            }
        }

    }


    sealed interface TitleOptions{
        data object HOME: TitleOptions
        data object HOLLYWOOD: TitleOptions
        data object NOLLYWOOD: TitleOptions
        data object TVSERIES: TitleOptions
        data object YOLLYWOOD: TitleOptions
    }
}