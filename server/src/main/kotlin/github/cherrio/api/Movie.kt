package github.cherrio.api

import kotlinx.serialization.Serializable

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
    val streamingUrl: String = ""
)

enum class MovieType{
    HOLLYWOOD, NOLLYWOOD, YORUBA, SERIES
}

