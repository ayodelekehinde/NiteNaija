package domain.usecase

import data.remote.Movie
import domain.repo.MovieRepo

class GetTitleDetailsUseCase(
    private val movieRepo: MovieRepo
) {

    suspend operator fun invoke(movie: Movie): Result<Movie>{
        return movieRepo.getTitleDetails(movie)
    }
}