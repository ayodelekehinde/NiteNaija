package domain.usecase

import data.remote.Movie
import data.remote.MovieType
import domain.repo.MovieRepo

class GetTitlesUseCase(private val movieRepo: MovieRepo) {

    suspend operator fun invoke(): Map<String, List<Movie>> {
        val result = movieRepo.getHomeTitles()
        val hollywood = result.filter { movie -> movie.movieType == MovieType.HOLLYWOOD }
        val nollywood = result.filter { movie -> movie.movieType == MovieType.NOLLYWOOD }
        val yollywood = result.filter { movie -> movie.movieType == MovieType.YORUBA }
        val series = result.filter { movie -> movie.movieType == MovieType.SERIES }
        return mapOf(
            "Hollywood" to hollywood,
            "Nollywood" to nollywood,
            "Yollywood" to yollywood,
            "Tv series" to series
        )
    }
}