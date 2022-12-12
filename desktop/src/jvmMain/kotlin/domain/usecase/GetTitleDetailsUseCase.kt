package domain.usecase

import data.remote.Movie
import domain.repo.MovieRepo
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class GetTitleDetailsUseCase(
    private val movieRepo: MovieRepo
) {

    suspend operator fun invoke(movie: Movie): Result<Movie>{
        return coroutineScope {
            val movieDetails = async { movieRepo.getTitleDetails(movie).getOrNull() }.await()
            val streamLink = async { movieRepo.getStreamUrl("${movie.url}/download") }.await()
            val subLink = async { movieRepo.getStreamUrl("${movie.url}/download-sub") }.await()
            if (movieDetails != null){
                Result.success(movieDetails.copy(streamingUrl = streamLink, subtitleUrl = subLink))
            }else{
                Result.failure(Throwable("An error occurred"))
            }
        }
    }
}