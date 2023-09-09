package presentation.home

import data.remote.Movie
import data.remote.MovieType
import data.remote.Series
import domain.usecase.GetTitleDetailsUseCase
import domain.usecase.GetTitlesUseCase
import kotlinx.coroutines.CoroutineScope
import org.koin.core.component.KoinComponent
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce

class AppViewModel(
    private val scope: CoroutineScope,
    private val getTitlesUseCase: GetTitlesUseCase,
    private val getTitleDetailsUseCase: GetTitleDetailsUseCase
): ContainerHost<HomeState, Nothing> {
    private val viewModelScope = scope
    override val container = viewModelScope.container<HomeState, Nothing>(HomeState())

     fun getTitles(sidePaneOptions: SidePaneOptions) = intent {
         if (!state.isMovieDetails) {
             reduce { state.copy(loading = true) }
             val data = getTitlesUseCase(titleOptions = sidePaneOptions.toTitle())
             if (sidePaneOptions == SidePaneOptions.HOME){
                 reduce { state.copy(loading = false, homeTitle = data) }
             }else {
                 reduce {
                     state.copy(
                         loading = false,
                         titles = data[sidePaneOptions.toTitle().toString()].orEmpty(),
                         currentPage = sidePaneOptions.toTitle().toString()
                     )
                 }
             }
         }
    }
    fun openMovieDetails(movie: Movie) = intent {
        reduce { state.copy(isMovieDetails = true, movie = movie, series = emptyList()) }
    }

    fun back() = intent {
        reduce { state.copy(
            isMovieDetails = false,
            movie = null,
            isMoviePlaying = false
        ) }
    }
    fun fetchMovieDetails(movie: Movie) = intent {
        if (movie.isComplete()){
            when(movie.movieType){
                MovieType.HOLLYWOOD -> reduce { state.copy(movie = movie) }
                MovieType.NOLLYWOOD -> TODO()
                MovieType.YORUBA -> TODO()
                MovieType.SERIES -> reduce { state.copy(series = state.series) }
            }
            reduce { state.copy(movie = movie) }
        }else {
            reduce { state.copy(isDetailsLoading = true) }
            val result = when(movie.movieType){
                MovieType.HOLLYWOOD -> getTitleDetailsUseCase(movie)
                MovieType.NOLLYWOOD -> TODO()
                MovieType.YORUBA -> TODO()
                MovieType.SERIES -> getTitleDetailsUseCase.getSeriesDetails(movie)
            }
            result.onSuccess {
                when(movie.movieType){
                    MovieType.SERIES -> {
                        reduce { state.copy(isDetailsLoading = false, series = it as List<Series>) }
                    }
                    else -> reduce { state.copy(isDetailsLoading = false, movie = it as Movie) }
                }

            }.onFailure {
                reduce { state.copy(isDetailsLoading = false) }
            }
        }
    }

    fun playMovie(movie: Movie) = intent {
        when(movie.movieType){
            MovieType.HOLLYWOOD -> reduce { state.copy(isMoviePlaying = true, movie = movie, isMovieDetails = false) }
            MovieType.NOLLYWOOD -> TODO()
            MovieType.YORUBA -> TODO()
            MovieType.SERIES -> {
                if (!state.isDetailsLoading) {
                    reduce { state.copy(isDetailsLoading = true) }
                    val response = getTitleDetailsUseCase(movie)
                    response.onSuccess {
                        reduce {
                            state.copy(
                                isMoviePlaying = true,
                                isDetailsLoading = false,
                                movie = it,
                                isMovieDetails = false
                            )
                        }
                    }
                }
            }
        }
    }
    private fun SidePaneOptions.toTitle() = when(this){
        SidePaneOptions.HOLLYWOOD -> GetTitlesUseCase.TitleOptions.HOLLYWOOD
        SidePaneOptions.HOME -> GetTitlesUseCase.TitleOptions.HOME
        SidePaneOptions.NOLLYWOOD -> GetTitlesUseCase.TitleOptions.NOLLYWOOD
        SidePaneOptions.TVSERIES -> GetTitlesUseCase.TitleOptions.TVSERIES
        SidePaneOptions.YOLLYWOOD -> GetTitlesUseCase.TitleOptions.YOLLYWOOD
    }

    fun loadMore(options: SidePaneOptions, page: Int) = intent {
        println("Page: $page")
        if (!state.isMovieDetails) {
            reduce { state.copy(isLoadingMore = true) }
            val data = getTitlesUseCase(titleOptions = options.toTitle(), page)
            val newMovies = state.titles + data[options.toTitle().toString()].orEmpty()
            reduce {
                state.copy(isLoadingMore = false, titles = newMovies, currentPage = options.toTitle().toString())
            }
        }
    }

    companion object: KoinComponent{
        fun getViewModel() = getKoin().get<AppViewModel>()
    }

}

data class HomeState(
    val loading: Boolean = false,
    val titles: List<Movie> = emptyList(),
    val homeTitle: Map<String, List<Movie>> = mapOf(),
    val isMovieDetails: Boolean = false,
    val movie: Movie? = null,
    val isDetailsLoading: Boolean = false,
    val isMoviePlaying: Boolean = false,
    val series: List<Series> = emptyList(),
    val isLoadingMore: Boolean = false,
    val currentPage: String = ""
)