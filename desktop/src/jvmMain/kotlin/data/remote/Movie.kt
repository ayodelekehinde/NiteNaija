package data.remote

import kotlinx.serialization.Serializable
import presentation.player.Subtitle

@Serializable
data class Movie(
    val name: String,
    val url: String,
    val image: String,
    val movieType: MovieType,
    val summary: String = "",
    val genre: String = "",
    val releaseDate: String = "",
    val stars: String = "",
    val downloadUrl: String = "",
    val streamingUrl: String = "",
    val subtitleUrl: String = ""
){
    fun isComplete() = summary.isNotEmpty() && streamingUrl.isNotEmpty()

}

enum class MovieType{
    HOLLYWOOD, NOLLYWOOD, YORUBA, SERIES
}


@Serializable
data class Series(
    val seasons: Pair<String, List<Movie>>
)

