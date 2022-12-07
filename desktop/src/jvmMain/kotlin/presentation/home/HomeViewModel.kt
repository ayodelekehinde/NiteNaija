package presentation.home

import data.remote.Movie
import domain.usecase.GetTitleDetailsUseCase
import domain.usecase.GetTitlesUseCase
import kotlinx.coroutines.MainScope
import org.koin.core.component.KoinComponent
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce

class HomeViewModel(
    private val getTitlesUseCase: GetTitlesUseCase,
    private val getTitleDetailsUseCase: GetTitleDetailsUseCase
): ContainerHost<HomeState, Nothing> {
    private val viewModelScope = MainScope()
    override val container = viewModelScope.container<HomeState, Nothing>(HomeState())

     fun getTitles() = intent {
        reduce { state.copy(loading = true) }
        val data = getTitlesUseCase()
        reduce { state.copy(loading = false, titles = data) }
    }
    fun openMovieDetails(movie: Movie) = intent {
        reduce { state.copy(isMovieDetails = true, movie = movie) }
    }

    fun back() = intent {
        reduce { state.copy(isMovieDetails = false, movie = null) }
    }
    fun fetchMovieDetails(movie: Movie) = intent {
        reduce { state.copy(isDetailsLoading = true) }
        getTitleDetailsUseCase(movie).onSuccess {
            reduce { state.copy(isDetailsLoading = false, movie = it) }
        }.onFailure {
            reduce { state.copy(isDetailsLoading = false) }
        }
    }

    companion object: KoinComponent{
        fun getViewModel() = getKoin().get<HomeViewModel>()
    }

}

data class HomeState(
    val loading: Boolean = false,
    val titles: Map<String, List<Movie>>? = null,
    val isMovieDetails: Boolean = false,
    val movie: Movie? = null,
    val isDetailsLoading: Boolean = false
)